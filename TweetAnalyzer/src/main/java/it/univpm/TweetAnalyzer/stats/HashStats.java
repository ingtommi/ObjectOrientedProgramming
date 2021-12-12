package it.univpm.TweetAnalyzer.stats;


import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.Tweet;

public class HashStats {

	String hashtag;
	private ArrayList<Tweet> tweets;

	public HashStats (String hashtag, ArrayList<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
	}

	public HashMap<String, Float> hashstats() {
		
		HashMap<String, Float> out = new HashMap<String, Float>();
		if(hashtag == null) {
			out = this.general();
		}
		else out = this.specific();
		return out;
	}
	
	//se hashtag != null
	private HashMap<String, Float> specific() {
		
		int count = 0;
		for(Tweet tweet : tweets) {
			for(String hash : tweet.getHashtags()) {
				if(hash.toLowerCase().equals(hashtag.toLowerCase())) { //permette di trovare stringhe scritte diversamente
					count++;
				} 
			}	
		}
		HashMap<String, Float> spe = new HashMap<String, Float>();
		spe.put("Total tweets downloaded", (float) tweets.size());
		spe.put("Tweets containing #" + hashtag, (float) count);
		spe.put("Percentage", 100*(float) count/tweets.size());

		return spe;
	}
	
	//se hashthag == null
	private HashMap<String, Float> general() {
		
		int count = 0;
		for(Tweet tweet : tweets) {
			count += tweet.getHashtags().size();
		}
		HashMap<String, Float> gen = new HashMap<String, Float>();
		gen.put("Total tweets downloaded", (float) tweets.size());
		gen.put("Average hashtags per tweet", (float) count/tweets.size());

		return gen;
	}
}