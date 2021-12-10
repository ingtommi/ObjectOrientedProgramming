package it.univpm.TweetAnalyzer.stats;

import java.util.ArrayList;

import it.univpm.TweetAnalyzer.model.*;


public class GeoStats {
 
	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;

	public GeoStats (String location , ArrayList<Tweet> tweets, ArrayList<User> users) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
	}

}
