package it.univpm.TweetAnalyzer.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class APICall {
	
	private static final String apiBase = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	private String api;
	private String hashtags;
	private int count;
	private String lang;
	
	public APICall(String hashtags, int count, String lang) {
		this.hashtags = hashtags;
		hashtags.replace("#","%23"); //necessario perchè url vuole il %23 al posto di #
		this.count = count;
		this.lang = lang;
		this.api = apiBase + "q=" + this.hashtags + "&count=" + this.count + "&lang=" + this.lang;
	}
	
	public JSONObject getMeta() {
		
	}
	
	public JSONArray getData() {
		
	}
}