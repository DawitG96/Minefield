package minefield;

public class Field {
	
	public final int NUMBER_OF_MINES;
	public final int MAX_ROW;
	public final int MAX_COLUMN;
	
	public Field(int row, int column, int mine)
	{
		NUMBER_OF_MINES = mine;
		MAX_COLUMN = column;
		MAX_ROW = row;
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
