package com.vayumandal.services;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
public class UserLocationDetector 
{
	private String lat=null;
	private String lon=null;
	private String errorMsg=null;
	
    public void detectUserLocation() {
        try {

        	System.out.println("Pass : Detecting User Location based on IP");
        	
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
            
            JSONObject jsonObject = new JSONObject(jsonResult);
            // Extract latitude and longitude directly from "loc" field
            String[] locArray = jsonObject.getString("loc").split(",");
            lat = locArray[0];
            lon = locArray[1];
            
           System.out.println("Pass : User Location Detected");
           System.out.println("       Lat :"+lat);
           System.out.println("       Lon :"+lon);
        
        } 
        catch (IOException e) 
        {
        	System.out.println("Fail : Detecting User Location is Fail");
            errorMsg = "Failed to detect your current location. /n Please try again or enter your location manually.";
        } 
    }

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public String getErrorMsg() {
		return errorMsg;
	}	
}
