package minefield.exceptions;

public class ColumnException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ColumnException() {
		
	}

	public ColumnException(String message) {
		super(message);
	}

	public ColumnException(Throwable cause) {
		super(cause);
	}

	public ColumnException(String message, Throwable cause) {
		super(message, cause);
	}

	public ColumnException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
