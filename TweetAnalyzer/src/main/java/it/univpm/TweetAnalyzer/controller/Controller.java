package it.univpm.TweetAnalyzer.controller;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//TODO: lanciare eccezioni quando mancano parametri
	
	//visti i problemi delle API per la restituzione degli hashtags il filtraggio viene fatto direttamente qua
	@GetMapping(value = "/tweet/data/{method}") //method = and/or
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
	
	@PostMapping(value = "/tweet/filter/day")
	public ResponseEntity<Object> dayfilter(
			@RequestParam(name = "day") int day,
			@RequestParam(name = "month") int month,
			@RequestParam(name = "year") int year) {
		
		LocalDate date = LocalDate.of(year,Month.of(month),day);
		return new ResponseEntity<>(date, HttpStatus.OK);
	}
	
	//@PostMapping(value = "/tweet/filter/geo")
	
	@PostMapping(value = "/tweet/stats/day")
	public ResponseEntity<Object> daystats(
			@RequestParam(name = "day") int day,
			@RequestParam(name = "month") int month,
			@RequestParam(name = "year") int year) {
		
		LocalDate date = LocalDate.of(year,Month.of(month),day);
		return new ResponseEntity<>(date, HttpStatus.OK);
	}
	
	//@PostMapping(value = "/tweet/stats/geo")
}