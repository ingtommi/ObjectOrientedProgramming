package it.univpm.TweetAnalyzer.service;

import it.univpm.TweetAnalyzer.model.User;
import it.univpm.TweetAnalyzer.model.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


public class APICall implements APICallService {
	
	private static final String apiBase = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	private String api;
	private String hashtags;
	private int count;
	private String lang;
	
	public APICall() {}
	
	public APICall(String hashtags, int count, String lang) {
		this.hashtags = hashtags.replace("#","%23").replaceAll("\\s+",""); //sostituzione in %23 richiesta da URL, rimozione spazi per evitare eccezioni
		this.count = count;
		this.lang = lang;
		this.api = apiBase + "q=" + this.hashtags + "&count=" + this.count + "&lang=" + this.lang;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMeta() {
		
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
	
	@SuppressWarnings("unchecked")
	public JSONObject getData() {
		
		String body = "";
		String line = "";
		JSONObject obj = null;
		
		try {
			URLConnection openConnection = new URL(api).openConnection();
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
		
		//TODO: lanciare eccezione se status/hashtags = []
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		ArrayList<User> users = new ArrayList<User>();
		JSONArray statuses = (JSONArray) obj.get("statuses");
		
		for(int i=0; i<statuses.size(); i++) {
			JSONObject tweet = (JSONObject) statuses.get(i);
			//TWEET INFO
			String tweetDate = (String) tweet.get("created_at");
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
            String userDate = (String) user.get("created_at");
            Long userId = (Long) user.get("id");
            String userLocation = (String) user.get("location");
            //imposto a null per avere uniformità con tweetLocation
            if(userLocation.equals(""))
            	userLocation = null;
            String userName = (String) user.get("name");
            
            User us = new User(userDate,userId,userLocation,userName);
            users.add(us);
        }
		
		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();
		
		//per ogni tweet viene generato un JSONObject "prop" da mettere dentro un JSONArray "list"
		//contenuto dentro un JSONObject "data" che viene poi ritornato
		for(int k=0; k<statuses.size(); k++) {
			//definiti dentro perchè altrimenti c'erano problemi di sovrascrizione
			JSONObject tweet = new JSONObject();
			JSONObject prop = new JSONObject();
			JSONObject user = new JSONObject();
			
			tweet.put("created_at",tweets.get(k).getCreated_at());
			tweet.put("id",tweets.get(k).getId());
			tweet.put("location",tweets.get(k).getLocation());
			tweet.put("hashtags",tweets.get(k).getHashtags());
			prop.put("tweet",tweet);

			user.put("created_at",users.get(k).getCreated_at());
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