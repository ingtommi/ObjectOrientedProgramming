package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
<<<<<<< HEAD
 * viene inviata una richiesta prima di quella get
=======
 * vengono lanciate altre rotte prima di salvare i dati
>>>>>>> eab88fb27b2ce4d10d22e18653893a6849ae95e5
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
	 * @param message indica il messaggio di errore
	 */
	public MissingCallException(String message) {
		super(message);
	}
}