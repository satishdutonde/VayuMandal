package com.vayumandal.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiKeysReader {

	private String openWeatherMapApiKey;
	private String googleApiKey;
	    public void loadApiKeys() 
	    {
	    	System.out.println("Pass : Loading Api Keys from properties file");
	        try (InputStream fis = getClass().getClassLoader().getResourceAsStream("com/vayumandal/util/ApiKeys.properties")) {
	            Properties properties = new Properties();
	            properties.load(fis);
	            openWeatherMapApiKey = properties.getProperty("openWeatherMapApiKey");
	            googleApiKey = properties.getProperty("googleApiKey");
	            System.out.println("       Api Keys Loading Success");
	        } catch (IOException e) {
	        	System.out.println("Fail : Api Keys Loading Fail");
	        	System.out.println("       Check ApiKeys.properties file is present in correct place ");
	            e.printStackTrace();
	        }
	    }

	    public String getOpenWeatherMapApiKey() {
	        return openWeatherMapApiKey;
	    }

	    public String getGoogleApiKey() {
	        return googleApiKey;
	    }
}
