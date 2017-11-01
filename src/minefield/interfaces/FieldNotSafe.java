package minefield.interfaces;

public abstract class FieldNotSafe extends Field {
	
	protected FieldNotSafe(int row, int column, int mine) {
		super(row, column, mine);
	}
}
