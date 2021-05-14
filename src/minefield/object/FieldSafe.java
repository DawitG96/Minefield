package minefield.object;

import minefield.exceptions.MineTooHighException;
import minefield.util.StringToShow;

/**
 * Classe per generare un campo sicuro
 * @author Dawit&Stefano
 *
 */
public class FieldSafe extends Field {
	
	/**
	 * Il costruttore della classe CampoSicuro
	 * @param lunghezza La lunghezza della matrice
	 * @param altezza L'altezza della matrice
	 * @param mine Il numero di mine
	 * @throws IllegalArgumentException Input delle dimensioni errate
	 * @throws IllegalArgumentException Numero mine errato
	 */
	public FieldSafe(int row, int column, int mine) {
		super(row, column, mine);
		if(mine>row*column-9)
			throw new MineTooHighException(StringToShow.ERROR_FIELDSAFE_MINE_TOO_HIGH);
	}
	
	@Override
	/**
	 * Permette di generare le mine. Non ci potranno essere mine nella cella in cui si preme la prima volta e in quelle intorno ad esse
	 */
	protected void buildMatrixWithMines(int row, int column) {
		for(int minePlaced=0; minePlaced<super.numberOfMines; minePlaced++) {
			int randRow= (int) (Math.random()*super.maxRow);
			int randColumn= (int) (Math.random()*super.maxColumn);
			
			if(matrix[randRow][randColumn].isMine() || ( (randRow>=row-1 && randRow<=row+1) && (randColumn>=column-1 && randColumn <=column+1)))
				minePlaced--;
			else {
				matrix[randRow][randColumn].setMine();
				super.updateNeighbourMineNumber(randRow, randColumn);
				
			}
		}
		
	}
}
