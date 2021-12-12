package it.univpm.TweetAnalyzer.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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

	public HashMap<String,Float> daystats() {
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
		HashMap<String, Float> out = new HashMap<String, Float>();
		out.put("Total tweets downloaded", (float) tweets.size());
		out.put("Tweets written on " + date, (float) day1);
		out.put("Tweets written on " + date.minusDays(1), (float) day2);
		out.put("Tweets written on " + date.minusDays(2), (float) day3);
		out.put("Percentage", 100*(float) (day1+day2+day3)/tweets.size());
		return out;		
	}
}
