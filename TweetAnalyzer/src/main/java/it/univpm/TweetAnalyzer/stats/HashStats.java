package it.univpm.TweetAnalyzer.stats;


import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.Tweet;

/**
 * Questa classe implementa l'interfaccia Stats 
 * ed effettua statistiche in base agli hashtag
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class HashStats implements Stats{

	/**
	 * Attributi di HashStats: hashtag e tweets
	 */
	String hashtag;
	private ArrayList<Tweet> tweets;

	/**
	 * Questo è il costuttore
	 * @param hashtag indica l'hashtag da trovare nei tweets
	 * @param tweets indica i tweets contenenti uno specifico hashtag
	 */
	public HashStats (String hashtag, ArrayList<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
	}

	@Override
	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweets contententi uno specifico hashtag
	 * Ci sono due diverse possibilità nel caso l'hashtag sia scelto o meno per effettuare la statistica
	 * @return HashMap contenente diversi dati in base alla presenza o meno dell'hashtag scelto
	 * 
	 */
	public HashMap<String, Float> stats() {

		HashMap<String, Float> out = new HashMap<String, Float>();
		if(hashtag == null) {
			out = this.general();
		}
		else out = this.specific();
		return out;
	}

	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweets contenenti uno specifico hashtag
	 * Caso in cui viene scelto un hashtag
	 * @return HashMap contenente i tweets scaricati, i tweets contenenti l'hastag scelto e la percentuale
	 */
	//se hashtag != null
	private HashMap<String, Float> specific() {

		int tot = 0;
		for(Tweet tweet : tweets) {
			for(String hash : tweet.getHashtags()) {
				if(hash.toLowerCase().equals(hashtag.toLowerCase())) { //permette di trovare stringhe scritte diversamente
					tot++;
				} 
			}	
		}
		HashMap<String, Float> spe = new HashMap<String, Float>();
		spe.put("Total tweets downloaded", (float) tweets.size());
		spe.put("Tweets containing #" + hashtag, (float) tot);
		spe.put("Percentage", 100* (float) tot/tweets.size());

		return spe;
	}

	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweets contenenti uno specifico hashtag
	 * Caso in cui non viene scelto nessun hashtag
	 * @return HashMap contenente i tweets scaricati, il numero di hastag massimo per tweet, il numero di hastag minimo per tweet
	 * e la media deglia hashtag per tweet
	 */

	//se hashthag == null
	private HashMap<String, Float> general() {

		int tot = 0;
		int max = 0;
		int min = 100;
		for(Tweet tweet : tweets) {
			tot += tweet.getHashtags().size();
			if(tweet.getHashtags().size()>max) {
				max = tweet.getHashtags().size();
			}
			if(tweet.getHashtags().size()<min) {
				min = tweet.getHashtags().size();
			}
		}
		HashMap<String, Float> gen = new HashMap<String, Float>();
		gen.put("Total tweets downloaded", (float) tweets.size());
		gen.put("Max hashtags per tweet", (float) max);
		gen.put("Min hashtags per tweet", (float) min);
		gen.put("Average hashtags per tweet", (float) tot/tweets.size());

		return gen;
	}
}