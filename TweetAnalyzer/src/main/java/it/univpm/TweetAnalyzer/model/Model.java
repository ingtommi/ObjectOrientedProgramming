package it.univpm.TweetAnalyzer.model;

public class Model {
	
	private String created_at;
	private long id;
	private String location;
	
	public Model(String created_at, long id, String location) {
		this.created_at = created_at;
		this.id = id;
		this.location = location;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}