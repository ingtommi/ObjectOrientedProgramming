package it.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsEmptyException() {
		super();
	}

	//ritorna messaggio di errore
	public ResponseEntity<Object> getErr() {
		return new ResponseEntity<>("ERROR: no tweets found!", HttpStatus.BAD_REQUEST);
	}
}