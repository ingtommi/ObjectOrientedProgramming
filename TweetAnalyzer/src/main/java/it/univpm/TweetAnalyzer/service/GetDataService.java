package it.univpm.TweetAnalyzer.service;

import org.json.simple.JSONObject;

public interface GetDataService {
	
	public abstract JSONObject seeMeta();
	
	public abstract JSONObject seeData();
}