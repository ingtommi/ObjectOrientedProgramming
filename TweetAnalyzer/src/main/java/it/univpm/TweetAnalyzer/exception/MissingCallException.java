package it.univpm.TweetAnalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MissingCallException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MissingCallException() {
		super();
	}

	//ritorna messaggio di errore
	public ResponseEntity<Object> getErr() {
		return new ResponseEntity<>("ERROR: first contact http://localhost:8080/tweet/get", HttpStatus.BAD_REQUEST);
	}
}