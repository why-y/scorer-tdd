package ch.gry.scorer;

public class GameOverException extends Exception {

	private static final long serialVersionUID = 1L;

	public GameOverException() {
		super();
	}
	
	public GameOverException(String message) {
		super(message);
	}
		
}
