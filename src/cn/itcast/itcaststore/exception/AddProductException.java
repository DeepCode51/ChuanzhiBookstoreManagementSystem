package cn.itcast.itcaststore.exception;

public class AddProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public AddProductException() {
		super();
	}

	public AddProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddProductException(String message) {
		super(message);
	}

	public AddProductException(Throwable cause) {
		super(cause);
	}

}
