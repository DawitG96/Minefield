package minefield.interfaces;

public abstract class Cell {
	
	public Cell(boolean isMine, int minMineNear)
	{}
	
	abstract public boolean isMine();
	abstract public int minMineNear();
	abstract public boolean isCovered();
	abstract public boolean isDangerous();
	abstract public boolean setDangerous();
	abstract public boolean uncover();
}
