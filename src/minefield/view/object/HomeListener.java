package minefield.view.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import minefield.view.HomeWindow;

/**
 * La classe HomeListener serve a controllare la HomeWindow e a seconda di quale bottone è premuto esegue un azione
 * @author Dawit&Stefano
 *
 */
public class HomeListener implements ActionListener {

	public HomeWindow window;
	
	
	public HomeListener(HomeWindow window) {
		this.window=window;
	}
	
	/**
	 * Permette di vedere quale bottone è stato premuto. Se è un JRadioButton si aggiorna la finestra del menu',
	 * invece se e' il bottone Start crea la nuova finestra
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object button = event.getSource();
		
		if(button.getClass() == JRadioButton.class)
			window.updateWindow();
		else
			window.pressStartButton();
	}

}
