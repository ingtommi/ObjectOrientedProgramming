package it.univpm.TweetAnalyzer.stats;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.*;


public class GeoStats {
 
	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;
	ArrayList<Location> cities;

	public GeoStats (String location, ArrayList<Tweet> tweets, ArrayList<User> users, ArrayList<Location> cities) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
		this.cities = cities;
	}
	
	public HashMap<String, String> geostats() {
		
		HashMap<String, String> out = new HashMap<String, String>();
		return out;
	}
}