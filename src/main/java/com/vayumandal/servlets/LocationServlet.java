package com.vayumandal.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class LocationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ipInfoURL = "http://ipinfo.io";
        String city = null;
        String latitude = null;
        String longitude = null;
        String msg = null;

        try {
            URL url = new URL(ipInfoURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            String jsonResult = result.toString();

            // Extracting city, latitude, and longitude from the JSON response
            int cityIndex = jsonResult.indexOf("\"city\":") + 7;
            int cityEndIndex = jsonResult.indexOf(",", cityIndex);
            city = jsonResult.substring(cityIndex, cityEndIndex).replace("\"", "").trim();

            int locIndex = jsonResult.indexOf("\"loc\":") + 7;
            int locEndIndex = jsonResult.indexOf("}", locIndex);
            String loc = jsonResult.substring(locIndex, locEndIndex).replace("\"", "").trim();
            latitude = loc.split(",")[0];
            longitude = loc.split(",")[1];

            msg = "Success";
        } catch (Exception e) {
            msg = "Fail to Detect Your Current Location. Try to Enter User Location Manually or Check Your Internet Connection.";
        }

        // Set attributes to pass to JSP
        request.setAttribute("city", city);
        request.setAttribute("latitude", latitude);
        request.setAttribute("longitude", longitude);
        request.setAttribute("msg", msg);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = request.getRequestDispatcher("2. user cordinate printing ok.jsp");
        dispatcher.forward(request, response);
    }
}
