package it.univpm.TweetAnalyzer.stats;

import java.util.HashMap;

/**
 * Questa interfaccia viene utilizzata per le diverse statistiche
 * Scelta di inserire un solo metodo perchè il tipo e i parametri sono gli stessi per tutte le classi specifiche
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface Stats {

	/**
	 * Metodo che restituisce una HashMap delle statistiche
	 * 
	 * @return una HashMap di stats
	 */
	public abstract HashMap<String, Float> stats();
}