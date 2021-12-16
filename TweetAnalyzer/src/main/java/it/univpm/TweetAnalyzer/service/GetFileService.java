package it.univpm.TweetAnalyzer.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import it.univpm.TweetAnalyzer.model.Location;

/**
 * Questa interfaccia viene utilizzata per implementare il metodo GetFile
 * @author Marco Ciampichetti
 * @author Tommaso Fava
 */

public interface GetFileService {

	/**
	 * Metodo che permette di ottenere una lista dei comuni italiani con le rispettive province e regioni
	 * @return ArrayList di Location
	 * @throws FileNotFoundException se il tentativo di aprire il file indicato da un percorso specificato è fallito
	 * @throws IOException se si è verificata un'eccezione I/O di qualche tipo
	 * @throws ParseException se si è verificato un errore durante il parsing
	 */
	public abstract ArrayList<Location> getFile() throws FileNotFoundException, IOException, ParseException;
}