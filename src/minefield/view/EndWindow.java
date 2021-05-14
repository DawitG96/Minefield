package minefield.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minefield.util.StringToShow;
import minefield.view.object.EndListener;
/*
 * ==========================
 * =		Hai Vinto!   	=
 * ==========================
 * =   Menu  Riprova  Esci  =
 * ==========================
 */

/**
 * La classe EndWindow permette di creare una finestra con il menu finale.
 * L'utente può scegliere se uscire dal gioco, ricominciare la partita con le stesse condizioni o andare al menu iniziale
 * @author Dawit&Stefano
 *
 */
public class EndWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private EndListener listener;
	
	/**
	 * Costruttore per creare il menu finale quando si vince o perde la partita. 
	 * Ci sono tre bottoni:
	 * MENU per tornare al menu iniziale
	 * RIPROVA per ricominciare la partita con la stessa difficolta'
	 * ESCI per chiudere il gioco
	 * @param game La finestra di gioco
	 * @param homeWindow La finestra del menu iniziale
	 */
	public EndWindow(GameWindow game, HomeWindow homeWindow) {
		JPanel msgPanel = new JPanel();
		label = new JLabel();
		msgPanel.add(label);
		
		JPanel button = new JPanel();
		JButton menu = new JButton(StringToShow.MENU_BUTTON);
		JButton retry = new JButton(StringToShow.RETRY_BUTTON);
		JButton quit = new JButton(StringToShow.QUIT_BUTTON);
		button.add(menu);
		button.add(retry);
		button.add(quit);
		
		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(msgPanel);
		c.add(button);

		listener = new EndListener(homeWindow, game, this);
		menu.addActionListener(listener);
		retry.addActionListener(listener);
		quit.addActionListener(listener);
		
		Dimension dim = new Dimension(350, 150);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(StringToShow.GAME_TITLE);
		this.setVisible(true);
		this.setSize(dim);
		
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimScreen.width - dim.width);
		int y = (dimScreen.height - dim.height);
		setLocation(x/2, y/2);
	}
	
	/**
	 * Permette di modificare il testo sopra i bottoni
	 * @param text Il testo che verra' mostrato
	 */
	public void setTextLabel(String text) {
		label.setText(text);
	}
}
