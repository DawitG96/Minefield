package minefield.obj;

public class Field {
	
	public final int numMine;
	public final int row;
	public final int column;
	
	protected Field(int row, int column, int mine)
	{
		this.numMine = mine;
		this.column = column;
		this.row = row;
	}
	
	public boolean isMineExploded(int row, int column) {
		return false;
	}
	public int getNumDangerousCell() {
		return 0;
	}
	public boolean setDangerousCell(int row, int column) {
		return false;
	}
	public boolean unSetDangerousCell(int row, int column) {
		return false;
	}
	public int getNumCoveredCell() {
		return 0;
	}
	public boolean uncoverCell(int row, int column) {
		return false;
	}
	public int getNumMineNearCell(int row, int column) {
		return 0;
	}
	public boolean isCellDangerous(int row, int column) {
		return false;
	}
	public boolean isCellCovered(int row, int column) {
		return false;
	}
	public boolean isCellMine(int row, int column) {
		return false;
	}
}
