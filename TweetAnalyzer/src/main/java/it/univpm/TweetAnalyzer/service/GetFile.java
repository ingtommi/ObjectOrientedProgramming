package it.univpm.TweetAnalyzer.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.TweetAnalyzer.model.Location;

public class GetFile {

	ArrayList<Location> cityList = new ArrayList<Location>();

	public GetFile() {}

	public ArrayList<Location> getFile() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("listaComuni.json"));
		JSONObject jsonObj = (JSONObject) object;
		JSONArray list =  (JSONArray) jsonObj.get("Location");
		if (list != null) { 
			for (int i=0; i<(list).size(); i++){ 
				JSONObject loc = (JSONObject) list.get(i);
				String city = (String) loc.get("City");
				String province = (String) loc.get("Province");
				String region = (String) loc.get("Region");
				cityList.add(new Location(city,province,region));
			} 
		} 
		return cityList;
	}		
}