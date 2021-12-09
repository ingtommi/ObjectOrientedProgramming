package it.univpm.TweetAnalyzer.model;

import java.util.ArrayList;

public class Tweet extends Model {

	private String text;
	private ArrayList<String> hashtags;
	
	public Tweet(String created_at, long id, String location, String text, ArrayList<String> hashtags) {
		super(created_at,id,location);
		this.text = text;
		this.hashtags = hashtags;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}
}