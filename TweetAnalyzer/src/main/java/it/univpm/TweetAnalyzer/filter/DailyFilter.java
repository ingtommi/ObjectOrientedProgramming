package it.univpm.TweetAnalyzer.filter;

import java.time.LocalDate;
import java.util.ArrayList;

import it.univpm.TweetAnalyzer.model.Tweet;

public class DailyFilter {
	
	private LocalDate date;
	private ArrayList<Tweet> tweets;
	private int tot;
	
	public DailyFilter (LocalDate date, ArrayList<Tweet> tweets) {
		this.date = date;
		this.tweets = tweets;
	}
}