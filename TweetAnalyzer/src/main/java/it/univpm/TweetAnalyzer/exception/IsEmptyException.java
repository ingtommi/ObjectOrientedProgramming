package it.univpm.TweetAnalyzer.exception;

public class IsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsEmptyException() {
		super();
	}

	//ritorna messaggio di errore
	public String getErr() {
		return "ERRORE: Nessun hashtag trovato!";
	}
}
