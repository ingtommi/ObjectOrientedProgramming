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

public class APICall {

	private static final String apiBase = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	private String api;
	private String ht1,ht2,ht3;
	private int count;
	private String lang;
	private String met;

	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	ArrayList<User> users = new ArrayList<User>();
	Object[] list = new Object[2];

	public APICall(String ht1, String ht2, String ht3, String met, String lang, int count) {
		this.ht1 = ht1.replace("#","%23").replaceAll("\\s+","");
		if(ht2!=null) {
			this.ht2 = ht2.replace("#","%23").replaceAll("\\s+","");
		}
		if(ht3!=null) {
			this.ht3 = ht3.replace("#","%23").replaceAll("\\s+","");
		}
		this.met = met.toUpperCase(); //url richiede AND/OR			
		this.count = count;
		this.lang = lang;
	}

	public Object[] getList() { return this.list; }
	
	public String apiBuild() {

		if(met.equals("AND") || met.equals("OR")) {
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
		//TODO: inserire eccezione se metodo diverso da AND o OR
		return api;
	}

	@SuppressWarnings("unchecked")
	public JSONObject saveData() {

		String body = "";
		String line = "";
		JSONObject obj = null;

		try {
			URLConnection openConnection = new URL(this.apiBuild()).openConnection();
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
		list[0] = tweets;
		list[1] = users;
		
		JSONObject mex = new JSONObject();
		mex.put("Message","tweets saved: see at http://localhost:8080/tweet/data");

		return mex;
	}
}