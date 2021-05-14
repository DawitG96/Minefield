package minefield.object;

/**
 * Classe per generare un campo non sicuro
 * @author Dawit&Stefano
 *
 */
public class FieldNotSafe extends Field {
	
	/**
	 * Costruttore della Classe CampoNon Sicuro
	 * @param lunghezza La lunghezza della matrice
	 * @param altezza L'altezza della matrice
	 * @param mine	Il numero di mine
	 * @throws IllegalArgumentException Input delle dimensioni errate
	 * @throws IllegalArgumentException Numero mine errato
	 */
	public FieldNotSafe(int row, int column, int mine) {
		super(row, column, mine);
	}
	
	@Override
	/**
	 * Permette di generare le mine. Si puo' trovare anche quando si scopre una cella per la prima volta
	 */
	protected void buildMatrixWithMines(int row, int column) {
		for(int minePlaced=0; minePlaced<super.numberOfMines; minePlaced++) {
			int randRow= (int) (Math.random()*super.maxRow);
			int randColumn= (int) (Math.random()*super.maxColumn);
			
			if(matrix[randRow][randColumn].isMine())
				minePlaced--;
			else {
				matrix[randRow][randColumn].setMine();
				super.updateNeighbourMineNumber(randRow, randColumn);
			}
		}
	}
}
