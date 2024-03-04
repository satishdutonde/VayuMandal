package com.vayumandal.services;

import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherDataFetcher {
	private String place;
	private String country;
	private String weatherMain;
	private String temp;
	private String feelLike;
	private String windSpeed;
	private String visibility;
	private String humidity;
	private String pressure;
	private String errorMsg=null;

	public void fetchWeatherData(String lat, String lon, String openWeatherMapApiKey) {
		
		System.out.println("Pass : Fetching Weather Data For Coordinates :"+lat+","+lon);
		
		try {
			// Create URL for the API call
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid="
					+ openWeatherMapApiKey + "&units=metric");

			// Create HTTP connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// Read response using Scanner
			Scanner scanner = new Scanner(conn.getInputStream());
			String jsonResult = "";
			while (scanner.hasNextLine()) {
				jsonResult += scanner.nextLine();
			}
			scanner.close();

			// Parse JSON response
			JSONObject jsonObject = new JSONObject(jsonResult);

			// Extract required properties
			place = jsonObject.getString("name");
			country = jsonObject.getJSONObject("sys").getString("country");
			weatherMain = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
			temp = String.valueOf(jsonObject.getJSONObject("main").getInt("temp"));
			feelLike = String.valueOf(jsonObject.getJSONObject("main").getDouble("feels_like"));
			windSpeed = String.valueOf(jsonObject.getJSONObject("wind").getInt("speed"));
			int visibilityInMeters = jsonObject.getInt("visibility"); // return Visibility in Meter
			double visibilityInKilometers = visibilityInMeters / 1000; // converting meter to KiloMeter
			visibility = String.valueOf(visibilityInKilometers);
			humidity = String.valueOf(jsonObject.getJSONObject("main").getDouble("humidity"));
			pressure = String.valueOf(jsonObject.getJSONObject("main").getDouble("pressure"));
			
			System.out.println("       Weather Data Fetching success");

		} catch (IOException e) {
			System.out.println("Fail : Weather Data Fetching Fail");
			errorMsg = "Weather Data Fetching Fail.";
		}
	}

	public String getPlace() {
		return place;
	}

	public String getCountry() {
		return country;
	}

	public String getWeatherMain() {
		return weatherMain;
	}

	public String getTemp() {
		return temp;
	}

	public String getFeelLike() {
		return feelLike;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public String getErrorMsg() {
		return errorMsg;
	}


}
