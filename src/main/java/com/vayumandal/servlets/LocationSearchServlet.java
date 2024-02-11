package com.vayumandal.servlets;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/LocationSearchServlet")
public class LocationSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("locationInput");

        // Use OpenStreetMap Nominatim API for real-time place suggestions
        String suggestions = getSuggestions(location);

        // Set attributes for use in the JSP
        request.setAttribute("locationName", location);
        request.setAttribute("latitude", "Replace with actual latitude");
        request.setAttribute("longitude", "Replace with actual longitude");
        request.setAttribute("suggestions", suggestions);

        // Forward to the same JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private String getSuggestions(String location) throws IOException {
        StringBuilder result = new StringBuilder();

        // Use OpenStreetMap Nominatim API for real-time place suggestions
        String apiUrl = "http://nominatim.openstreetmap.org/search?format=json&q="
                + URLEncoder.encode(location, "UTF-8");

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
        }

        scanner.close();

        JSONArray suggestionsArray = new JSONArray(result.toString());
        result.setLength(0);

        if (suggestionsArray.length() > 0) {
            result.append("<ul>");
            for (int i = 0; i < Math.min(suggestionsArray.length(), 5); i++) {
                JSONObject suggestion = suggestionsArray.getJSONObject(i);
                result.append("<li>").append(suggestion.getString("display_name")).append("</li>");
            }
            result.append("</ul>");
        }

        return result.toString();
    }
}
