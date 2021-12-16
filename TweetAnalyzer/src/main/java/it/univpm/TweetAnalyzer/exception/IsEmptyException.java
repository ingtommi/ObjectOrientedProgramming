package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
 * non viene trovato nessun tweet contenente gli hashtag selezionati
 *  
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 * 
 */

public class IsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore di default
	 */
	public IsEmptyException() {
		super();
	}

	/**
	 * @param message indica il messaggio di errore
	 */
	public IsEmptyException(String message) {
		super(message);
	}
}