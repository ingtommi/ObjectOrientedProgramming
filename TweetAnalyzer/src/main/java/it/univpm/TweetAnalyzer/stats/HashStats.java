package it.univpm.TweetAnalyzer.stats;


import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.Tweet;

public class HashStats implements Stats{

	String hashtag;
	private ArrayList<Tweet> tweets;

	public HashStats (String hashtag, ArrayList<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
	}

	@Override
	public HashMap<String, Float> stats() {
		
		HashMap<String, Float> out = new HashMap<String, Float>();
		if(hashtag == null) {
			out = this.general();
		}
		else out = this.specific();
		return out;
	}
	
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