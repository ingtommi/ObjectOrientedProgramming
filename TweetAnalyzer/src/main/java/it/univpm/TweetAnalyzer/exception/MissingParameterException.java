package it.univpm.TweetAnalyzer.exception;


public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public MissingParameterException() {
		super();
	}

	public MissingParameterException(String message) {
		super(message);
	}
}
