package com.vayumandal.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/temp")
public class temp extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	System.out.println("====================================");
    	System.out.println("Temp Servlet Called");
    	// Retrieve latitude and longitude from the form
        String latitude = request.getParameter("lat");
        String longitude = request.getParameter("lon");
        
        // Print latitude and longitude to console
        System.out.println("     Latitude : " + latitude);
        System.out.println("     Longitude: " + longitude);
    }
}
