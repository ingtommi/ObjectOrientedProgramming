package it.univpm.TweetAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	//@Autowired
	
	//@GetMapping(value = "/tweet/metadata")
	
	//@GetMapping(value = "/tweet/data")
	
	@GetMapping(value = "/tweet/filter/hashtag")
	public ResponseEntity<Object> getData(@RequestParam(name = "query") String query,
			@RequestParam(name = "count", defaultValue = "10") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) {
		//TODO: modellare classe chiamata
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> filter(@RequestParam(name = "long") String lon, @RequestParam(name = "lat") String lat, 
			@RequestParam(name = "rad") String rad, @RequestParam(name = "count", defaultValue = "10") int count) {
		//TODO: modellare classe chiamata
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}