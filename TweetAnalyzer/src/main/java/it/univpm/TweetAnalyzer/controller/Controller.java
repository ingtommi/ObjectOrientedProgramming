package it.univpm.TweetAnalyzer.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.TweetAnalyzer.exception.*;
import it.univpm.TweetAnalyzer.filter.DailyFilter;
import it.univpm.TweetAnalyzer.filter.GeoFilter;
import it.univpm.TweetAnalyzer.model.Config;
import it.univpm.TweetAnalyzer.service.APICall;
import it.univpm.TweetAnalyzer.service.GetData;
import it.univpm.TweetAnalyzer.service.GetFile;
import it.univpm.TweetAnalyzer.stats.DailyStats;
import it.univpm.TweetAnalyzer.stats.GeoStats;
import it.univpm.TweetAnalyzer.stats.HashStats;

@RestController
public class Controller {

	APICall call;
	Config conf;

	//AUTENTICAZIONE

	@PostMapping(value = "/config")
	public ResponseEntity<Object> config(
			@RequestParam(name = "url", defaultValue = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?") String url) {

		conf = new Config(url);
		return new ResponseEntity<>(conf.getMex(), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/tweet/get/{method}") //method = and/or
	public ResponseEntity<Object> getData(
			@PathVariable(name = "method") String met,
			@RequestParam(name = "hashtag1") String ht1,
			@RequestParam(name = "hashtag2", required = false) String ht2,
			@RequestParam(name = "hashtag3", required = false) String ht3,
			@RequestParam(name = "count", defaultValue = "5") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) throws WrongMethodException, IsEmptyException, MissingParameterException, MissingCallException {

		if(ht1==null) {
			throw new MissingParameterException("ERROR: missing parameters!");
		}
		if(conf==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/config");
		}
		call = new APICall(ht1,ht2,ht3,met,lang,count,conf.getUrl());
		return new ResponseEntity<>(call.saveData(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/metadata")
	public ResponseEntity<Object> seeMeta() {

		GetData meta = new GetData();
		return new ResponseEntity<>(meta.seeMeta(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/data")
	public ResponseEntity<Object> seeData() throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		GetData data = new GetData(call.getTweets(),call.getUsers());
		return new ResponseEntity<>(data.seeData(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/filter/day")
	public ResponseEntity<Object> dayfilter(
			@RequestParam(name = "day", required = false) Integer day, //utilizzo Integer perchè posso controllare se null
			@RequestParam(name = "month", required = false) Integer month,
			@RequestParam(name = "year", required = false) Integer year) throws MissingCallException {

		LocalDate date;
		if(day == null || month == null || year == null) {
			date = LocalDate.now();
		}
		else date = LocalDate.of(year,Month.of(month),day);
		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		DailyFilter df = new DailyFilter(date,call.getTweets());
		return new ResponseEntity<>(df.filter(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> geofilter(@RequestParam(name = "location") String loc) throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		GeoFilter gf = new GeoFilter(loc,call.getTweets(),call.getUsers());
		return new ResponseEntity<>(gf.filter(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/stats/day")
	public ResponseEntity<Object> daystats(
			@RequestParam(name = "day", required = false) Integer day,
			@RequestParam(name = "month", required = false) Integer month,
			@RequestParam(name = "year", required = false) Integer year) throws MissingCallException {

		LocalDate date;
		if(day == null || month == null || year == null) {
			date = LocalDate.now();
		}
		else date = LocalDate.of(year,Month.of(month),day);
		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		DailyStats ds = new DailyStats(date,call.getTweets());
		return new ResponseEntity<>(ds.stats(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/stats/geo")
	public ResponseEntity<Object> geostats(@RequestParam(name = "location", required = false) String loc) 
			throws FileNotFoundException, IOException, ParseException, MissingCallException {

		GetFile gf = new GetFile();
		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		GeoStats gs = new GeoStats(loc,call.getTweets(),call.getUsers(),gf.getFile());
		return new ResponseEntity<>(gs.stats(), HttpStatus.OK);
	}

	@GetMapping(value = "/tweet/stats/hash")
	public ResponseEntity<Object> hashstats(@RequestParam(name = "hashtag", required = false) String hashtag) throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		HashStats hs = new HashStats(hashtag,call.getTweets());
		return new ResponseEntity<>(hs.stats(), HttpStatus.OK);
	}		
}