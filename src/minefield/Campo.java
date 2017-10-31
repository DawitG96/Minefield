package minefield;

/**
 * La classe Campo definisci le funzioni di base per gestire il campo.
 * Questi verrà poi generato nelle due sottoclassi
 * @author Dawit&Stefano
 *
 */
public abstract class Campo 
{
	protected final Cella[][] matrice;
	public final int numMine;
	public final int righe;
	public final int colonne;
	
	protected int numCellePericolose;
	protected int numCelleCoperte;
	
	/**
	 * Il costruttore ci permette di creare il Campo
	 * @param lunghezza La lunghezza del campo
	 * @param altezza L'altezza del campo
	 * @param mine Il numero di mine nel campo
	 * @throws IllegalArgumentException Input delle dimensioni errate
	 * @throws IllegalArgumentException Numero mine errato
	 */
	protected Campo(int lunghezza, int altezza, int mine)
	{
		if(lunghezza<=0 || altezza<=0)
			throw new java.lang.IllegalArgumentException("Le grandezze del campo devono essere maggiori di zero!");
		if(lunghezza>=100 || altezza>=100)
			throw new java.lang.IllegalArgumentException("Le grandezze del campo devono essere minori di cento!");
		if(mine<=0)
			throw new java.lang.IllegalArgumentException("Deve esserci almeno una mina!");
		if(mine>=lunghezza*altezza)
			throw new java.lang.IllegalArgumentException("Le mine devono essere minori delle celle!");
		
		matrice = new Cella[lunghezza][altezza];
		numCellePericolose=0;
		numCelleCoperte=altezza*lunghezza;
		this.righe=lunghezza;
		this.colonne=altezza;
		this.numMine=mine;
		
		generaMatrice();
	}
	
	/**
	 * Indica se e' esplosa una mina
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se la mina e' esplosa, altrimenti false
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean minaEsplosa(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		if(matrice[x][y].isMine() && !matrice[x][y].essereCoperta())
			return true;
		return false;
	}
	
	/**
	 * Indica il numero di celle pericolose
	 * @return il numero
	 */
	public int numeroCellePericolose()
	{
		return numCellePericolose;
	}
	
	/**
	 * Ci permette di aggiungere una cella pericolosa
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se e' stata aggiunta la cella pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean aggiungiCellaPericolose(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		if(matrice[x][y].metterePericolosa())
		{
			numCellePericolose++;
			return true;
		}
		return false;
	}
	
	/**
	 * Ci permette di rimuovere una cella pericolosa
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se e' stata rimossa la cella pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean rimuoviCellaPericolose(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		if(matrice[x][y].toglierePericolosa())
		{
			numCellePericolose--;
			return true;
		}
		return false;
	}
	
	/**
	 * Indica il numero di celle coperte restanti
	 * @return il numero
	 */
	public int numeroCelleCoperte()
	{
		return numCelleCoperte;
	}
	
	/**
	 * Ci permette di scoprire una data cella
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se e' stata scoperta con successo, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean scopriCella(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		if(numCelleCoperte==righe*colonne)
		{
			generaMine(x,y);
		}
		
		if(matrice[x][y].scopriCella())
		{
			numCelleCoperte--;
			if(matrice[x][y].numeroMineIntorno() == 0)
				scopriCelleVicine(x, y);
			return true;
		}
		return false;
	}
	
	/**
	 * Indica il numero di mine intorno a una data cella
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return il numero
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public int numeroMineIntorno(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		return matrice[x][y].numeroMineIntorno();
	}

	/**
	 * Indica se la cella e' pericolosa
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se la cella e' pericolosa, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean cellaPericolosa(int x, int y)
	{
		convalidaCoordinate(x, y);
		return matrice[x][y].esserePericolosa();
	}
	
	/**
	 * Indica se la cella e' coperta
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se la cella e' coperta, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean cellaCoperta(int x, int y)
	{
		convalidaCoordinate(x, y);
		return matrice[x][y].essereCoperta();
	}
	
	/**
	 * Indica se la mina
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 * @return true se c'e' la mina, false altrimenti
	 * @throws IllegalArgumentException Input coordinate sbagliate
	 */
	public boolean essereMina(int x, int y)
	{
		convalidaCoordinate(x, y);
		if(!matrice[x][y].essereCoperta())
			return matrice[x][y].isMine();
		return false;
	}
	
	/**
	 * Consente di aggiornare il numero di mine nelle celle attorno alla mina presente nella cella selezionata
	 * @param x La coordinata x della matrice
	 * @param y La coordinata y della matrice
	 */
	protected void aggiornaNumeroMineIntornoAllaMina(int x, int y)
	{
		convalidaCoordinate(x, y);
		
		if(matrice[x][y].isMine())
			for(int i=x-1; i<=x+1; i++)
				for(int j=y-1; j<=y+1; j++)
				{
					try{
						convalidaCoordinate(i,j);
						if(matrice[i][j].isMine()==false)
							matrice[i][j] = new Cella(false, matrice[i][j].numeroMineIntorno()+1);
					}
					catch(IllegalArgumentException e) {/* DO Nothing */}
				}
	}
	
	/**
	 * Ci permette di generare le mine nel campo.
	 * Questa funzione viene invocata quando si preme per scoprire la prima cella.
	 * Non serve richiamarla nel costruttore sennò rigenerebbe di nuovo-
	 */
	protected abstract void generaMine(int x, int y);
	
	private void generaMatrice()
	{
		for(int x=0; x<righe; x++)
			for(int y=0; y<colonne; y++)
				matrice[x][y]= new Cella(false, 0);
	}
	
	private void scopriCelleVicine(int x, int y)
	{
		for(int i=x-1; i<=x+1; i++)
			for(int j=y-1; j<=y+1; j++)
				if(i!=x || j!=y)
					try {
						scopriCella(i,j);
					}catch(IllegalArgumentException e) {/* DO NOTHING */}
	}

	private void convalidaCoordinate(int x, int y)
	{
		if(x<0 || y<0 || x>=righe || y>=colonne)
			throw new java.lang.IllegalArgumentException("Le coordinate devono essere positive o minori delle rispettive dimensioni");
	}
	
}
