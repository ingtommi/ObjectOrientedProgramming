package it.univpm.TweetAnalyzer.service;

import org.json.simple.JSONObject;

/**
 * Questa interfaccia viene implementata per visualizzare dati e metadati
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface GetDataService {

	/**
	 * Metodo che permette di visualizzare metadati
	 * 
	 * @return un JSONObject di metadati
	 */
	public abstract JSONObject seeMeta();

	/**
	 * Metodo che permette di visualizzare dati
	 * 
	 * @return un JSONObject di dati
	 */
	public abstract JSONObject seeData();
}