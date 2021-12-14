package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;

public class User extends Model{

	private String name;

	public User(LocalDate date, long id, String location, String name) {
		super(date,id,location);
		this.name = name;
	}

	public String getName() { return name; }
}