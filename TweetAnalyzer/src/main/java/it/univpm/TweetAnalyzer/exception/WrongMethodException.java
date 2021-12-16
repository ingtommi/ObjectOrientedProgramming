package it.univpm.TweetAnalyzer.exception;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando 
 * vengono generati metodi diversi rispetto ad AND o OR nella ricerca tramite gli hashtag
 *  
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 * 
 */

public class WrongMethodException extends Exception {

	private static final long serialVersionUID = 1L;

    /**
	 * Questo è il costruttore di default
	 */
	public WrongMethodException() {
		super();
	}

	/**
	 * @param message indica il messaggio di errore
	 */
	public WrongMethodException(String message) {
		super(message);
	}
}