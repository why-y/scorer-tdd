package ch.gry.scorer;

public class AlreadyTerminatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyTerminatedException() {
		super();
	}
	
	public AlreadyTerminatedException(String message) {
		super(message);
	}
		
}
