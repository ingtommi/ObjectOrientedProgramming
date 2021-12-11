package it.univpm.TweetAnalyzer.exception;

public class WrongMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongMethodException() {
		super();
	}

	//ritorna messaggio di errore
	public String getErr() {
		return "ERRORE: Metodo inserito errato!";
	}
}
