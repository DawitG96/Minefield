package minefield.game;

import minefield.util.StringToShow;


/**
* La classe Main permette di iniziare il gioco o tramite la console o per mezzo dell'interfaccia grafica
* @author Dawit&Stefano
*
*/
public class Main {
	/**
	 * Se si passa come argomento "console" o "-nogui" imposta "startConsole=true" e permette di iniziare il gioco tramite console
	 * @param args stringa passata come argomento
	 */
	public static void main(String[] args) {		
		boolean startConsole = false;
		for(String argument: args) {
			if(argument.matches(StringToShow.ARGUMENT_CONSOLE))
				startConsole=true;
		}
		
		if(startConsole)
			Console.start(args);
		else
			Graphic.start();
	}	
	
}
