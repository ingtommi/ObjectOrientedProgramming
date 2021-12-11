package it.univpm.TweetAnalyzer.stats;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TweetAnalyzer.model.Tweet;

public class DailyStats {
	
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	int day1 = 0;
	int day2 = 0;
	int day3 = 0;
	
	public DailyStats (LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject daystats() {
		for(Tweet tweet : tweets) {
			if(tweet.getDatatime().compareTo(date)==0) {
				day1++;
			}
			else if(tweet.getDatatime().compareTo(date)==1) {
				day2++;
			}
			else if(tweet.getDatatime().compareTo(date)==2) {
				day3++;
			}
		}
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject out = new JSONObject();
		
		obj.put("Tweets downloaded", tweets.size());
		obj.put("Tweets written on " + date, day1);
		obj.put("Tweets written on " + date.minusDays(1), day2);
		obj.put("Tweets written on " + date.minusDays(2), day3);
		arr.add(obj);
		out.put("Stats", obj);
		
		return out;
	}
}
