package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
 * vengono lanciate altre rotte prima di salvare i dati
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