package it.univpm.TweetAnalyzer.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.Tweet;

/**
 * Questa classe implementa l'interfaccia Stats
 * ed effettua statistiche su tweet giornalieri
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class DailyStats implements Stats {

	/**
	 * Attributi di data,tweet e giorno 1,2,3
	 */
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	int day1 = 0;
	int day2 = 0;
	int day3 = 0;

	/**
	 * Questo è il costruttore
	 * @param date indica la data selezionata per la statistica in formato LocalDate
	 * @param tweets indica la lista dei tweet 
	 */
	public DailyStats (LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}

	@Override
	/**
	 * Questo metodo serve per scorrere i tweet e trovare quelli scritti nella data selezionata e nei due giorni precedenti 
	 * @return HashMap  contenente i tweet scaricati, i tweet scritti nella data scelta e nei due giorni precedenti 
	 * e la percentuale riguardante la somma dei tweet scritti nei tre giorni rispetto a quelli totali
	 */
	public HashMap<String,Float> stats() {
		for(Tweet tweet : tweets) {
			if(tweet.getDatatime().compareTo(date)==0) {
				day1++;
			}
			else if(tweet.getDatatime().compareTo(date)==1) {
				day2++;
			}
			else if(tweet.getDatatime().compareTo(date)==2) {
				day3++;
			}
		}
		HashMap<String, Float> out = new HashMap<String, Float>();
		out.put("Total tweets downloaded", (float) tweets.size());
		out.put("Tweets written on " + date, (float) day1);
		out.put("Tweets written on " + date.minusDays(1), (float) day2);
		out.put("Tweets written on " + date.minusDays(2), (float) day3);
		out.put("Percentage", 100* (float) (day1+day2+day3)/tweets.size());
		return out;		
	}
}