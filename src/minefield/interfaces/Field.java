package minefield.interfaces;

public abstract class Field {
	
	public final int numMine;
	public final int row;
	public final int column;
	
	protected Field(int row, int column, int mine)
	{
		this.numMine = mine;
		this.column = column;
		this.row = row;
	}
	
	abstract public boolean isMineExploded(int row, int column);
	abstract public int getNumDangerousCell();
	abstract public boolean setDangerousCell(int row, int column);
	abstract public boolean unSetDangerousCell(int row, int column);
	abstract public int getNumCoveredCell();
	abstract public boolean uncoverCell(int row, int column);
	abstract public int getNumMineNearCell(int row, int column);
	abstract public boolean isCellDangerous(int row, int column);
	abstract public boolean isCellCovered(int row, int column);
	abstract public boolean isCellMine(int row, int column);
}
