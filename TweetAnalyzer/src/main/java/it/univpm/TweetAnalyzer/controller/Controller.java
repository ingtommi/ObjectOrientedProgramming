package it.univpm.TweetAnalyzer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.TweetAnalyzer.service.APICall;

@RestController
public class Controller {
	
	@GetMapping(value = "/tweet/metadata")
	public ResponseEntity<Object> getMeta() {
		
		APICall call = new APICall();
		return new ResponseEntity<>(call.getMeta(), HttpStatus.OK);
	}
	
	//method = and/or
	@GetMapping(value = "/tweet/data/{method}")
	public ResponseEntity<Object> getData(
			@PathVariable(name = "method") String met,
			@RequestParam(name = "hashtag1") String ht1,
			@RequestParam(name = "hashtag2", required = false) String ht2,
			@RequestParam(name = "hashtag3", required = false) String ht3,
			@RequestParam(name = "count", defaultValue = "5") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) {
		
		APICall call = new APICall(ht1,ht2,ht3,met,lang,count);
		return new ResponseEntity<>(call.getData(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> filter(@RequestParam(name = "long") String lon,  
			@RequestParam(name = "lat") String lat, 
			@RequestParam(name = "rad") String rad) {
		
		//TODO: implementare metodo al posto di NULL
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}