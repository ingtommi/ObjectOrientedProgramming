package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Model {
	
	private LocalDate date;
	private long id;
	private String location;
	
	public Model(LocalDate date, long id, String location) {
		this.date = date;
		this.id = id;
		this.location = location;
	}

	public LocalDate getDatatime() { return date; }
	public long getId() { return id; }
	public String getLocation() { return location; }
}