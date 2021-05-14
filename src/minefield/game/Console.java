package minefield.game;

import jbook.util.Input;
import minefield.object.Field;
import minefield.util.Difficulty;
import minefield.util.GameUtility;
import minefield.util.StringToShow;

/**
* La classe Console permette di giocare per mezzo della console.
* L'utente potrà interagire con la console per impostare la difficolta' e per scoprire le celle.
* @author Dawit&Stefano
*
*/
public class Console {
	
	/**
	 * Permette l'interazione tra utente e console. 
	 * L'utente può scegliere il livello di difficoltà e se partire con il safeStart.
	 * Poi puo' iniziare a scoprire o impostare pericolosa una cella inserendo la riga e la colonna.
	 * Alla fine riceverà un messaggio a seconda se vince o perde.
	 * Può impostare la difficolta' e il safeStart attraverso una serie di argomenti passati.
	 * @param args Gli argomenti passati
	 */
	public static void start(String[] args) {
		System.out.println(StringToShow.GAME_START);
		
		Boolean sicuro = getSafeFromArgument(args);
		if(sicuro==null)
			sicuro=stringInteraction(StringToShow.QUESTION_SAFE+"(s/n)", "(s|S|N|n)").matches(("(s|S)"));
		
		Difficulty difficolta = getDifficultyFromArgument(args);
		if(difficolta==null)
			difficolta=difficultyChoosingInteraction();
		
		Field field = fieldChoosingInteraction(sicuro, difficolta);
		
		boolean hasLost=false;
		boolean hasWin=false;
		
		do {
			System.out.println(GameUtility.viewString(field));
			
			int row= integerInteraction(0, StringToShow.INSERT_ROW);
			int column= integerInteraction(0, StringToShow.INSERT_COLUMN);
			
			if(stringInteraction(StringToShow.QUESTION_UNCOVER_CELL + "(s/p)", "(s|S|P|p)").matches("(p|P)")) {

				if(field.isCellDangerous(row, column))
					field.unSetDangerousCell(row, column);
				else
					field.setDangerousCell(row, column);
			}
			else
				field.uncoverCell(row, column);
			
			hasLost=GameUtility.hasLost(field, row, column);
			hasWin=GameUtility.hasWon(field);
		}while(hasWin == false && hasLost == false);
		
		GameUtility.uncoverAllField(field);
		System.out.println(GameUtility.viewString(field));
		
		if(hasWin)
			System.out.println(StringToShow.GAME_END_WIN);
		else
			System.out.println(StringToShow.GAME_END_LOST);
	}

	private static Field fieldChoosingInteraction(boolean safe, Difficulty diff) {
		try {
			return GameUtility.buildNewField(safe, diff);
		}
		catch (IllegalArgumentException d) {
			System.out.println(StringToShow.CUSTOM_LEVEL);
			
			int	column=integerInteraction(1, StringToShow.QUESTION_COLUMN);
			int row=integerInteraction(1, StringToShow.QUESTION_ROW);
			int mine=0;
			Field campo = null;
			
			do {
				mine = integerInteraction(1, StringToShow.QUESTION_MINE);
				
				try {
					campo = GameUtility.buildNewField(safe, row, column, mine);
				}
				catch(minefield.exceptions.MineTooHighException e) {System.out.println(e.getMessage());}
				
			}while(campo==null);
			
			return campo;
		}
	}
	
	private static Difficulty difficultyChoosingInteraction() {
		int difficulty = 0;
		
		do {
			try {
				System.out.println(StringToShow.QUESTION_DIFFICULTY);
				System.out.println("1 - "+StringToShow.DIFFICULTY_EASY);
				System.out.println("2 - "+StringToShow.DIFFICULTY_MEDIUM);
				System.out.println("3 - "+StringToShow.DIFFICULTY_HARD);
				System.out.println("4 - "+StringToShow.DIFFICULTY_CUSTOM);
				
				difficulty=Input.readInt();
			}
			catch(NumberFormatException e) {System.out.println(StringToShow.INSERT_CORRECT_NUMBER);}
			catch(IllegalArgumentException e) {System.out.println(StringToShow.INSERT_CORRECT_NUMBER);}
			
		}while(difficulty<1 || difficulty>4);
		
		switch(difficulty) {
		case 1:
			return Difficulty.EASY;
		case 2:
			return Difficulty.MEDIUM;
		case 3:
			return Difficulty.HARD;
		case 4:
			return Difficulty.CUSTOM;
		default:
			return null;
		}
	}

	private static int integerInteraction(int lowLimit, String stringToShow) {
		int var=lowLimit-1;
		
		do {
			try {
				var = Input.readInt(stringToShow);
				if(var<lowLimit)
					System.out.println(StringToShow.INT_BIGGER_THAN + lowLimit);
			}
			catch(NumberFormatException e) {System.out.println(StringToShow.INSERT_INT);}
			catch(IllegalArgumentException e) {System.out.println(StringToShow.INSERT_INT);}
			
		}while(var<lowLimit);
		
		return var;
	}
	
	private static String stringInteraction(String stringToShow, String restrictionRegex) {
		String returnString = new String();
		
		do {
			returnString=Input.readString(stringToShow);
		} while(!returnString.matches(restrictionRegex));
		
		return returnString;
	}
	
	private static Boolean getSafeFromArgument(String[] args) {
		for(String argument : args) {
			if(argument.matches(StringToShow.ARGUMENT_SAFE))
				return true;
			else if(argument.matches(StringToShow.ARGUMENT_NO_SAFE))
				return false;
		}
		
		return null;
	}
	
	private static Difficulty getDifficultyFromArgument(String[] args) {
		for(String argument : args) {
			if(argument.matches(StringToShow.ARGUMENT_EASY))
				return Difficulty.EASY;
			else if(argument.matches(StringToShow.ARGUMENT_MEDIUM))
					return Difficulty.MEDIUM;
			else if(argument.matches(StringToShow.ARGUMENT_HARD))
				return Difficulty.HARD;
			else if(argument.matches(StringToShow.ARGUMENT_CUSTOM))
				return Difficulty.CUSTOM;
		}
		return null;
	}
}
