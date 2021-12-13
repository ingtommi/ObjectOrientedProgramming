package it.univpm.TweetAnalyzer.model;

public class Location {
	
	private String city;
	private String province;
	private String region;
	
	public Location(String city, String province, String region) {
		this.city = city;
		this.province = province;
		this.region = region;
	}

	public String getCity() { return city; }
	public String getProvince() { return province; }
	public String getRegion() { return region; }
}