package minefield.exceptions;

public class RowException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RowException() {
		
	}

	public RowException(String message) {
		super(message);
	}

	public RowException(Throwable cause) {
		super(cause);
	}

	public RowException(String message, Throwable cause) {
		super(message, cause);
	}

	public RowException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
