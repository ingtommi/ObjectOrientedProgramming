package it.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongMethodException() {
		super();
	}

	//ritorna messaggio di errore
	public ResponseEntity<Object> getErr() {
		return new ResponseEntity<>("ERROR: wrong method!", HttpStatus.BAD_REQUEST);
	}
}