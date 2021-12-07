package it.univpm.TweetAnalyzer.model;

import java.util.ArrayList;

public class Tweet {

	private String created_at;
	private long id;
	private String text;
	private ArrayList<String> hashtags;
	private String[] coordinates; //[lon,lat]
	private String[] place; //[full_name,country_code]
	
	public Tweet(String created_at, long id, String text, ArrayList<String> hashtags, String[] coordinates, String[] place) {
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		this.hashtags = hashtags;
		this.coordinates = coordinates;
		this.place = place;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}

	public String[] getPlace() {
		return place;
	}

	public void setPlace(String[] place) {
		this.place = place;
	}
}