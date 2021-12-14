package it.univpm.TweetAnalyzer.exception;


public class WrongMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongMethodException() {
		super();
	}

	public WrongMethodException(String message) {
		super(message);
	}
}