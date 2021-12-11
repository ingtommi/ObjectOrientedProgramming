package it.univpm.TweetAnalyzer.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.Tweet;

public class DailyFilter {
	
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	
	public DailyFilter(LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject dayfilter() {
		
		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();
		
		for(int i=0; i<tweets.size(); i++) {
			JSONObject tweet = new JSONObject();
			if(tweets.get(i).getDatatime().compareTo(date)==0) {
				tweet.put("created_at",tweets.get(i).parseData());
				tweet.put("id",tweets.get(i).getId());
				tweet.put("location",tweets.get(i).getLocation());
				tweet.put("hashtags",tweets.get(i).getHashtags());
			}
			list.add(tweet);
			data.put("tweets",list);
		}
		return data;
	}
}