package minefield.util;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
* La calsse GameIcons contiene le immagini da mostrare
* @author Dawit&Stefano
*
*/
public class GameIcons {
	
	public static final ImageIcon FLAG = createImageIcon("resources/flag.png");
	
	public static final ImageIcon BOMB = createImageIcon("resources/bomb.png");
	
	public static final ImageIcon ONE = createImageIcon("resources/one.png");
	public static final ImageIcon TWO = createImageIcon("resources/two.png");
	public static final ImageIcon THREE = createImageIcon("resources/three.png");
	public static final ImageIcon FOUR = createImageIcon("resources/four.png");
	public static final ImageIcon FIVE = createImageIcon("resources/five.png");
	public static final ImageIcon SIX = createImageIcon("resources/six.png");
	public static final ImageIcon SEVEN = createImageIcon("resources/seven.png");
	public static final ImageIcon EIGHT = createImageIcon("resources/eight.png");
	
	/**
	 * Sceglie la gusta immagine per la cella che non e' una mina
	 * @param numMineNear Il numero di mine intorno a quella cella
	 * @return Il percorso al corretta foto
	 */
	public static Icon chooseImageIcon(int numMineNear) {
		switch(numMineNear) {
		case 0: 
			return null;
		case 1:
			return GameIcons.ONE;
		case 2:
			return GameIcons.TWO;
		case 3:
			return GameIcons.THREE;
		case 4:
			return GameIcons.FOUR;
		case 5:
			return GameIcons.FIVE;
		case 6:
			return GameIcons.SIX;
		case 7:
			return GameIcons.SEVEN;
		case 8:
			return GameIcons.EIGHT;
		}
		return null;	
	}
	
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ClassLoader.getSystemResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} 
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
