package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe che estende Model e 
 * modella il tweet che si ottiene nella ricerca
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class Tweet extends Model {

	/**
	 * ArrayList di hashtag
	 */
	private ArrayList<String> hashtags;

	/**
	 * Costruttore
	 * 
	 * @param date sta ad indicare la data del tweet
	 * @param id sta ad indicare il numero identificativo del tweet
	 * @param location sta ad indicare la posizione in cui è stato scritto il tweet
	 * @param hashtags sta ad indicare la lista degli hashtag contenuti nel tweet
	 */
	public Tweet(LocalDate date, long id, String location, ArrayList<String> hashtags) {
		super(date,id,location);
		this.hashtags = hashtags;
	}

	/**
	 * Metodo che restituisce una lista di tutti gli hashtag di un tweet
	 * 
	 * @return hashtag
	 */
	public ArrayList<String> getHashtags() { return hashtags; }
}