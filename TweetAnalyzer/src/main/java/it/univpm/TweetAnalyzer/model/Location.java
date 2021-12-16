package it.univpm.TweetAnalyzer.model;
/**
 * Questa classe rappresenta il luogo dove si trova l'utente
 * 
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 *
 */
public class Location {
	
    /**
     * Attributi di location: città, provincia, regione
     */
	private String city;
	private String province;
	private String region;
	
	/**
	 * Questo è il costruttore
	 * 
	 * @param city indica la città
	 * @param province indica la provincia
	 * @param region indica la regione
	 */
	public Location(String city, String province, String region) {
		this.city = city;
		this.province = province;
		this.region = region;
	}

	/**
	 * Metodo che restituisce la città come stringa
	 * 
	 * @return city
	 */
	public String getCity() { return city; }
	
	/**
	 * Metodo che restituisce la provincia come stringa
	 * 
	 * @return province
	 */
	public String getProvince() { return province; }
	
	/**
	 * Metodo che restituisce la regione come stringa
	 * 
	 * @return region
	 */
	public String getRegion() { return region; }
	
	/**
	 * Metodo toString utilizzato per i test
	 * 
	 * @return una stringa
	 */
	
	@Override
	public String toString() {
		
		return "Città: " + this.city + " | Provincia: " + this.province + " | Regione: " + this.region;
	}
}