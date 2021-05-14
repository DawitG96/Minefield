package minefield.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minefield.object.Field;
import minefield.object.FieldNotSafe;
import minefield.object.FieldSafe;
import minefield.view.object.CellObserver;

/**
*La classe GameUtility contiene dei metodi per uso generale.
* @author Dawit&Stefano
*
*/
public class GameUtility {
	
	/**
	 * Indica se hai perso controllando per ogni cella se e' esplosa una mina 
	 * @param field Il campo
	 * @return true se hai perso, false altrimenti
	 */
	public static boolean hasLost(Field field) {
		for(int row=0; row < field.maxRow; row++)
			for(int column=0; column < field.maxColumn; column ++)
				if(hasLost(field, row, column))
					return true;
		return false;
	}
	
	/**
	 * Indica se hai perso controllando se in una cella e' esplosa una mina
	 * @param field Il campo
	 * @param row La riga
	 * @param column La colonna
	 * @return true se e' esplosa la mina in quella cella, false altrimenti
	 */
	public static boolean hasLost(Field field, int row, int column) {
		return field.isCellExploded(row, column);
	}
	
	/**
	 * Indica se hai vinto. Si vince se il numero ci celle coperte o il numero di bandierine nel posto giusto è uguale al numero di mine 
	 * @param field
	 * @return true se ha vinto, false se non ha vinto
	 */
	public static boolean hasWon(Field field) {
		if(hasLost(field)==false) {
			if(field.getNumCoveredCell()==field.numberOfMines || field.getNumberMinesFlagged()==field.numberOfMines)
				return true;
		}
		return false;
	}
	
	/**
	 * Costruisce un nuovo campo a seconda se e' safe o no, e della difficolta'
	 * @param safe puo' essere true o false perchè ci puo' essere o meno il safe start
	 * @param diff La difficoltà della partita'
	 * @return Il campo creato a seconda delle opzioni scelte
	 * @throws IllegalArgumentException  
	 */
	public static Field buildNewField(boolean safe, Difficulty diff) {
		switch(diff) {
		case EASY:
			return GameUtility.buildNewField(safe, 9, 9, 10);
		case MEDIUM:
			return GameUtility.buildNewField(safe, 16, 16, 40);
		case HARD:
			return GameUtility.buildNewField(safe, 16, 30, 99);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Costruisce un nuovo campo quando si e' scelta la difficolta' personalizzata
	 * @param safe puo' essere true o false perchè ci puo' essere o meno il safe start
	 * @param column Il numero di colonne
	 * @param row Il numero di righe
	 * @param mine Il numero di mine
	 * @return Un campo sicuro se e' stato scelto il safe start, altrimenti un campo non sicuro
	 */
	public static Field buildNewField(boolean safe, int column, int row, int mine) {
		if(!safe)
			return new FieldNotSafe(column, row, mine);
		else
			return new FieldSafe(column, row, mine);
	}
	
	/**
	 * Trasforma il campo in una stringa
	 * @param field Il campo
	 * @return Una stringa che rappresenta il campo
	 */
	public static String viewString(Field field) {
		String matrix = new String("\t");
		
		for(int row=0;row<field.maxColumn;row++)
			matrix+=row+"\t";
		matrix+="\n";
		for(int row=0; row<field.maxRow; row++) {
			matrix+=row+"\t";
			for(int column=0; column<field.maxColumn; column++)
				matrix += viewString(field.getCellStatus(row, column), field.getNumMineNearCell(row, column))+"\t";
			
			matrix+="\n";
		}
		return matrix;
	}
	
	/**
	 * Trasforma una cella in una stringa
	 * @param status Lo stato della cella
	 * @param numMineNear Il numero di mine intorno a quella cella
	 * @return La stringa della cella
	 */
	public static String viewString(Status status, int numMineNear) {
		String symbol = new String("");
		
		if(status==Status.DANGEROUS)
			symbol+=StringToShow.CELL_SYMBOL_FOR_DANGEROUS;
		else if(status==Status.COVERED)
			symbol+=StringToShow.CELL_SYMBOL_FOR_COVERED;
		else if(status==Status.EXPLODED)
			symbol+=StringToShow.CELL_SYMBOL_FOR_EXPLODED;
		else
			symbol+="["+ numMineNear +"]";
		
		return symbol;
	}
	
	/**
	 * Ci permette di scoprire tutto il campo
	 * @param field Il  campo
	 */
	public static void uncoverAllField(Field field) {
		for(int row=0; row< field.maxRow; row++)
			for(int column=0; column<field.maxColumn; column++) {
				field.unSetDangerousCell(row, column);
				field.uncoverCell(row, column);				
			}
		}
	
	/**
	 * Restituisce il pannello generato per la visualizzazione del campo
	 * @param field il campo
	 * @return il pannello generato
	 */
	public static JPanel viewJPanel(Field field) {		
		return viewJPanel(field, null);
	}

	/**
	 * Permette di creare il pannello per la griglia di gioco
	 * @param field Il campo
	 * @param listener Il listener
	 * @return Il Pannello per la griglia di gioco
	 */
	public static JPanel viewJPanel(Field field, MouseListener listener) {
		JPanel fieldView = new JPanel();
		fieldView.setLayout(new GridLayout(field.maxRow, field.maxColumn));
		Dimension dim = new Dimension();
		
		for(int row=0; row < field.maxRow; row++)
			for(int column=0; column < field.maxColumn; column++) {
				JLabel label = viewJLabel(field.getCellStatus(row, column),field.getNumMineNearCell(row, column), listener);
				label.setName(row +"-"+ column);
				
				field.addObserverToCell(row, column, new CellObserver(label));
				fieldView.add(label);
				if(row==0 && column==0) {
					Dimension labelDim = label.getPreferredSize();
					dim = new Dimension(labelDim.width*field.maxColumn, labelDim.height*field.maxRow);
				}
			}
		fieldView.setPreferredSize(dim);
		fieldView.setSize(dim);
		
		return fieldView;
		
	}
	
	/**
	 * Restituisce il label generato per ogni cella
	 * @param status
	 * @param numMineNear
	 * @return label generato
	 */
	public static JLabel viewJLabel(Status status, int numMineNear) {
		return viewJLabel(status, numMineNear, null);
	}
	
	/**
	 * Permette di creare la rappresentazione grafica della cella
	 * @param status Lo stato della cella
	 * @param numMineNear Il numero di mine intorno alla cella
	 * @param listener Il listener della cella
	 * @return La rappresentazione grafica della cella
	 */
	public static JLabel viewJLabel(Status status, int numMineNear, MouseListener listener) {
		JLabel cellView = new JLabel();
		Dimension dim = new Dimension(21, 21);
		cellView.setPreferredSize(dim);
		cellView.setSize(dim);
		cellView.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		cellView.setOpaque(true);
		cellView.setBackground(Color.GRAY);
		
		if(listener!=null)
			cellView.addMouseListener(listener);
		return cellView;
	}

	/**
	 * Restituisce l'icona di una data cella
	 * @param status Lo stato di una cella
	 * @param numMineNear Il numero di mine attorno alla cella
	 * @return L'icona di quella cella
	 */
	public static Icon getIconOfCell(Status status, int numMineNear) {
		switch (status) {
			case DANGEROUS :
				return GameIcons.FLAG;
			case EXPLODED:
				return GameIcons.BOMB;
			case UNCOVERED:
				return GameIcons.chooseImageIcon(numMineNear);
			default:
				break;
		}
		return null;
	}
}
