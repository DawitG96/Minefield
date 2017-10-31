package minefield;
/**
 * Classe per generare un campo non sicuro
 * @author Dawit&Stefano
 *
 */
public class CampoNonSicuro extends Campo
{

	/**
	 * Costruttore della Classe CampoNon Sicuro
	 * @param lunghezza La lunghezza della matrice
	 * @param altezza L'altezza della matrice
	 * @param mine	Il numero di mine
	 * @throws IllegalArgumentException Input delle dimensioni errate
	 * @throws IllegalArgumentException Numero mine errato
	 */
	public CampoNonSicuro(int lunghezza, int altezza, int mine) 
	{
		super(lunghezza, altezza, mine);
		
	}

	
	@Override
	/**
	 * Permette di generare le mine. Si può trovare anche quando si scopre una cella per la prima volta
	 */
	protected void generaMine(int x, int y)
	{
		for(int i=0; i<super.numMine; i++)
		{
			int lunghezza= (int) (Math.random()*super.righe);
			int altezza= (int) (Math.random()*super.colonne);
			
			if(matrice[lunghezza][altezza].isMine())
				i--;
			else
			{
				matrice[lunghezza][altezza] = new Cella(true, 0);
				super.aggiornaNumeroMineIntornoAllaMina(lunghezza, altezza);
				
			}
		}
	}
	
}
