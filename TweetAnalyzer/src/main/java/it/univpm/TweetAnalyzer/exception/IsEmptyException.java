package it.univpm.TweetAnalyzer.exception;


public class IsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsEmptyException() {
		super();
	}

	public IsEmptyException(String message) {
		super(message);
	}
}