package minefield.view.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import minefield.util.StringToShow;
import minefield.view.EndWindow;
import minefield.view.GameWindow;
import minefield.view.HomeWindow;

/**
 * La classe EndListener permette di controllare la EndWindow.
 * A seconda del bottone premuto esegue un azione
 * @author Dawit&Stefano
 *
 */
public class EndListener implements ActionListener {

	public EndWindow end;
	public GameWindow game;
	public HomeWindow home;
	
	public EndListener(HomeWindow home, GameWindow game, EndWindow end) {
		this.home = home;
		this.game = game;
		this.end = end;
	}
	
	/**
	 * Permette di vedere quale bottone è stato premuto e di fare la giusta azione:
	 * MENU torna al menu iniziale
	 * RIPROVA ricomincia la partita con la stessa difficolta'
	 * ESCI chiude il gioco 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		String name = button.getText();
		
		if(name.matches(StringToShow.QUIT_BUTTON))
		{
			System.exit(0);
		}
		else if(name.matches(StringToShow.RETRY_BUTTON))
		{
			game.setVisible(false);
			home.pressStartButton();
		}
		else if(name.matches(StringToShow.MENU_BUTTON))
		{
			game.setVisible(false);
			home = new HomeWindow();
			home.setVisible(true);
			
		}
		end.setVisible(false);
	}

}
