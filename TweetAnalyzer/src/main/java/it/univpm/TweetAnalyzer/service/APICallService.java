package it.univpm.TweetAnalyzer.service;

import java.time.LocalDate;
import java.util.ArrayList;

import it.univpm.TweetAnalyzer.exception.IsEmptyException;
import it.univpm.TweetAnalyzer.exception.WrongMethodException;
import it.univpm.TweetAnalyzer.model.Tweet;
import it.univpm.TweetAnalyzer.model.User;

public interface APICallService {
	
	public abstract ArrayList<Tweet> getTweets();
	
	public abstract ArrayList<User> getUsers();

	public abstract String apiBuild() throws WrongMethodException;
	
	public abstract String saveData() throws WrongMethodException, IsEmptyException ;
	
	public abstract LocalDate parseData(String created_at);
}