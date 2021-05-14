package minefield.exceptions;


public class MineTooHighException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MineTooHighException() {
		
	}

	public MineTooHighException(String message) {
		super(message);
	}

	public MineTooHighException(Throwable cause) {
		super(cause);
	}

	public MineTooHighException(String message, Throwable cause) {
		super(message, cause);
	}

	public MineTooHighException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
