package it.univpm.TweetAnalyzer.filter;

import org.json.simple.JSONObject;

/**
 * Questa interfaccia viene utilizzata per i diversi filtri
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface Filter {

	/**
	 * Metodo che restituisce un JSONObject dei filtri
	 * @return JSONObject di filter
	 */
	public abstract JSONObject filter();
}