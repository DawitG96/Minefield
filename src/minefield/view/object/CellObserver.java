package minefield.view.object;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import minefield.object.Cell;
import minefield.util.GameUtility;
import minefield.util.Status;

/**
 * La classe CellObserver permette di aggiornare una cella
 * @author Dawit&Stefano
 *
 */
public class CellObserver implements Observer {
	JLabel label;
	
	public CellObserver(JLabel label) {
		this.label=label;
	}
	
	/**
	 * Permette di aggiornare una cella. Mette lo sfondo grigio o bianco a seconda se la cella e' coperta o no.
	 * Imposta l'icona della cella
	 */
	@Override
	public void update(Observable cell, Object arg1) {
		Cell c = (Cell)cell;
		Status status = c.getStatus();
		label.setIcon(GameUtility.getIconOfCell(status, c.numMineNear));
		if(status==Status.COVERED)
			label.setBackground(Color.GRAY);
		else
			label.setBackground(Color.WHITE);
		label.setPreferredSize(new Dimension(25, 25));
	}

}
