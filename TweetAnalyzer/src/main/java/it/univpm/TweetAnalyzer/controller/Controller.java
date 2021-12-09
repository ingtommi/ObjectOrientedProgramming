package it.univpm.TweetAnalyzer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.TweetAnalyzer.service.APICall;

@RestController
public class Controller {
	
	//TODO: modellare classi chiamate al posto di NULL
	
	@GetMapping(value = "/tweet/metadata")
	public ResponseEntity<Object> getMeta() {
		
		APICall ac = new APICall();
		return new ResponseEntity<>(ac.getMeta(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/data")
	public ResponseEntity<Object> getData(@RequestParam(name = "hashtags") String hashtags,
			@RequestParam(name = "count", defaultValue = "5") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) {
		
		APICall ac = new APICall(hashtags,count,lang);
		return new ResponseEntity<>(ac.getData(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> filter(@RequestParam(name = "long") String lon, @RequestParam(name = "lat") String lat, 
			@RequestParam(name = "rad") String rad) {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}