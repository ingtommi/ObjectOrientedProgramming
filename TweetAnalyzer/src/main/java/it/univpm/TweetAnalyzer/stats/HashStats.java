package it.univpm.TweetAnalyzer.stats;


import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.Tweet;

public class HashStats {

	int count;
	String hashtag;
	private ArrayList<Tweet> tweets;

	public HashStats (String hashtag, ArrayList<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
	}

	@SuppressWarnings("unchecked")
	public JSONObject hashstats() {
		for(Tweet tweet : tweets) {
			for(String hash : tweet.getHashtags()) {
				if(hash.equals(hashtag)) {
					count++;
				} 
			}
		}

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject out = new JSONObject();

		obj.put("Tweets downloaded", tweets.size());
		obj.put("Tweets containing #" + hashtag, count);

		arr.add(obj);
		out.put("Stats", obj);

		return out;
	}
}
