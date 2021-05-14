package minefield.game;

import minefield.view.HomeWindow;

/**
* La classe Graphic permette di iniziare una nuova partita facendo apparire il menu iniziale
* @author Dawit&Stefano
*
*/
public class Graphic {
	
	/**
	 * Questo metodo permette di creare una nuova HomeWindow e impostarla come visibile
	 */
	public static void start() {
		HomeWindow home = new HomeWindow();
		home.setVisible(true);
		
	}

}
