package minefield.object;

import minefield.exceptions.ColumnException;
import minefield.exceptions.MineTooHighException;
import minefield.exceptions.MineTooLowException;
import minefield.exceptions.RowException;
import minefield.util.Status;
import minefield.util.StringToShow;
import minefield.view.object.CellObserver;

/**
 * La classe Field definisce le funzioni di base per gestire il campo.
 * Questo verra' poi generato nelle due sottoclassi
 * @author Dawit&Stefano
 *
 */
public abstract class Field {
	
	public final int numberOfMines;
	public final int maxRow;
	public final int maxColumn;
	protected final Cell[][] matrix;
	
	protected int numCellDangerous;
	protected int numCellCovered;
	
	/**
	 * Il costruttore ci permette di creare il Campo
	 * @param row La lunghezza del campo
	 * @param column L'altezza del campo
	 * @param mine Il numero di mine nel campo
	 * @throws ColumnException Input delle dimensioni delle colonne errate
	 * @throws RowException Input delle dimensioni delle righe errate
	 * @throws MineTooLowException Numero mine troppo basso
	 * @throws MineTooHighException Numero mine troppo alto
	 */
	public Field(int row, int column, int mine) {		
		if(column<=0 || column >=51)
			throw new ColumnException(StringToShow.ERROR_FIELD_COLUMN);
		if(row<=0 || row>=31)
			throw new RowException(StringToShow.ERROR_FIELD_ROW);
		if(mine<=0)
			throw new MineTooLowException(StringToShow.ERROR_FIELD_MINE_TOO_LOW);
		if(mine>=row*column)
			throw new MineTooHighException(StringToShow.ERROR_FIELD_MINE_TOO_HIGH);
		
		matrix = new Cell[row][column];
		numCellDangerous=0;
		numCellCovered=row*column;
		numberOfMines = mine;
		maxColumn = column;
		maxRow = row;
		
		buildRawMatrix();
	}
	
	/**
	 * Indica il numero di celle pericolose
	 * @return il numero
	 */
	public int getNumDangerousCell() {
		return numCellDangerous;
	}
	
	/**
	 * Ci permette di aggiungere una cella pericolosa
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se e' stata aggiunta la cella pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean setDangerousCell(int row, int column) {
		checkCoordinates(row, column);
		
		if(matrix[row][column].isDangerous()==false && matrix[row][column].isCovered() && numCellDangerous < numberOfMines) {
			matrix[row][column].setDangerous();
			numCellDangerous++;
			return true;
		}
		return false;
	}
	
	/**
	 * Ci permette di rimuovere una cella pericolosa
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se e' stata rimossa la cella pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean unSetDangerousCell(int row, int column) {
		checkCoordinates(row, column);
		
		if(matrix[row][column].isDangerous() && numCellDangerous <= numberOfMines) {
			matrix[row][column].unSetDangerous();
			numCellDangerous--;
			return true;
		}
		return false;
	}
	
	/**
	 * Indica il numero di celle coperte restanti
	 * @return il numero
	 */
	public int getNumCoveredCell() {
		return numCellCovered;
	}
	
	/**
	 * Ci permette di scoprire una data cella
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se e' stata scoperta con successo, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean uncoverCell(int row, int column) {
		checkCoordinates(row, column);
		
		if(numCellCovered==maxRow*maxColumn)
			buildMatrixWithMines(row,column);
		
		if(matrix[row][column].uncover()) {
			numCellCovered--;
			if(matrix[row][column].getNumMineNear() == 0 && matrix[row][column].isMine()==false)
				uncoverCellNear(row, column);
			return true;
		}
		return false;
	}
	
	/**
	 * Indica il numero di mine intorno a una data cella
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return il numero
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public int getNumMineNearCell(int row, int column) {
		checkCoordinates(row, column);
		
		return matrix[row][column].getNumMineNear();
	}
	
	/**
	 * Indica se la cella e' pericolosa
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se la cella e' pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean isCellDangerous(int row, int column) {
		checkCoordinates(row, column);
		
		return matrix[row][column].isDangerous();
	}
	
	/**
	 * Indica se la cella e' coperta
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se la cella e' coperta, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean isCellCovered(int row, int column) {
		checkCoordinates(row, column);
		
		return matrix[row][column].isCovered();
	}
	
	/**
	 * Indica se c'e' la mina
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return true se c'e' la mina, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean isCellExploded(int row, int column) {
		checkCoordinates(row, column);
		
		if(!matrix[row][column].isCovered())
			return matrix[row][column].isMine();
		return false;
	}
	
	/**
	 * Indica lo stato di una cella precisa
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @return lo stato della cella
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public Status getCellStatus(int row, int column) {
		checkCoordinates(row, column);
		
		return matrix[row][column].getStatus();
	}
	
	/**
	 * Indica il numero di bandierine posizionate sulle mine
	 * @return il numero
	 */
	public int getNumberMinesFlagged() {
		int allFlagsOnAllMines=0;
		
		for(int row=0; row<maxRow; row++)
			for(int column=0; column<maxColumn; column++)
				if(isCellDangerous(row, column) && matrix[row][column].isMine())
					allFlagsOnAllMines++;
		return allFlagsOnAllMines;
	}
	
	/**
	 * Aggiunge la cella agli ossertatori
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @param observer Gli osservatori
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public void addObserverToCell(int row, int column, CellObserver observer) {
		checkCoordinates(row, column);
		
		matrix[row][column].addObserver(observer);
	}
	
	/**
	 * Consente di aggiornare il numero di mine nelle celle attorno alla mina presente nella cella selezionata
	 * @param row La coordinata x della matrice
	 * @param column La coordinata y della matrice
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	protected void updateNeighbourMineNumber(int row, int column) {
		checkCoordinates(row, column);
		
		if(matrix[row][column].isMine()) {
			for(int currentRow=row-1; currentRow<=row+1; currentRow++)
				for(int currentColumn=column-1; currentColumn<=column+1; currentColumn++) {
					try{
						checkCoordinates(currentRow,currentColumn);
						if(matrix[currentRow][currentColumn].isMine()==false)
							matrix[currentRow][currentColumn].numMineNear++;
					}
					catch(IllegalArgumentException e) {/* DO Nothing */}
				}
			matrix[row][column].numMineNear=0;
		}
	}
	
	/**
	 * Ci permette di generare le mine nel campo.
	 * Questa funzione viene invocata quando si preme per scoprire la prima cella.
	 * Non serve richiamarla nel costruttore altrimenti rigenerebbe di nuovo.
	 * Verrà implementate nelle due sottoclassi FieldSafe e FieldNotSafe.
	 * 
	 */
	protected abstract void buildMatrixWithMines(int row, int column);
	
	private void buildRawMatrix() {
		for(int row=0; row<maxRow; row++)
			for(int column=0; column<maxColumn; column++)
				matrix[row][column]= new Cell(false, 0);
	}
	
	private void uncoverCellNear(int row, int column) {
		for(int currentRow=row-1; currentRow<=row+1; currentRow++)
			for(int currentColumn=column-1; currentColumn<=column+1; currentColumn++)
				if(currentRow!=row || currentColumn!=column)
					try {
						uncoverCell(currentRow,currentColumn);
					}catch(IllegalArgumentException e) {/* DO NOTHING */}
	}

	private void checkCoordinates(int row, int column) {
		if(row<0 || column<0 || row>=maxRow || column>=maxColumn)
			throw new java.lang.IllegalArgumentException(StringToShow.ERROR_FIELD_COORDINATES);
	}
}
