<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Location Coordinates</title>
</head>
<body>
    <h1>User Location Information</h1>
    <h2>Message: <%= request.getAttribute("msg") %></h2>
    <h2>City: <%= request.getAttribute("city") %></h2>
    <h2>Latitude: <%= request.getAttribute("latitude") %></h2>
    <h2>Longitude: <%= request.getAttribute("longitude") %></h2>
</body>
</html>
