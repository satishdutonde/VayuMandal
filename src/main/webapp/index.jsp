<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String lat=(String)request.getAttribute("lat");
    String lon = (String)request.getAttribute("lon");
	String place=(String)request.getAttribute("place"); 
	String country=(String)request.getAttribute("country");
	String weatherMain=(String)request.getAttribute("weatherMain");
	String temp=(String)request.getAttribute("temp");
	String feelLike=(String)request.getAttribute("feelLike");
	String windSpeed=(String)request.getAttribute("windSpeed");
	String visibility=(String)request.getAttribute("visibility");
	String humidity=(String)request.getAttribute("humidity");
	String pressure=(String)request.getAttribute("pressure");
	String errorMsg=(String)request.getAttribute("errorMsg");
	String searchError=(String)request.getAttribute("searchError");
	
	String googleApiKey=(String)request.getAttribute("googleApiKey");
	
	
	// Base Condition to Avaoid Loop
    if (lat == null && lon==null && errorMsg == null) // errorMsg: if errorMsg is "not null" that mean our servlet already called.
    {
        request.getRequestDispatcher("/LocationServlet").forward(request, response); // detect user cordinate Based on IP
		if(lat !=null && lon != null)
        {
        	request.getRequestDispatcher("/WeatherServlet").forward(request, response); // get weather info
        }
    } 
%>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>VayuMandal</title>
		<link rel="stylesheet" href="css/styles.css">
		<script src="js/script.js" defer></script>
	</head>

<style>

    #searchBox{
                -webkit-appearance: none; /* Remove default styling */
        -moz-appearance: none;
        appearance: none;
    }




</style>


<body>
	<!-- Header Container -->
	<div id="headerContainer">
		<img id="logo" src="img\app logo2.png" alt="Logo"> 
		<img id="locLogo" src="img\loc.png" alt="locLogo"> 
		
		
		<input type="text" id="searchBox" list="places" placeholder="Search Location...">
		<datalist id="places"></datalist>
		
		<form id="placeForm" action="WeatherServlet" method="get">
    		<input type="hidden" id="lat" name="lat">
   			<input type="hidden" id="lon" name="lon">
    		<input id="searchButton" type="image" src="img\search.png" alt="Submit" disabled>
  		</form>
	</div>
		<!-- Middle Container -->
	<div id="midContainer">
		<!-- mid Left Container -->
		<div id="midLeftContainer">
			<img src="img/homeIcon.png" id="homeIcon" title="homeIcon">
			<p id="placeName"><%= place %>, <%= country %></p>
			<img src="img/tempIcon.png" id="tempIcon" title="tempIcon">
			<p id="currentTemp"><%= temp %><sup>oC</sup></p>
			<p id="main"><%= weatherMain %></p>
			<p id="feelLike">Feel Like <%= feelLike %><sup>oC</sup></p>
			<table>
				<tr class="prop">
					<td>Wind</td>
					<td>Humidity</td>
					<td>Visibility</td>
					<td>Pressure</td>
				</tr>
				<tr class="values">
					<td><%= windSpeed %> km/h</td>
					<td><%= visibility %>%</td>
					<td><%= humidity %> km</td>
					<td><%= pressure %> mb</td>
				</tr>
			</table>
		</div>
		<!-- mid Right Container -->
		<div id="midRightContainer">
			<p id="title">Location on Google Map</p>
			<iframe id="map"
				src="https://www.google.com/maps/embed/v1/view?key=<%= googleApiKey %>&center=<%= lat %>,<%= lon %>&zoom=15&maptype=satellite">
			</iframe>
		</div>
	</div>

	<!-- Footer Container -->
	<div id="footerContainer">Copyright &#169; [2024] VayuMandal. All Rights Reserved.</div>
</body>
</html>