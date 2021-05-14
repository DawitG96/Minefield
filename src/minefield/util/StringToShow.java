package minefield.util;

/**
 * La classe StringToShow permette di raggruppare tutte le costanto.
 * In questo modo se si volesse modificare una costante basta cambiarla qui una sola volta e non tutte le volte che appare
 * @author Dawit&Stefano
 *
 */
public class StringToShow {
	public static final String ARGUMENT_CONSOLE = "-console|-nogui";
	public static final String ARGUMENT_SAFE = "-safe";
	public static final String ARGUMENT_NO_SAFE = "-nosafe";
	public static final String ARGUMENT_EASY = "-easy";
	public static final String ARGUMENT_MEDIUM = "-medium";
	public static final String ARGUMENT_HARD = "-hard";
	public static final String ARGUMENT_CUSTOM = "-custom";
	
	public static final String GAME_TITLE = "CampoMinato";
	public static final String GAME_START = "Inizio partita!";
	public static final String GAME_END_WIN = "Hai Vinto!";
	public static final String GAME_END_LOST = "Hai Perso!";
	public static final String GAME_FLAG = "Bandierine: ";
	
	public static final String QUESTION_SAFE = "Vuoi iniziare con il SafeStart? ";
	public static final String QUESTION_DIFFICULTY = "Quale difficolta' desideri? ";
	public static final String QUESTION_ROW = "Inserisci quante righe vuoi: ";
	public static final String QUESTION_COLUMN = "Inserisci quante colonne vuoi: ";
	public static final String QUESTION_MINE = "Inserisci quante mine vuoi: ";
	public static final String QUESTION_UNCOVER_CELL = "Vuoi scoprire o mettere/togliere la cella in stato pericoloso?";
	
	public static final String INSERT_ROW = "Inserisci la riga: ";
	public static final String INSERT_COLUMN = "Inserisci la colonna: ";
	public static final String INSERT_CORRECT_NUMBER = "Inserire un valore corretto: 1, 2, 3 o 4";
	public static final String INSERT_INT = "Deve essere un intero!";
	
	public static final String INT_BIGGER_THAN = "Inserisci un intero maggiore di ";
	
	public static final String DIFFICULTY_EASY = "Principiante (9*9 con 10 mine)";
	public static final String DIFFICULTY_MEDIUM = "Intermedio (16*16 con 40 mine)";
	public static final String DIFFICULTY_HARD = "Esperto (16*30 con 99 mine)";
	public static final String DIFFICULTY_CUSTOM = "Personalizzato, scegli te!";
	
	public static final String CUSTOM_LEVEL = "Livello Personalizzato";
	
	public static final String MENU_BUTTON = "MENU";
	public static final String QUIT_BUTTON = "ESCI";
	public static final String RETRY_BUTTON = "RIPROVA";
	public static final String START_BUTTON = "START";
	
	public static final String ROW = "Righe: ";
	public static final String COLUMN = "Colonne: ";
	public static final String MINE = "Mine: ";
	
	public static final String SAFE_YES = "Sì";
	public static final String SAFE_NO = "No";
	
	public static final String CELL_SYMBOL_FOR_DANGEROUS = "[P]";
	public static final String CELL_SYMBOL_FOR_EXPLODED = "[X]";
	public static final String CELL_SYMBOL_FOR_COVERED = "[-]";
	public static final String CELL_SYMBOL_FOR_EMPTY = "[ ]";
	
	public static final String ERROR_CELL_NEAR_TOO_LOW = "Il numero di mine intorno deve essere maggiore o uguale a 0!";
	public static final String ERROR_CELL_NEAR_TOO_HIGH = "Il numero di mine intorno deve essere minore o uguale a 8!";
	
	public static final String ERROR_FIELD_MINE_TOO_LOW = "Nel campo ci deve essere almeno una mina!";
	public static final String ERROR_FIELD_MINE_TOO_HIGH = "Le mine devono essere minori della somma delle celle!";
	public static final String ERROR_FIELD_COLUMN = "La grandezza della colonna del campo deve essere maggiore di zero e minore o uguale a 50!";
	public static final String ERROR_FIELD_ROW = "La grandezza della riga del campo deve essere maggiore di zero e minore o uguale a 30!";
	public static final String ERROR_FIELD_COORDINATES = "Le coordinate devono essere positive o minori delle rispettive dimensioni!";
	public static final String ERROR_FIELDSAFE_MINE_TOO_HIGH = "Le mine devono essere minori della somma delle celle-9!";
}
