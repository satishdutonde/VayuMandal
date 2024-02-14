package com.vayumandal.servlets;

import java.io.IOException;


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
        
    	System.out.println("LocationServlet Called"); // to track flow of app on terminal
    	
    	// Declare Weather Properties 
    	String lat =null;
        String lon =null;
        String msg=null;
        
        UserLocationDetector userLocationDetector=new UserLocationDetector();
        lat=userLocationDetector.getLat();
        lon=userLocationDetector.getLon();
        msg=userLocationDetector.getMsg();
        
        if(msg==null)
        {
            // Set weather property in request
            request.setAttribute("lat", lat);
            request.setAttribute("lon", lon);
            System.out.println("lat and long Setted "); // to track flow of app
        }
        else
        {
        	request.setAttribute("msg", msg);
        	System.out.println("Exception occure While Detecting User Cordinates");
        }
    
        
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Forward to index.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}