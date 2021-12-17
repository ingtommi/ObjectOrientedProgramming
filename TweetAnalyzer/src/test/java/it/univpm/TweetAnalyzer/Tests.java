package it.univpm.TweetAnalyzer;

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

/**
 * Questa classe contiene dei test per verificare il corretto funzionamento di alcune parti del programma
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

@SpringBootTest
class Tests {

	private APICall c1,c2;
	private String created_at;

	/**
	 * Questo metodo inizializza gli attributi
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		c1 = new APICall("#univpm",null,null,"and","it",5);
		c2 = new APICall("#java",null,null,"xxx","en",10);
		created_at = "Sun Feb 25 18:11:01 +0000 2018";
	}

	/**
	 * Questo metodo distrugge gli attributi
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {}

	/**
	 * Questo test verifica la correttezza dell'url
	 * 
	 * @throws WrongMethodException se i metodi inseriti sono diversi da AND o OR
	 */
	@Test
	@DisplayName("Controllo correttezza url")
	void analyze1() throws WrongMethodException {

		assertEquals(c1.apiBuild(),"https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=%23univpm&count=5&lang=it");
	}

	/**
	 * Questo test verifica la correttezza della data
	 */
	@Test
	@DisplayName("Controllo correttezza data")
	void analyze2() {

		LocalDate date = LocalDate.of(2018,02,25);
		assertEquals(c1.parseData(created_at),date);
	}

	/**
	 * Questo test verifica il corretto lancio dell'eccezione WrongMethodException
	 */
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