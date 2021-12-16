package it.univpm.TweetAnalyzer.service;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.Tweet;
import it.univpm.TweetAnalyzer.model.User;

/**
 * Questa classe implementa l'interfaccia GetDataService e
 * permete di visualizzare i dati e metadati
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 *
 */
public class GetData implements GetDataService{

	/**
	 * Attribuiti di GetData: lista di tweets e utenti
	 */
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;

	public GetData() {}

	/**
	 * Questo è il costruttore di GetData
	 * @param tweets: lista dei tweet
	 * @param users: lista di utenti
	 */
	public GetData(ArrayList<Tweet> tweets, ArrayList<User> users) {
		this.tweets = tweets;
		this.users = users;
	}



	/**
	 * Questo metodo permette di visualizzare i metadati 
	 * @return JSONObject contenente i metadati
	 */
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject seeMeta() {

		JSONObject meta = new JSONObject();
		JSONArray list = new JSONArray();
		JSONObject prop = new JSONObject();
		JSONObject tweet = new JSONObject();
		JSONObject user = new JSONObject();

		tweet.put("created_at","String");
		tweet.put("id","long");
		tweet.put("location","String");
		tweet.put("hashtags","ArrayList<String>");
		prop.put("tweet",tweet);

		user.put("created_at","String");
		user.put("id","long");
		user.put("name","String");
		user.put("location","String");
		prop.put("user",user);

		list.add(prop);
		meta.put("list",prop);

		return meta;
	}


	/**
	 * Questo metodo permette di visualizzare i dati
	 * @return JSONObject contentente i dati 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject seeData() {

		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();

		//per ogni tweet viene generato un JSONObject "prop" da mettere dentro un JSONArray "list"
		//contenuto dentro un JSONObject "data" che viene poi ritornato
		for(int k=0; k<tweets.size(); k++) {

			JSONObject tweet = new JSONObject();
			JSONObject prop = new JSONObject();
			JSONObject user = new JSONObject();

			tweet.put("created_at",tweets.get(k).getDatatime());
			tweet.put("id",tweets.get(k).getId());
			tweet.put("location",tweets.get(k).getLocation());
			tweet.put("hashtags",tweets.get(k).getHashtags());
			prop.put("tweet",tweet);

			user.put("created_at",users.get(k).getDatatime());
			user.put("id",users.get(k).getId());
			user.put("name",users.get(k).getName());
			user.put("location",users.get(k).getLocation());
			prop.put("user",user);

			list.add(prop);
		}
		data.put("list",list);
		return data;
	}
}