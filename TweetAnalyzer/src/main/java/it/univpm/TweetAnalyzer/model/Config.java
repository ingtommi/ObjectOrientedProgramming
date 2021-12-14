package it.univpm.TweetAnalyzer.model;

public class Config {
	
	private String url;
	private String token;
	
	public Config(String url, String token) {
		this.url = url;
		this.token = token;
	}
	
	public String getUrl() { return this.url; }
	public String getToken() { return this.token; }
	
	public String getMex() { return "AUTHENTICATED: go to http://localhost:8080/config"; }
}