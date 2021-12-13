package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;

public class Super {
	
	private LocalDate date;
	private long id;
	private String location;
	
	public Super(LocalDate date, long id, String location) {
		this.date = date;
		this.id = id;
		this.location = location;
	}

	public LocalDate getDatatime() { return date; }
	public long getId() { return id; }
	public String getLocation() { return location; }
}