package it.univpm.TweetAnalyzer.model;

public class User extends Model{

	private String name;

	public User(String created_at, long id, String location, String name) {
		super(created_at,id,location);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}