package minefield.exceptions;

public class MineTooLowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MineTooLowException() {
		
	}

	public MineTooLowException(String message) {
		super(message);
	}

	public MineTooLowException(Throwable cause) {
		super(cause);
	}

	public MineTooLowException(String message, Throwable cause) {
		super(message, cause);
	}

	public MineTooLowException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
