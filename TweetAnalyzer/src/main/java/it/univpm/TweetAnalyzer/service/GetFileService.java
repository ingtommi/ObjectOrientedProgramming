package it.univpm.TweetAnalyzer.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import it.univpm.TweetAnalyzer.model.Location;

public interface GetFileService {
	
	public abstract ArrayList<Location> getFile() throws FileNotFoundException, IOException, ParseException;
}