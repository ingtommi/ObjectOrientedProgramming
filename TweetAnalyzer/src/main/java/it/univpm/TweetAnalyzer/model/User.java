package it.univpm.TweetAnalyzer.model;

public class User {


	private long id;
	private String place;

	public User(long id, String place) {
		super();
		this.id = id;
		this.place = place;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
