package minefield.obj;

public class Cell {
	
	public Cell(boolean isMine, int minMineNear)
	{}
	
	public boolean isMine(){
		return false;
	}
	public int getNumMineNear() {
		return 0;
	}
	public boolean isCovered() {
		return false;
	}
	public boolean isDangerous() {
		return false;
	}
	public boolean setDangerous() {
		return false;
	}
	public boolean uncover() {
		return false;
	}
	
	public boolean unSetDangerous() {
		return false;
	}
}
