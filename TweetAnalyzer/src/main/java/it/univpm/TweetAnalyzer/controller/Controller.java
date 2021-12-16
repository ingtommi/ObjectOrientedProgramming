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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.TweetAnalyzer.exception.*;
import it.univpm.TweetAnalyzer.filter.DailyFilter;
import it.univpm.TweetAnalyzer.filter.GeoFilter;
import it.univpm.TweetAnalyzer.service.APICall;
import it.univpm.TweetAnalyzer.service.GetData;
import it.univpm.TweetAnalyzer.service.GetFile;
import it.univpm.TweetAnalyzer.stats.DailyStats;
import it.univpm.TweetAnalyzer.stats.GeoStats;
import it.univpm.TweetAnalyzer.stats.HashStats;

/**
 * Questa classe gestisce tutte le varie rotte disponibili
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

@RestController
public class Controller {

	APICall call;

	/**
	 * Rotta di tipo GET che consente di fare la ricerca e salvare i dati e restituisce un messaggio di avvenuto salvataggio
	 * @param met: metodo di tipo AND o OR utilizzati per effettuare la ricerca
	 * @param ht1: primo hashtag, è obbligatorio inserirlo per poter effettuare la ricerca
	 * @param ht2: secondo hashtag, non obbligatorio
	 * @param ht3: terzo hashtag, non obbligatorio
	 * @param count: numero di risultati che si vuole visualizzare 
	 * @param lang: linguaggio della ricerca
	 * @return stringa che conferma il salvataggio del tweet
	 * @throws WrongMethodException se i metodi inseriti sono diversi da and o or
	 * @throws IsEmptyException se nessun tweet contiene gli hashtag selezionati
	 * @throws MissingCallException se prima non viene chiamata la rotta GET
	 */
	@GetMapping(value = "/tweet/get/{method}") //method = and/or
	public ResponseEntity<Object> getData(
			@PathVariable(name = "method") String met,
			@RequestParam(name = "hashtag1") String ht1,
			@RequestParam(name = "hashtag2", required = false) String ht2,
			@RequestParam(name = "hashtag3", required = false) String ht3,
			@RequestParam(name = "count", defaultValue = "5") int count, 
			@RequestParam(name = "lang", defaultValue = "it") String lang) throws WrongMethodException, IsEmptyException, MissingCallException {

		call = new APICall(ht1,ht2,ht3,met,lang,count);
		return new ResponseEntity<>(call.saveData(), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo GET che mostra i metadati
	 * @return un JSONObject contenente le informazioni relative ai tipi di dato visualizzabili
	 */
	@GetMapping(value = "/tweet/metadata")
	public ResponseEntity<Object> seeMeta() {

		GetData meta = new GetData();
		return new ResponseEntity<>(meta.seeMeta(), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo GET che mostra i dati salvati
	 * @return un JSONObject contenente i dati relativi ai tweet precedentemente salvati
	 * @throws MissingCallException se prima non viene chiamata la rotta GET
	 */
	@GetMapping(value = "/tweet/data")
	public ResponseEntity<Object> seeData() throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		GetData data = new GetData(call.getTweets(),call.getUsers());
		return new ResponseEntity<>(data.seeData(), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo GET che permette il filtraggio per giorno
	 * @param day è il giorno selezionato per filtraggio
	 * @param month è il mese selezionato per filtraggio
	 * @param year è l'anno selezionato per filtraggio
	 * @return un JSONObject contenente i tweet postati nel giorno inserito
	 * @throws MissingCallException se prima non viene chiamata la rotta GET
	 */
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

	/**
	 * Rotta di tipo GET che permette il filtraggio per luogo
	 * @param loc è il luogo sul quale si vuole effettuare il filtraggio
	 * @return un JSONObject contenente i tweet postati dal luogo inserito
	 * @throws MissingCallException se prima di DATA non viene chiamata la rotta GET
	 */
	@GetMapping(value = "/tweet/filter/geo")
	public ResponseEntity<Object> geofilter(@RequestParam(name = "location") String loc) throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		GeoFilter gf = new GeoFilter(loc,call.getTweets(),call.getUsers());
		return new ResponseEntity<>(gf.filter(), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che mostra le statistiche per il giorno selezionato e i due precedenti
	 * @param day è il giorno selezionato per effettuare la statistica
	 * @param month è il mese selezionato per effettuare la statistica
	 * @param year è l'anno selezionato per effettuare la statistica
	 * @return una HashMap con il numero di tweet postati nel giorno inserito e nei due precedenti
	 * @throws MissingCallException
	 */
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
    
    /**
     * Rotta di tipo GET che mostra le statistiche per luogo
     * @param loc è il luogo sul quale si vuole effettuare le statistiche
     * @return una HashMap con il numero di tweet postati dal luogo inserito o dall'Italia
     * @throws FileNotFoundException se ci sono problemi riguardanti il file
     * @throws IOException se si verifica un errore di I/O di qualche tipo
     * @throws ParseException se si verifica un errore durante il parsing
     * @throws MissingCallException se prima di DATA non viene chiamata la rotta GET
     */
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

    /**
     * Rotta di tipo GET che mostra le statistiche per hashtag
     * @param hashtag è quello sul quale si vuole effettuare una statistica
     * @return una HashMap con il numero di tweet contenenti l'hashtag inserito
     * @throws MissingCallException se prima di DATA non viene chiamata la rotta GET
     */
	@GetMapping(value = "/tweet/stats/hash")
	public ResponseEntity<Object> hashstats(@RequestParam(name = "hashtag", required = false) String hashtag) throws MissingCallException {

		if(call==null) {
			throw new MissingCallException("ERROR: first contact http://localhost:8080/tweet/get/{method}");
		}
		HashStats hs = new HashStats(hashtag,call.getTweets());
		return new ResponseEntity<>(hs.stats(), HttpStatus.OK);
	}		
}