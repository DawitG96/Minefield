package minefield.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minefield.object.Field;
import minefield.util.GameUtility;
import minefield.util.StringToShow;
import minefield.view.object.Game;
import minefield.view.object.GameListener;

/*
 * ==================
 * =	<Flag>		=
 * ==================
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * =[][][][][][][][]=
 * ==================
 */

/**
 * La classe GameWindow permette di creare la finestra di gioco rappresentando graficamente il campo
 * @author Dawit&Stefano
 *
 */
public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private HomeWindow homeWindow;
	private Game game;
	private GameListener listener;
	private JLabel flagLabel;
	
	/**
	 * Costruttore per creare la finestra dove si gioca
	 * @param field Il campo
	 * @param homeWindow La finestra del menu iniziale
	 */
	public GameWindow(Field field, HomeWindow homeWindow) {
		game = new Game(field);
		listener = new GameListener(this);
		this.homeWindow = homeWindow;
		
		JPanel infoPanel = new JPanel();
		flagLabel = new JLabel(StringToShow.GAME_FLAG+game.flag);
 
		JPanel gridPanel = GameUtility.viewJPanel(field, listener);
		int widthGridPanel = gridPanel.getSize().width;
		int heightGridPanel = gridPanel.getSize().height;
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(flagLabel);
		infoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		int heightInfoPanel = infoPanel.getSize().height;
		
		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(infoPanel);
		c.add(gridPanel);
		
		Dimension dim = new Dimension(widthGridPanel, heightGridPanel+heightInfoPanel+100);
		this.setPreferredSize(dim);
		this.setSize(dim);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(StringToShow.GAME_TITLE);
		
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimScreen.width - dim.width);
		int y = (dimScreen.height - dim.height);
		setLocation(x/2, y/2);
		
	}
	
	/**
	 * Metodo che serve al listener per aggiornare il numero di bandierine
	 */
	public void updateFlag() {
		flagLabel.setText(StringToShow.GAME_FLAG+game.updateNumFlags());
	}
	
	/**
	 * Restituisce il campo
	 * @return Il campo
	 */
	public Field getField() {
		return game.field;
	}

	/**
	 * Permette di scoprire una cella, e se si vince o perde viene mostrato un messaggio
	 * @param row La riga del campo
	 * @param column La colonna del campo
	 */
	public void uncoverCellWindow(int row, int column) {
		game.field.uncoverCell(row, column);
		if(GameUtility.hasLost(getField())) {
			GameUtility.uncoverAllField(getField());
			EndWindow end = new EndWindow(this, homeWindow);
			end.setTextLabel(StringToShow.GAME_END_LOST);
		}
		if(GameUtility.hasWon(getField())) {
			EndWindow end = new EndWindow(this, homeWindow);
			end.setTextLabel(StringToShow.GAME_END_WIN);
		}
	}

	/**
	 * Permette di mettere/togliere lo stato Dangerous a una cella
	 * @param row La riga del campo
	 * @param column La colonna del campo
	 */
	public void dangerousCellWindow(int row, int column) {
		if(game.field.setDangerousCell(row, column) == false)
			game.field.unSetDangerousCell(row, column);
		
		updateFlag();
		if(GameUtility.hasWon(getField()))
			flagLabel.setText(StringToShow.GAME_END_WIN);
		
	}
}
