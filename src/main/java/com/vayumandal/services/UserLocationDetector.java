package com.vayumandal.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UserLocationDetector 
{
	private String lat=null;
	private String lon=null;
	private String msg=null;
	
    public UserLocationDetector() {
        try {

            // Make an HTTP request to IP Geolocation API
            String ipInfoURL = "https://ipinfo.io/json";
            URL url = new URL(ipInfoURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response using Scanner
            Scanner scanner = new Scanner(conn.getInputStream());
            String jsonResult = "";
            while (scanner.hasNextLine()) {
                jsonResult += scanner.nextLine();
            }
            scanner.close();

            // Parse JSON response to extract latitude and longitude
            String loc = jsonResult.split("\"loc\":")[1].trim(); //Returns the next string since the pattern was matched
			                                                     // here split method return to string but we need index 1
																 // trim () remove removes trailing whitespace (space, tab)
            loc = loc.replaceAll("\"", ""); // Remove quotation marks from all string 
            lat = loc.split(",")[0]; // split method return array--> and we are accessing first element from it which is lat value
            lon = loc.split(",")[1];
            
           System.out.println("UserLocationDetector Object Created !!"); // to track flow of app on terminal
        
        } 
        catch (IOException e) 
        {
            msg = "Failed to detect your current location. Please try again or enter your location manually.";
        } 
    }

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public String getMsg() {
		return msg;
	}
	
	

}
