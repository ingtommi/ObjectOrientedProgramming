package it.univpm.TweetAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.TweetAnalyzer.service.APICall;

@RestController
public class Controller {
	
	@Autowired
	APICall apicall;
	
	//TODO: modellare classi chiamate al posto di NULL
	
	@GetMapping(value = "/tweet/metadata")
	public ResponseEntity<Object> getMeta() {
		return new ResponseEntity<>(apicall.getMeta(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/data")
	public ResponseEntity<Object> getData(@RequestParam(name = "hashtags") String query,
			@RequestParam(name = "count", defaultValue = "5") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) {
		return new ResponseEntity<>(apicall.getData(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> filter(@RequestParam(name = "long") String lon, @RequestParam(name = "lat") String lat, 
			@RequestParam(name = "rad") String rad) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}