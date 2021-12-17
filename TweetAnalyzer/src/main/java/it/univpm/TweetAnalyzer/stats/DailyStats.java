package it.univpm.TweetAnalyzer.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.Tweet;

/**
 * Questa classe implementa l'interfaccia Stats
 * ed effettua statistiche su tweet giornalieri
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class DailyStats implements Stats {

	/**
	 * Attributi di data, tweet e giorno 1,2,3
	 */
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	private int day1 = 0;
	private int day2 = 0;
	private int day3 = 0;

	/**
	 * Costruttore
	 * 
	 * @param date indica la data selezionata per la statistica in formato LocalDate
	 * @param tweets indica la lista dei tweet 
	 */
	public DailyStats (LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}

	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweets scritti in quella data o nei due giorni precedenti 
	 * 
	 * @return HashMap  contenente i tweet scaricati, i tweet scritti nella data scelta e nei due giorni precedenti 
	 * e la percentuale deitweet scritti nei tre giorni rispetto a quelli totali
	 */
	@Override
	public HashMap<String,Float> stats() {
		for(Tweet tweet : tweets) {
			if(tweet.getDatatime().compareTo(date)==0) {
				day1++;
			}
			else if(tweet.getDatatime().compareTo(date.minusDays(1))==0) {
				day2++;
			}
			else if(tweet.getDatatime().compareTo(date.minusDays(2))==0) {
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