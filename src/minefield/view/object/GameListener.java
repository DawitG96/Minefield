package minefield.view.object;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import minefield.util.GameUtility;
import minefield.view.GameWindow;

/**
 * La classe GameListener serve per controllare la GameWindow. 
 * A seconda del tasto e cella cliccato si fanno delle azioni.
 * @author Dawit&Stefano
 *
 */
public class GameListener implements MouseListener {
	
	public GameWindow window;
	
	public GameListener(GameWindow window) {
		this.window=window;
	}

	/**
	 * Ci permette di fare delle operazioni a seconda del tasto e della cella cliccata.
	 * Se e' click si sinistro si scopre la cella, se è destro si mette/toglie lo stato Dangerous
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(GameUtility.hasLost(window.getField())==false && GameUtility.hasWon(window.getField())==false) {
			
			String[] coordinates = ((JLabel)e.getSource()).getName().split("-");
			int row = Integer.parseInt(coordinates[0]);
			int column = Integer.parseInt(coordinates[1]);
			
			if(e.getButton()==MouseEvent.BUTTON1)
				window.uncoverCellWindow(row, column);
			if(e.getButton()==MouseEvent.BUTTON3)
				window.dangerousCellWindow(row, column);
		}	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	

}
