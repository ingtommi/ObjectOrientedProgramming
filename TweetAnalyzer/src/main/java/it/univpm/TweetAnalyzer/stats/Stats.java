package it.univpm.TweetAnalyzer.stats;

import java.util.HashMap;

/**
 * Questa interfaccia viene utilizzata per le diverse statistiche
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface Stats {

	/**
	 * Metodo che restituisce una HashMap delle statistiche
	 * @return HashMap di stats
	 */
	public abstract HashMap<String, Float> stats();
}