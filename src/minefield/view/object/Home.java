package minefield.view.object;

import minefield.util.Difficulty;

/**
 * Imposta i valori di default per quando si genera un campo 
 * @author Dawit&Stefano
 *
 */
public class Home {
	public Difficulty difficulty;
	public boolean safe;
	public int column;
	public int row;
	public int mine;
	
	public Home() {
		difficulty = Difficulty.EASY;
		column = 10;
		row = 10;
		mine = 10;
		safe = true;
	}
	
}
