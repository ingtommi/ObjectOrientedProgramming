package it.univpm.TweetAnalyzer.service;

import java.time.LocalDate;
import java.util.ArrayList;

import it.univpm.TweetAnalyzer.exception.IsEmptyException;
import it.univpm.TweetAnalyzer.exception.WrongMethodException;
import it.univpm.TweetAnalyzer.model.Tweet;
import it.univpm.TweetAnalyzer.model.User;

/**
 * Questa interfaccia viene utilizzata per la chiamata API
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface APICallService {

	/**
	 * Metodo che restituisce un ArrayList di Tweet
	 * @return ArrayList di Tweet
	 */
	public abstract ArrayList<Tweet> getTweets();

	/**
	 * Metodo che restituisce un ArrayList di User
	 * @return ArrayList di User
	 */
	public abstract ArrayList<User> getUsers();

	/**
	 * Metodo che restituisce una stringa corrispondente all'URL per effettuare la ricerca
	 * @return stringa corrispondente all'URL utilizzato per effettuare la ricerca
	 * @throws WrongMethodException se i metodi inseriti sono diversi da AND o OR
	 */
	public abstract String apiBuild() throws WrongMethodException;

	/**
	 * Metodo che restituisce una stringa che conferma il salvataggio
	 * @return stringa con conferma di salvataggio avvenuto
	 * @throws WrongMethodException se i metodi inseriti sono diversi da AND o OR
	 * @throws IsEmptyException se la ricerca non ha raccolto alcun tweet
	 */
	public abstract String saveData() throws WrongMethodException, IsEmptyException;

	/**
	 * Metodo che converte la stringa di data in LocalDate
	 * @param created_at: stringa che rappresenta la data
	 * @return data in formato LocalDate 
	 */
	public abstract LocalDate parseData(String created_at);
}