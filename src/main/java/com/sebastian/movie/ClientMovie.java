package com.sebastian.movie;

import java.util.Map;
import java.util.TreeMap;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientMovie {
	
	private String uri;
	private JSONObject json;
	private Map<Integer, String> movieMap; //< Year, "{title} [{year}] - {poster_url}"
	
	public ClientMovie(String searchTerm) {
		this.uri = "http://www.omdbapi.com/?s="+searchTerm+"&type=movie&plot=short&r=json";
		movieMap = new TreeMap<Integer, String>();
	}	
	
	/**
	 * showResultExercise will print out the sorted information 
	 * stored in the Map - movieMap -.
	 * 
	 * It will do just a simply System.out.println
	 * */
	private void showResultExercise() {
		
		//Here I will print out the information required sort by year ASC
		for(Map.Entry<Integer, String> entry : movieMap.entrySet()) {
			System.out.println(entry.getValue());
		}
		
		//This is the second line to show
		System.out.println("\n=> "+movieMap.size()+" result(s) found");
	}
	
	/**
	 * parseJSON will treat the json response to get the result
	 * required by the exercise.  
	 * 
	 * If the JSON response is False then a small error message will be shown,
	 * otherwise the information to be shown on screen it will be store as a map
	 * value which key will be the year. By this way and due that the TreeMap 
	 * order naturally all their keys we will be able to show everything sorted ASC by year.
	 * 
	 * @param String response retrieved by the query to the OMDb API
	 * @return true if the string was parse with no problems, otherwise false
	 * */
	private boolean parseJSON(String output) {
		
		String year, title, poster;				
		json = new JSONObject(output);	
		boolean result = false;
		
		if(json.get("Response").equals("False")) {
			System.out.println("Movie not found!");			
		}
		else {
			
			JSONArray array = json.getJSONArray("Search");
			
			
			for(int i = 0 ; i < array.length() ; i++){
			    
				//The movie has to contain a valid poster link
			    if(!array.getJSONObject(i).getString("Poster").equals("N/A")) {
			    	
			    	title = array.getJSONObject(i).getString("Title");
			    	// Here I replace all non-digits
			    	year = array.getJSONObject(i).getString("Year").replaceAll("\\D+",""); 
			    	poster = array.getJSONObject(i).getString("Poster");
			    	
			    	movieMap.put(Integer.parseInt(year), title+" ["+year+"] - "+poster);
			    }
			    
			}
			result = true;
		}	
		
		return result;
	}
	
	
	/**
	 * In this method the connection to the API will be done, then
	 * the Json object retrieve by it will be parsed following
	 * the step in which all the information parsed will be shown.
	 * 
	 * */
	public void executeClientMovie() {		
		
		try {			
			Client client = Client.create();
			WebResource webResource = client.resource(uri);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);		
			
			if(response.getStatus() == 200) {
				String output = response.getEntity(String.class);		
				
				//Here we will show the result we need for the exercise
				if( parseJSON(output) ) {
					showResultExercise();
				}
				
			}
			else {
				System.out.println("Failed : HTTP error code : " + response.getStatus());
			}		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
