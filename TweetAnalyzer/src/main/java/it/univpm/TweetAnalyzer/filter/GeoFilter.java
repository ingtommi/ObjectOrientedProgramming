package it.univpm.TweetAnalyzer.filter;

import java.util.ArrayList;

import it.univpm.TweetAnalyzer.model.*;


public class GeoFilter {
	
	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;

	public GeoFilter (String location , ArrayList<Tweet> tweets, ArrayList<User> users) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
	}
}