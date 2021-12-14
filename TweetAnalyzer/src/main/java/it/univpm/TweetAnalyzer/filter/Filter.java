package it.univpm.TweetAnalyzer.filter;

import org.json.simple.JSONObject;

public interface Filter {

	public abstract JSONObject filter();
}