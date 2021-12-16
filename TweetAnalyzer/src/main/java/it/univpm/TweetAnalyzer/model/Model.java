package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;

/**
 * Questa classe rappresenta il modello che si ottiene effettuando una ricerca 
 * Si hanno dei parametri comuni di Tweet e User che sono stati raccolti in questa classe
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class Model {

	/**
	 * Attributi di Model: data, id, location
	 */
	private LocalDate date;
	private long id;
	private String location;

	/**
	 * Questo è il costruttore
	 * 
	 * @param date sta ad indicare la data
	 * @param id sta ad indicare il numero di riconoscimento
	 * @param location sta ad indicare il luogo 
	 */
	public Model(LocalDate date, long id, String location) {
		this.date = date;
		this.id = id;
		this.location = location;
	}

	/**
	 * Metodo che restituisce la data in formato LocalDate
	 * 
	 * @return date
	 */
	public LocalDate getDatatime() { return date; }

	/**
	 * Metodo che restituisce il numero identificativo come long
	 * 
	 * @return id
	 */
	public long getId() { return id; }

	/**
	 * Metodo che restituisce il luogo come stringa
	 * 
	 * @return location
	 */
	public String getLocation() { return location; }
}