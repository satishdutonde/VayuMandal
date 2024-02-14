<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String lat=(String)request.getAttribute("lat");
    String lon = (String)request.getAttribute("lon");
	String city=(String)request.getAttribute("city"); 
	String country=(String)request.getAttribute("country");
	
	String weatherMain=(String)request.getAttribute("weatherMain");
	String weatherDescription=(String)request.getAttribute("weatherDescription");
	String temp=(String)request.getAttribute("temp");
	String feelLike=(String)request.getAttribute("feelLike");
	String tempMin=(String)request.getAttribute("tempMin");
	String tempMax=(String)request.getAttribute("tempMax");
	String windSpeed=(String)request.getAttribute("windSpeed");
	String visibility=(String)request.getAttribute("visibility");
	String humidity=(String)request.getAttribute("humidity");
	String pressure=(String)request.getAttribute("pressure");
	String msg=(String)request.getAttribute("msg");
	
    if (lat == null && lon==null && msg == null) // msg contain exception related issue then its servlet already called and this is a second request
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
	</head>

<style>

</style>


<body>
	<!-- Header Container -->
	<div id="headerContainer">
		<img id="logo" src="img\app logo2.png" alt="Logo"> 
		<img id="locLogo" src="img\loc.png" alt="locLogo"> 
		<input type="text" id="searchBox" placeholder="Search Location...">
		<input id="searchButton" type="image" src="img\search.png" alt="Submit">
	</div>

	<!-- Middle Container -->
	<div id="midContainer">
		<!-- mid Left Container -->
		<div id="midLeftContainer">
			<img src="img/homeIcon.png" id="homeIcon" title="homeIcon">
			<p id="placeName">Mehkar, Maharashtra</p>
			<img src="img/tempIcon.png" id="tempIcon" title="tempIcon">
			<p id="currentTemp"> 32<sup>oC</sup></p>
			<p id="main">Mostly Cloudy</p>
			<p id="feelLike">Feel Like 35<sup>o</sup></p>
			<p id="min">Min : 23<sup>o</sup></p>
			<p id="max">Max : 30<sup>o</sup></p>
			<table>
				<tr class="prop">
					<td>Wind</td>
					<td>Humidity</td>
					<td>Visibility</td>
					<td>Pressure</td>
				</tr>
				<tr class="values">
					<td>3 km/h</td>
					<td>40%</td>
					<td>4 km</td>
					<td>1017 mb</td>
				</tr>
			</table>
		</div>
		<!-- mid Right Container -->
		<div id="midRightContainer">
			<p id="title">Location on Google Map</p>
			<iframe id="map"
				src="https://www.google.com/maps/embed/v1/view?key=AIzaSyBJ7jy05eY3WoN9VfKkBWWS6qpkkZPENnQ&center=<%= lat %>,<%= lon %>&zoom=15&maptype=satellite">
			</iframe>
		</div>
	</div>

	<!-- Footer Container -->
	<div id="footerContainer">Copyright &#169; [2024] VayuMandal. All Rights Reserved.</div>
</body>
</html>