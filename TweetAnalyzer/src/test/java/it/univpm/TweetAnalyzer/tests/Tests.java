package it.univpm.TweetAnalyzer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.univpm.TweetAnalyzer.exception.WrongMethodException;
import it.univpm.TweetAnalyzer.model.Location;
import it.univpm.TweetAnalyzer.service.APICall;

@SpringBootTest
class Tests {
	
	private APICall call;
	private Location loc;
	
	@BeforeEach
	void setUp() throws Exception {
		
		call = new APICall("#univpm",null,null,"and","it",5);
		loc = new Location("Jesi","AN","Marche");
	}

	@AfterEach
	void tearDown() throws Exception {}
	
	@Test
	@DisplayName("Controllo correttezza url")
	void analyze1() throws WrongMethodException {
		
		assertEquals(call.apiBuild(),"https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=%23univpm&count=5&lang=it");
	}
	
	@Test
	@DisplayName("Controllo correttezza location")
	void analyze2() {
		
		assertEquals(loc.toString(),"Città: Jesi | Provincia: AN | Regione: Marche");
	}
}