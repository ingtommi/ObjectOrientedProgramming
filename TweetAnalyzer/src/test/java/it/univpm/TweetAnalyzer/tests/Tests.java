package it.univpm.TweetAnalyzer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.univpm.TweetAnalyzer.exception.WrongMethodException;
import it.univpm.TweetAnalyzer.service.APICall;

@SpringBootTest
class Tests {
	
	private APICall c1,c2;
	private String created_at;
	
	@BeforeEach
	void setUp() throws Exception {
		
		c1 = new APICall("#univpm",null,null,"and","it",5);
		c2 = new APICall("#java",null,null,"xxx","en",10);
		created_at = "Sun Feb 25 18:11:01 +0000 2018";
	}

	@AfterEach
	void tearDown() throws Exception {}
	
	@Test
	@DisplayName("Controllo correttezza url")
	void analyze1() throws WrongMethodException {
		
		assertEquals(c1.apiBuild(),"https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=%23univpm&count=5&lang=it");
	}
	
	@Test
	@DisplayName("Controllo correttezza data")
	void analyze2() {
		
		LocalDate date = LocalDate.of(2018,02,25);
		assertEquals(c1.parseData(created_at),date);
	}
	
	@Test
	@DisplayName("Controllo lancio eccezione WrongMethodException")
	void analyze3() {
		
		try {
			c2.apiBuild();
			//serve per generare un failure
			fail("No exception generated!");
		}
		catch(WrongMethodException e) {
			e.printStackTrace();
		}
	}
}