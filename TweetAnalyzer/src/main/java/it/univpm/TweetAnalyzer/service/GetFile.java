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

	public GetFile(){}

	//TODO:aggiungere eccezioni;

	public ArrayList<Location> getFile() {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("listaComuni.json"));
		JSONObject jsonObject = (JSONObject) object;
		JSONArray JSONcityList =  (JSONArray)jsonObject.get("Location");
		if (JSONcityList != null) { 
			for (int i=0;i<(JSONcityList).size();i++){ 
				JSONObject obj = (JSONObject) JSONcityList.get(i);
				String city = (String) obj.get("City");
				String province = (String) obj.get("Province");
				String region = (String) obj.get("Region");
				cityList.add(new Location(city,province,region));
			} 
		} 
		return cityList;
	}		

}
