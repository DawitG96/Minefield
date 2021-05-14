package minefield.object;

import java.util.Observable;

import minefield.util.Status;
import minefield.util.StringToShow;

/**
 * Classe Cell che contiene gli stati
 * @author Dawit&Stefano
 *
 */
public class Cell extends Observable {
	
	public int numMineNear;
	private boolean isMine;
	
	private boolean isCovered;
	private boolean isDangerous;
	
	/**
	 * Il costruttore ci permette di creare la Cella
	 * @param isMine Indica se e' una mina
	 * @param numMineNear Indica quante mine ha attorno
	 * @throws IllegalArgumentException Errore nel numero di mine intorno ad una cella
	 */
	public Cell(boolean isMine, int numMineNear) {
		if(numMineNear <0)
			throw new java.lang.IllegalArgumentException(StringToShow.ERROR_CELL_NEAR_TOO_LOW);
		if(numMineNear >8)
			throw new java.lang.IllegalArgumentException(StringToShow.ERROR_CELL_NEAR_TOO_HIGH);

		isCovered=true;
		isDangerous=false;
		this.isMine=isMine;
		this.numMineNear=numMineNear;
	}
	
	/**
	 * Indica se e' una mina o meno
	 * @return Ritorna vero se e' una mina
	 */
	public boolean isMine() {
		return isMine;
	}
	
	/**
	 * Indica il numero di mine attorno
	 * @return Ritorna il numero
	 */
	public int getNumMineNear() {
		return numMineNear;
	}
	
	/**
	 * Mette una cella come mina
	 */
	public void setMine() {
		isMine=true;
	}
	
	/**
	 * Indica se e' coperta o meno
	 * @return Ritorna vero se e' coperta
	 */
	public boolean isCovered() {
		return isCovered;
	}
	
	/**
	 * Indica se e' pericolosa
	 * @return Ritorna vero se e' pericolosa
	 */
	public boolean isDangerous() {
		return isDangerous;
	}
	
	/**
	 * Mette lo stato di pericolo
	 * @return Ritorna vero se e' stato posto il pericolo
	 */
	public boolean setDangerous() {
		if(!isDangerous && isCovered) {
			isDangerous=true;
			
			this.setChanged();
			this.notifyObservers();
			this.clearChanged();
			return true;
		}
		return false;
	}
	
	/**
	 * Indica se e' stata scoperta una cella
	 * @return Ritorna vero se e' avvenuto con successo
	 */
	public boolean uncover() {
		if(isCovered && !isDangerous) {
			isCovered=false;
			
			this.setChanged();
			this.notifyObservers();
			this.clearChanged();
			return true;
		}
		return false;
	}
	
	/**
	 * Toglie lo stato di pericolo 
	 * @return Ritorna vero se e' stato tolto lo stato di pericolo
	 */
	public boolean unSetDangerous() {
		if(isDangerous && isCovered) {
			isDangerous=false;
			
			this.setChanged();
			this.notifyObservers();
			this.clearChanged();
			return true;
		}
		return false;
	}
	
	/**
	 * Restituisce lo stato 
	 * @return Lo stato Dangerous se e' pericolosa, Covered se e' coperta, Exploded se e' una mina, Uncovered altrimenti
	 */
	public Status getStatus() {
		if(isDangerous)
			return Status.DANGEROUS;
		if(isCovered)
			return Status.COVERED;
		if(isMine)
			return Status.EXPLODED;
		return Status.UNCOVERED;
	}
		
}
