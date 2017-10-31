package minefield;

/**
 * Classe per generare un campo sicuro
 * @author Dawit&Stefano
 *
 */
public class CampoSicuro extends Campo 
{

	/**
	 * Il costruttore della classe CampoSicuro
	 * @param lunghezza La lunghezza della matrice
	 * @param altezza L'altezza della matrice
	 * @param mine Il numero di mine
	 * @throws IllegalArgumentException Input delle dimensioni errate
	 * @throws IllegalArgumentException Numero mine errato
	 */
	public CampoSicuro(int lunghezza, int altezza, int mine) 
	{
		super(lunghezza, altezza, mine);
		if(mine>lunghezza*altezza-9)
			throw new java.lang.IllegalArgumentException("Le mine devono essere minori o uguali al numero delle celle-9!");
	}

	@Override
	/**
	 * Permette di generare le mine. Non ci potranno essere mine nella cella in cui si preme la prima volta e in quelle intorno ad esse
	 */
	protected void generaMine(int x, int y)
	{
		for(int i=0; i<super.numMine; i++)
		{
			int lunghezza= (int) (Math.random()*super.righe);
			int altezza= (int) (Math.random()*super.colonne);
			
			if(matrice[lunghezza][altezza].isMine() || ( (lunghezza>=x-1 && lunghezza<=x+1) && (altezza>=y-1 && altezza <=y+1)))
				i--;
			else
			{
				matrice[lunghezza][altezza] = new Cella(true, 0);
				super.aggiornaNumeroMineIntornoAllaMina(lunghezza, altezza);
				
			}
		}
		
	}

}
