package it.univpm.TweetAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Questa classe avvia l'applicazione Spring
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 *
 */
@SpringBootApplication
public class TweetAnalyzerApplication {

	/**
	 * Metodo main
	 * 
	 * @param args indica gli argomenti del main
	 */
	public static void main(String[] args) {
		SpringApplication.run(TweetAnalyzerApplication.class, args);
	}
}