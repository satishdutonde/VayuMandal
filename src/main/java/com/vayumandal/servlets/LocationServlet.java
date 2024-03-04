package com.vayumandal.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vayumandal.services.UserLocationDetector;

@SuppressWarnings("serial")
@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	System.out.println("Pass : LocationServlet Called");
    	String lat;
    	String lon;
    	String errorMsg;

       // to GetUserLocation 
        UserLocationDetector userLocationDetector=new UserLocationDetector();
        userLocationDetector.detectUserLocation(); // to detect user location
        lat=userLocationDetector.getLat();
        lon=userLocationDetector.getLon();
        errorMsg=userLocationDetector.getErrorMsg();
        
        if(lat != null && lon != null)  // if coordinates is not null then only --> call WeatherServlet 
        {
        	System.out.println("Pass : Forwording UserLocation to WeatherServlet");
        	request.setAttribute("lat", lat);
        	request.setAttribute("lon", lon);

        	// Forward the request to the WeatherServlet
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WeatherServlet");
        	dispatcher.forward(request, response);
        	
        }
        else 
        {
        	request.setAttribute("errorMsg", errorMsg);
        	
        	// Forward to index.jsp
        	request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
          
    }
     
}