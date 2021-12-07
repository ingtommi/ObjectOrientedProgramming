package it.univpm.TweetAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	
	@GetMapping(value = "/tweet/data")
	public ResponseEntity<Object> getData (@RequestParam(name = "query") String query, @RequestParam(name = "count", defaultValue = "10") int count) {
		return new ResponseEntity<>(.getData(), HttpStatus.OK);
	}
}