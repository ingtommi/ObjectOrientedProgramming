package it.univpm.TweetAnalyzer.service;

import it.univpm.TweetAnalyzer.model.User;
import it.univpm.TweetAnalyzer.exception.*;
import it.univpm.TweetAnalyzer.model.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Questa classe implementa l'interfaccia APICallService
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class APICall implements APICallService {

	/**
	 * Attributi della classe APICallService
	 */
	private static final String apiBase = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	private String api;
	private String ht1,ht2,ht3;
	private int count;
	private String lang;
	private String met;

	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	ArrayList<User> users = new ArrayList<User>();

	public APICall(String ht1, String ht2, String ht3, String met, String lang, int count) {
		this.ht1 = ht1.replace("#","%23").replaceAll("\\s+","");
		if(ht2!=null) {
			this.ht2 = ht2.replace("#","%23").replaceAll("\\s+","");
		}
		if(ht3!=null) {
			this.ht3 = ht3.replace("#","%23").replaceAll("\\s+","");
		}
		this.met = met; //url richiede and/or			
		this.count = count;
		this.lang = lang;
	}


	/**
	 * Metodo get che ritorna una lista di tweets
	 */
	@Override
	public ArrayList<Tweet> getTweets() { return tweets; }

	/**
	 * Metodo get che ritorna una lista degli utenti
	 */
	@Override
	public ArrayList<User> getUsers() { return users; }



	/**
	 * Questo metodo permette di costruire l'URL per effettuare la ricerca
	 */
	@Override
	public String apiBuild() throws WrongMethodException {

		if (met.equals("and") || met.equals("or")) {
			if(ht2==null && ht3==null) {
				api = apiBase + "q=" + ht1 + "&count=" + count +"&lang=" + lang;
			}
			else if(ht3==null) {
				api = apiBase + "q=" + ht1 + "%20" + met + "%20" + ht2 + "&count=" + count +"&lang=" + lang;

			}
			else if(ht2==null) {
				api = apiBase + "q=" + ht1 + "%20" + met + "%20" + ht3 + "&count=" + count +"&lang=" + lang;
			}
			else {
				api = apiBase + "q=" + ht1 + "%20" + met + "%20" + ht2 + "%20" + met + "%20" + ht3 + "&count=" + count +"&lang=" + lang;
			}
		}
		else { 
			throw new WrongMethodException("ERROR: wrong method!");
		} 
		System.out.println(api);
		return api;
	}


	/**
	 * Metodo che permette di salvare i dati trovati
	 */
	@Override
	public String saveData() throws WrongMethodException, IsEmptyException {

		String body = "";
		String line = "";
		JSONObject obj = null;

		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(this.apiBuild()).openConnection();
			InputStream in = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader(in));
			while ((line = buf.readLine()) != null) {
				body += line;
			}
			in.close();
			obj = (JSONObject) JSONValue.parseWithException(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray statuses = (JSONArray) obj.get("statuses");
		if(statuses.isEmpty()) {
			throw new IsEmptyException("ERROR: no tweets found!");
		}
		for(int i=0; i<statuses.size(); i++) {
			JSONObject tweet = (JSONObject) statuses.get(i);
			
			//TWEET INFO
			LocalDate tweetDate = this.parseData((String) tweet.get("created_at"));
			Long tweetId = (Long) tweet.get("id");
			String tweetLocation = (String) tweet.get("place");

			JSONObject entities = (JSONObject) tweet.get("entities");
			JSONArray ht = (JSONArray) entities.get("hashtags");
			ArrayList<String> hashtags = new ArrayList<String>();
			for(int j=0; j<ht.size(); j++) {
				JSONObject hashtag = (JSONObject) ht.get(j);
				String text = (String) hashtag.get("text");
				hashtags.add(text);
			}

			Tweet tw = new Tweet(tweetDate,tweetId,tweetLocation,hashtags);
			tweets.add(tw);

			//USER INFO
			JSONObject user = (JSONObject) tweet.get("user");
			LocalDate userDate = this.parseData((String) user.get("created_at"));
			Long userId = (Long) user.get("id");
			String userLocation = (String) user.get("location");
			//imposto a null per avere uniformità con tweetLocation
			if(userLocation.equals(""))
				userLocation = null;
			String userName = (String) user.get("name");

			User us = new User(userDate,userId,userLocation,userName);
			users.add(us);
		}
		return "TWEETS SAVED: see at http://localhost:8080/tweet/data";
	}


	/**
	 * Metodo che permette di convertire la stringa della data in formato LocalDate
	 */
	@Override
	public LocalDate parseData(String created_at) {
		LocalDate date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		LocalDateTime dt = LocalDateTime.parse(created_at, formatter);
		date = dt.toLocalDate();
		return date;
	}
}