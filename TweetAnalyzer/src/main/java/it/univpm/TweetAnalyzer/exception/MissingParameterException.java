package it.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public MissingParameterException() {
		super();
	}

	//ritorna messaggio di errore
	public ResponseEntity<Object> getErr() {
		return new ResponseEntity<>("ERROR: missing parameters!", HttpStatus.BAD_REQUEST);
	}
}
