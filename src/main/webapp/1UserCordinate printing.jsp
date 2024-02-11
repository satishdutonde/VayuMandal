<%@ page import="java.net.URL" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location Coordinates</title>
</head>
<body>

<%
    String ipInfoURL = "http://ipinfo.io";
    String city = null;
    String latitude = null;
    String longitude = null;
    String msg=null;

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

        // Print the user's location information
        out.println("City: " + city + "<br>");
        out.println("Latitude: " + latitude + "<br>");
        out.println("Longitude: " + longitude);

    } catch (Exception e) {
        msg="Fail to Detect Your Current Location. Try to Enter User Location Manually or Check Your Internet Connection.";
    }
%>

<%
    // Redirect to the servlet with parameters
    response.sendRedirect("/YourServletPath? city=" + city + "&long=" + longitude +"&lat="+latitude+"&msg="+msg);
%>

</body>
</html>
