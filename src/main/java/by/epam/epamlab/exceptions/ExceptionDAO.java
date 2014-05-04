package by.epam.epamlab.exceptions;

public class ExceptionDAO extends Exception {

	private static final long serialVersionUID = 201404301835L;

	public ExceptionDAO() {
		super();
	}

	public ExceptionDAO(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionDAO(String message) {
		super(message);
	}

}
