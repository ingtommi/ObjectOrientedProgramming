package it.univpm.TweetAnalyzer.exception;

public class MissingCallException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MissingCallException() {
		super();
	}

	//ritorna messaggio di errore
	public String getErr() {
		return "ERRORE: You have to contact http://localhost:8080/tweet/get/{method} ";
	}

}
