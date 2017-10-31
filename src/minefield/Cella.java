package minefield;

/**
 * Classe cella che contiene gli stati
 * @author Dawit&Stefano
 *
 */
public class Cella 
{
	//sono tipi primitivi
	private final boolean mina;
	private final int numMineIntorno;
	
	private boolean coperta;
	private boolean pericolo;
	
	/**
	 * Il costruttore ci permette di creare la Cella
	 * @param mina Indica se e' una mina
	 * @param numeroMineIntorno Indica quante mine ha attorno
	 */
	public Cella(boolean mina, int numeroMineIntorno)
	{
		coperta=true;
		pericolo=false;
		this.mina=mina;
		this.numMineIntorno=numeroMineIntorno;
	}
	
	/**
	 * Indica se e' una mina o meno
	 * @return Ritorna vero se e' una mina
	 */
	public boolean isMine()
	{
		return mina;
	}
	
	/**
	 * Indica il numero di mine attorno
	 * @return Ritorna il numero
	 */
	public int numeroMineIntorno()
	{
		return numMineIntorno;
	}
	
	/**
	 * Indica se e' coperta o meno
	 * @return Ritorna vero se e' coperta
	 */
	public boolean essereCoperta()
	{
		return coperta;
	}
	
	/**
	 * Indica se e' pericolosa
	 * @return Ritorna vero se e' pericolosa
	 */
	public boolean esserePericolosa() 
	{
		return pericolo;
	}
	
	/**
	 * Toglie lo stato di pericolo 
	 * @return Ritorna vero se e' stato tolto lo stato di pericolo
	 */
	public boolean toglierePericolosa()
	{
		if(pericolo && coperta)
		{
			pericolo=false;
			return true;
		}
		return false;
	}
	
	/**
	 * Mette lo stato di pericolo
	 * @return Ritorna vero se e' stato posto il pericolo
	 */
	public boolean metterePericolosa()
	{
		if(!pericolo && coperta)
		{
			pericolo=true;
			return true;
		}
		return false;
	}
	
	/**
	 * Indica se è stata scoperta una cella
	 * @return Ritorna vero se e'avvenuto con successo
	 */
	public boolean scopriCella()
	{
		if(coperta && !pericolo)
		{
			coperta=false;
			return true;
		}
		return false;
	}
	
}
