package it.univpm.TweetAnalyzer.stats;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TweetAnalyzer.model.*;

/**
 * Questa classe implementa l'interfaccia Stats
 * ed effettua statistiche sulla geolocalizzazione
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public class GeoStats implements Stats {

	/**
	 * Attributi di luogo, tweet, utente e città
	 */

	private String location;
	private ArrayList<Tweet> tweets;
	private ArrayList<User> users;
	ArrayList<Location> cities;

	/**
	 * Questo è il costruttore
	 * @param location indica il luogo su cui si vuole fare la statistica
	 * @param tweets indica la lista dei tweet
	 * @param users indica la lista di utenti che hanno generato i tweet
	 * @param cities indica la città contenuta in listaComuni.json che verrà confrontata con il luogo
	 */
	public GeoStats (String location, ArrayList<Tweet> tweets, ArrayList<User> users, ArrayList<Location> cities) {
		this.location = location;
		this.tweets = tweets;
		this.users = users;
		this.cities = cities;
	}

	@Override
	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweet contenenti il luogo scelto per effettuare la statistica
	 * Ci sono due diverse possibilità nel caso il luogo sia scelto o meno per fare la statistica
	 * @return Hashmap contenente diversi dati in base alla presenza o meno della localizzazione
	 */

	public HashMap<String, Float> stats() {

		HashMap<String, Float> out = new HashMap<String, Float>();
		if(location == null) {
			out = this.general();
		}
		else out = this.specific();
		return out;
	}

	/**
	 * Questo metodo serve per memorizzare in una HashMap i tweet contenenti un luogo specifico 
	 * Caso in cui viene scelto un luogo
	 * @return HashMap contenente i tweet scaricati, i tweet scritti nel luogo scelto e 
	 * la percentuale dei tweet in quel luogo rispetto a quelli scaricati
	 */
	//se location != null
	private HashMap<String, Float> specific() {

		int tot = 0;
		for(int i=0; i<tweets.size(); i++) {
			if(tweets.get(i).getLocation() != null) {
				if(tweets.get(i).getLocation().toLowerCase().contains(location.toLowerCase())) {
					tot++;
				}
			}
			else if(tweets.get(i).getLocation() == null && users.get(i).getLocation() != null) {
				if(users.get(i).getLocation().toLowerCase().contains(location.toLowerCase())) {
					tot++;
				}
			}
		}
		HashMap<String, Float> spe = new HashMap<String, Float>();
		spe.put("Total tweets downloaded", (float) tweets.size());
		spe.put("Tweets written in " + location, (float) tot);
		spe.put("Percentage", 100*(float) tot/tweets.size());

		return spe;
	}

	/**
	 *  Questo metodo serve per memorizzare in una HashMap i tweet contenenti uno luogo specifico
	 *  Caso in cui non viene scelto nessun luogo
	 * @return HashMap contenente i tweet scaricati, i tweet scritti in Italia,i tweet con localizzazione non processabile,
	 *  i tweet senza localizzazione e le rispettive percentuali 
	 */
	//se location == null
	private HashMap<String, Float> general() {

		int no = 0;
		int ita = 0;
		for(int i=0; i<tweets.size(); i++) {
			boolean find = false;
			if(tweets.get(i).getLocation() == null && users.get(i).getLocation() == null) {
				no++;
			}
			else if(tweets.get(i).getLocation() != null) {
				for(int k=0; k<cities.size() && !find; k++) {
					if(tweets.get(i).getLocation().toLowerCase().contains(cities.get(k).getCity().toLowerCase()) || 
							tweets.get(i).getLocation().toLowerCase().contains(cities.get(k).getRegion().toLowerCase())) {
						find = true;
					}
				}
				if(find) {
					ita++;
				}
			}
			else if(tweets.get(i).getLocation() == null && users.get(i).getLocation() != null) {
				for(int k=0; k<cities.size() && !find; k++) {
					if(users.get(i).getLocation().toLowerCase().contains(cities.get(k).getCity().toLowerCase()) || 
							users.get(i).getLocation().toLowerCase().contains(cities.get(k).getRegion().toLowerCase())) {
						find = true;
					}
				}
				if(find) {
					ita++;
				}
			}
		}
		int rest = tweets.size()-ita-no;
		HashMap<String, Float> gen = new HashMap<String, Float>();
		gen.put("Total tweets downloaded", (float) tweets.size());
		gen.put("Tweets written in Italy", (float) ita);
		gen.put("Tweets with unprocessable location", (float) rest);
		gen.put("Tweets with null location", (float) no);
		gen.put("Percentage of tweets written in Italy", 100* (float) ita/tweets.size());
		gen.put("Percentage of tweets with unprocessable location", 100*(float) rest/tweets.size());
		gen.put("Percentage of tweets with no location", 100* (float) no/tweets.size());

		return gen;
	}
}