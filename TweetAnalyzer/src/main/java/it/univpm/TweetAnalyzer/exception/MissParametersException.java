package it.univpm.TweetAnalyzer.exception;

public class MissParametersException extends Exception {

	private static final long serialVersionUID = 1L;

	public MissParametersException() {
		super();
	}

	//ritorna messaggio di errore
	public String getErr() {
		return "ERRORE: Alcuni paramentri non sono presenti!";
	}
}
