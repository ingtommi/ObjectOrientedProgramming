package it.univpm.TweetAnalyzer.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.Tweet;

/**
 * Questa classe implementa l'interfaccia Filter
 * ed effettua filtraggio sui tweet di una data scelta
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class DailyFilter implements Filter {

	/**
	 * Attributi di data e tweet
	 */
	private LocalDate date;
	private ArrayList<Tweet> tweets;

	/**
	 * Questo è il costruttore
	 * @param date indica la data selezionata per filtrare
	 * @param tweets indica la lista dei tweet salvati
	 */
	public DailyFilter(LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}

	/**
	 * Questo metodo serve per memorizzare in un JSONObject i tweets scritti nella data scelta
	 * 
	 * @return un JSONObject contenente i tweet scritti nel giorno scelto e la lista dei tweet di quel giorno
	 */
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject filter() {

		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();

		for(int i=0; i<tweets.size(); i++) {
			JSONObject tweet = null;
			if(tweets.get(i).getDatatime().compareTo(date)==0) {
				tweet = new JSONObject();
				tweet.put("created_at",tweets.get(i).getDatatime());
				tweet.put("id",tweets.get(i).getId());
				tweet.put("location",tweets.get(i).getLocation());
				tweet.put("hashtags",tweets.get(i).getHashtags());
			}
			if(tweet != null) {
				list.add(tweet);
			}
			data.put("tweets written on " + date, list);
		}
		return data;
	}
}