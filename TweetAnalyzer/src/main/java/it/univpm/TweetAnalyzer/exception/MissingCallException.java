package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
 * viene inviata la richiesta data prima di quella get
 *  
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 * 
 */

public class MissingCallException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Questo è il costruttore di default
	 */
	public MissingCallException() {
		super();
	}

	/**
	 * @param message sta ad indicare il messaggio di errore
	 */
	public MissingCallException(String message) {
		super(message);
	}
}