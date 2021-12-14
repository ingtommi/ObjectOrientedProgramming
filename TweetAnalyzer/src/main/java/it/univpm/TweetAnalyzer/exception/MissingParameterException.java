package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
 * non sono stati inseriti dei parametri obbligatori
 *  
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 * 
 */

public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Questo è il costruttore di default
	 */
	public MissingParameterException() {
		super();
	}

	/**
	 * @param message sta ad indicare il messaggio di errore
	 */
	public MissingParameterException(String message) {
		super(message);
	}
}