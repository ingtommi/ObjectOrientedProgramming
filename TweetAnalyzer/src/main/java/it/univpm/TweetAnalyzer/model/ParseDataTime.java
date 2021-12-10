package it.univpm.TweetAnalyzer.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.json.simple.parser.ParseException;


public class ParseDataTime {

	private LocalDate datatime;

	public ParseDataTime() {
		this.datatime = null;
	}

	public LocalDate getParseDataTime() {
		return datatime;
	}


	public void setParseDataTime(String created_at) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		LocalDateTime dt = LocalDateTime.parse(created_at, formatter);
		this.datatime = dt.toLocalDate();
	}

}
