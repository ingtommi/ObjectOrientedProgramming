package it.univpm.TweetAnalyzer.exception;


public class MissingCallException extends Exception{

	private static final long serialVersionUID = 1L;

	public MissingCallException() {
		super();
	}

	public MissingCallException(String message) {
		super(message);
	}
}