package it.univpm.TweetAnalyzer.model;

import java.time.LocalDate;

/**
 * Questa classe estende Model e 
 * modella l'utente del tweet che si ottiene nella ricerca
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class User extends Model{

	/**
	 * nome utente che ha generato il tweet
	 */
	private String name;

	/**
	 * Costruttore
	 * 
	 * @param date sta ad indicare la data di creazione del profilo utente
	 * @param id sta ad indicare il numero identificativo dell'utente
	 * @param location sta ad indicare il luogo di provenienza dell'utente
	 * @param name sta ad indicare il nome dell'utente
	 */
	public User(LocalDate date, long id, String location, String name) {
		super(date,id,location);
		this.name = name;
	}

	/**
	 * Metodo che restituisce il nome dell'utente che ha generato il tweet
	 * @return stringa che rappresenta il nome utente
	 */
	public String getName() { return name; }
}