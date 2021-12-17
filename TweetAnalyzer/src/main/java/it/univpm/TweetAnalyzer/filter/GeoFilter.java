package it.univpm.TweetAnalyzer.filter;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.*;

/**
 * Questa classe implementa l'interfaccia Filter
 * ed effettua filtraggio sui tweet di un luogo scelto
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class GeoFilter implements Filter {

	/**
	 * Attributi di luogo, tweet e user
	 */
	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;

	/**
	 * Costruttore
	 * @param location indica il luogo scelto per effettuare il filtraggio
	 * @param tweets indica la lista di tweets salvati
	 * @param users indica la lista di utenti associati ai tweet
	 */
	public GeoFilter (String location , ArrayList<Tweet> tweets, ArrayList<User> users) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
	}

	/**
	 * Questo metodo serve per memorizzare in un JSONObject i tweets con localizzazione nel luogo scelto
	 * 
	 * @return un JSONObject contenente la lista dei tweet localizzati nel luogo selezionato
	 */
	@Override
	public JSONObject filter() {

		JSONObject loc = new JSONObject();
		JSONArray list = new JSONArray();

		for(int i=0; i<tweets.size(); i++) {
			JSONObject tweet = null;
			if(tweets.get(i).getLocation() != null) {
				if(tweets.get(i).getLocation().toLowerCase().contains(location.toLowerCase())) {
					tweet = new JSONObject();
					tweet.put("created_at",tweets.get(i).getDatatime());
					tweet.put("id",tweets.get(i).getId());
					tweet.put("location",tweets.get(i).getLocation());
					tweet.put("hashtags",tweets.get(i).getHashtags());
				}	
				if(tweet != null) {
					list.add(tweet);
				}
				loc.put("tweets written in " + location, list);
			}
			else if(tweets.get(i).getLocation() == null && users.get(i).getLocation() != null) {
				if(users.get(i).getLocation().toLowerCase().contains(location.toLowerCase())) {
					tweet = new JSONObject();
					tweet.put("created_at",tweets.get(i).getDatatime());
					tweet.put("id",tweets.get(i).getId());
					tweet.put("location",users.get(i).getLocation());
					tweet.put("hashtags",tweets.get(i).getHashtags());
				}
				if (tweet!= null) {
					list.add(tweet);
				}
				loc.put("tweets written in " + location, list);
			}
		}
		return loc;
	}
}