package it.univpm.TweetAnalyzer.filter;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.*;


public class GeoFilter {

	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;

	public GeoFilter (String location , ArrayList<Tweet> tweets, ArrayList<User> users) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
	}

	@SuppressWarnings("unchecked")
	public JSONObject geofilter() {

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