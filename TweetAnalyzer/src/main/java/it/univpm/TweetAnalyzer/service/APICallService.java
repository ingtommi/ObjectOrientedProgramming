package it.univpm.TweetAnalyzer.service;

import java.time.LocalDate;
import java.util.ArrayList;

import it.univpm.TweetAnalyzer.model.Tweet;
import it.univpm.TweetAnalyzer.model.User;

public interface APICallService {
	
	public ArrayList<Tweet> getTweets();
	
	public ArrayList<User> getUsers();

	public abstract String apiBuild();
	
	public abstract String saveData();
	
	public abstract LocalDate parseData(String created_at);
}