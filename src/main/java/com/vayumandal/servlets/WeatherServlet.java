package com.vayumandal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vayumandal.services.WeatherDataFetcher;
import com.vayumandal.util.ApiKeysReader;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Pass : WeatherServlet Called");

		// reading coordinates coming from LocationServlet 
		String lat = (String) request.getAttribute("lat");
		String lon = (String) request.getAttribute("lon");
		String errorMsg ="";
		
		
		// Reading Coordinates coming from index.jsp  ----> 
		if(lat==null || lon==null)
		{
			lat = (String) request.getParameter("lat");
			lon = (String) request.getParameter("lon");
		}
		

		
		if(lat==null || lon == null) // Location Servlet already check this condition so problem in placeForm if it execute 
		{
			System.out.println("Fail: Javascript Sending \"null\" value for Latitude And Longitude to WeathrServlet the throw placeForm");
			System.out.println("      Problem while getting Cordinates from Nominatim Geocodung Api in JavaScript");
			System.out.println("      It may be Network Problem or User Not Selecting Place from Suggested List");
			
			String searchError ="Check Your Internet Connection / Select Location From Suggested List ";
			request.setAttribute("searchError", searchError);
		}
		else
		{
			// to GetWeatherData
			WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
			ApiKeysReader apiKeysReader = new ApiKeysReader();
			apiKeysReader.loadApiKeys(); // Load Api Keys 
			String openWeatherMapApiKey = apiKeysReader.getOpenWeatherMapApiKey(); // get OpenWeatherMapApiKey
			if(openWeatherMapApiKey != null)
			{
				weatherDataFetcher.fetchWeatherData(lat, lon, openWeatherMapApiKey); // to FetWeatherData
				// get errorMsg 
				errorMsg = weatherDataFetcher.getErrorMsg();
				
				if (errorMsg == null)
				{
						// get weather data & GoogleApiKey
					String place = weatherDataFetcher.getPlace();
					String country = weatherDataFetcher.getCountry();
					String weatherMain = weatherDataFetcher.getWeatherMain();
					String temp = weatherDataFetcher.getTemp();
					String feelLike = weatherDataFetcher.getFeelLike();
					String windSpeed = weatherDataFetcher.getWindSpeed();
					String visibility = weatherDataFetcher.getVisibility();
					String humidity = weatherDataFetcher.getHumidity();
					String pressure = weatherDataFetcher.getPressure();
					String googleApiKey = apiKeysReader.getGoogleApiKey();
					
					// Seat weather data, GoogleApiKey, and  lat and long also
					request.setAttribute("lat", lat);
					request.setAttribute("lon", lon);
					request.setAttribute("place", place);
					request.setAttribute("country", country);
					request.setAttribute("weatherMain", weatherMain);
					request.setAttribute("temp", temp);
					request.setAttribute("feelLike", feelLike);
					request.setAttribute("windSpeed", windSpeed);
					request.setAttribute("visibility", visibility);
					request.setAttribute("humidity", humidity);
					request.setAttribute("pressure", pressure);
					request.setAttribute("googleApiKey", googleApiKey);	
				}
				else
				{
					request.setAttribute("errorMsg", errorMsg);
				}
	
			}	
		}
		
		// Forward to index.jsp
		System.out.println("Pass : Forwording Request to JSP Page");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}


