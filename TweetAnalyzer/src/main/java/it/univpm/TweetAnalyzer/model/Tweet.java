package it.univpm.TweetAnalyzer.model;

import java.util.ArrayList;

public class Tweet extends Model {

	private ArrayList<String> hashtags;
	
	public Tweet(String created_at, long id, String location, ArrayList<String> hashtags) {
		super(created_at,id,location);
		this.hashtags = hashtags;
	}

	public ArrayList<String> getHashtags() { return hashtags; }
}