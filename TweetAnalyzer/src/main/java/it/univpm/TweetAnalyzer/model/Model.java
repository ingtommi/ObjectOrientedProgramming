package it.univpm.TweetAnalyzer.model;

<<<<<<< HEAD
=======
import java.text.ParseException;
>>>>>>> 64254ac7266382251bd06d42c4b89b590268bd18
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Model {
	
	private String created_at;
	private long id;
	private String location;
	private LocalDate datatime = null;
	
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
<<<<<<< HEAD
	
	public LocalDate parseData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		LocalDateTime dt = LocalDateTime.parse(created_at, formatter);
		datatime = dt.toLocalDate();
		return datatime;
	}
=======

	public LocalDate ParseDataTime() throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		LocalDateTime dt = LocalDateTime.parse(created_at, formatter);
		return dt.toLocalDate();
	}
	
	
	
	
>>>>>>> 64254ac7266382251bd06d42c4b89b590268bd18
}