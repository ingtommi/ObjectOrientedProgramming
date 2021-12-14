package it.univpm.TweetAnalyzer.model;

public class Config {
	
	private String url;
	
	public Config(String url) {
		this.url = url;
	}
	
	public String getUrl() { return this.url; }
	
	public String getMex() { return "AUTHENTICATED: go to http://localhost:8080/config"; }
}