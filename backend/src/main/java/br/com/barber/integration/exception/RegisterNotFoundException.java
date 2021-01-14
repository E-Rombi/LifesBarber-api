package br.com.barber.integration.exception;

public class RegisterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException() {
		super();
	}
	
	public RegisterNotFoundException(String message) {
		super(message);
	}
	
}
