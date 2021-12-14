package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tweet extends Model {

	private ArrayList<String> hashtags;
	
	public Tweet(LocalDate date, long id, String location, ArrayList<String> hashtags) {
		super(date,id,location);
		this.hashtags = hashtags;
	}

	public ArrayList<String> getHashtags() { return hashtags; }
}