package it.univpm.TweetAnalyzer.service;

import org.json.simple.JSONObject;

public interface APICallService {
	
	public abstract JSONObject getMeta();
	
	public abstract JSONObject getData();
}