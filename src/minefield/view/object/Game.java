package minefield.view.object;

import minefield.object.Field;

/**
 * Classe in cui si inizializzano il numero di bandierine assegnabili alle celle e il campo generato 
 * @author Dawit&Stefano
 *
 */
public class Game {
	public int flag;
	public Field field;
	
	public Game(Field field) {
		flag=field.numberOfMines;
		this.field=field;
		
	}
	
	/**
	 * Mantiene aggiornato il numero di bandierine ogni volta che si imposta una cella in stato "pericolosa"
	 * o quando quest'ultimo viene rimosso
	 * @return
	 */
	public int updateNumFlags() {
		flag = field.numberOfMines-field.getNumDangerousCell();
		return flag;
	}
}
