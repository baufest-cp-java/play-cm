package exceptions;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9184234891416097610L;

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
