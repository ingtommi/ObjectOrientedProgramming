package it.univpm.TweetAnalyzer.filter;

import org.json.simple.JSONObject;

/**
 * Questa interfaccia viene utilizzata per i diversi filtri
 * Scelta di inserire un solo metodo perchè il tipo e i parametri sono gli stessi per tutte le classi specifiche
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface Filter {

	/**
	 * Metodo che restituisce un JSONObject con i dati filtrati
	 * 
	 * @return un JSONObject contenente i dati filtrati
	 */
	public abstract JSONObject filter();
}