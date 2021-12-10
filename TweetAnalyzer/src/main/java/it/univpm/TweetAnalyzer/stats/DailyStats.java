package it.univpm.TweetAnalyzer.stats;

import java.time.LocalDate;
import java.util.ArrayList;

import it.univpm.TweetAnalyzer.model.Tweet;

public class DailyStats {
	
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	
	public DailyStats (LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}
}
