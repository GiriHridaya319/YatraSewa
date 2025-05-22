<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	//Get the session and request objects
	
     

    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/addBus.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
</head>
<body>

<%
HttpSession agentSession = request.getSession();
	String userSession = (String) agentSession.getAttribute(StringUtils.AGENT_ID);

	String cookieUsername = null;
	String cookieSessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(StringUtils.AGENT))
		cookieUsername = cookie.getValue();
			if (cookie.getName().equals(StringUtils.JSESSIONID))
		cookieSessionID = cookie.getValue();
		}
	}
	%>


    <div class="container">
        <!-- Welcome Section -->
        <div class="card welcome-section" style="background-image: url('${pageContext.request.contextPath}/resources/other/addBus.jpeg');">
            <h2>WELCOME Another Bus In </h2>
            <h2>YatraSewa </h2>
            <button type="button" class="signin-btn" onclick="location.href='${pageContext.request.contextPath}/BusesServlet'">
    Go Back
</button>
            
            
        </div>

        <!-- Sign Up Section -->
        <div class="card signup-section">
        

        <h2>Hello <%=cookieUsername%></h2>
        <p>Add a new Bus </p>
            
           
            <form action="${pageContext.request.contextPath}/AddBusServlet" method="post"  class="signup-form">
    <input type="text" name = "busId"  placeholder="Bus ID" required>
    <input type="text" name = "busName" placeholder="Bus Name" required>

    <!-- Starting Location Dropdown -->
    <select id="starting-location" name = "pickup" required>
        <option value="">Select Starting Location</option>
        <option value="kathmandu">Kathmandu</option>
        <option value="pokhara">Pokhara</option>
        <option value="lalitpur">Lalitpur</option>
        <option value="bhaktapur">Bhaktapur</option>
        <option value="biratnagar">Biratnagar</option>
        <option value="birgunj">Birgunj</option>
        <option value="chitwan">Chitwan</option>
        <option value="nepalgunj">Nepalgunj</option>
        <option value="janakpur">Janakpur</option>
        <option value="dharan">Dharan</option>
        <option value="butwal">Butwal</option>
        <option value="hetauda">Hetauda</option>
        <option value="damak">Damak</option>
        <option value="bhimdatta">Bhimdatta</option>
        <option value="itahari">Itahari</option>
    </select>

    <!-- Destination Dropdown -->
    <select id="destination" name = "destination" required>
        <option value="">Select Destination</option>
        <option value="kathmandu">Kathmandu</option>
        <option value="pokhara">Pokhara</option>
        <option value="lalitpur">Lalitpur</option>
        <option value="bhaktapur">Bhaktapur</option>
        <option value="biratnagar">Biratnagar</option>
        <option value="birgunj">Birgunj</option>
        <option value="chitwan">Chitwan</option>
        <option value="nepalgunj">Nepalgunj</option>
        <option value="janakpur">Janakpur</option>
        <option value="dharan">Dharan</option>
        <option value="butwal">Butwal</option>
        <option value="hetauda">Hetauda</option>
        <option value="damak">Damak</option>
        <option value="bhimdatta">Bhimdatta</option>
        <option value="itahari">Itahari</option>
    </select>
    
	    <label for="destination">Date:</label>
    <input type="date" placeholder="Date" name = "date" required>
    
    <!-- Departure and Reaching Time Fields -->
        <label for="destination">Departure Time:</label>
    <input type="time" placeholder="Departure time" name = "departureTime" required>
        <label for="destination">Reaching Time:</label>
    <input type="time" placeholder="Reaching time" name = "reachingTime"required>	
    
    <input type="text" placeholder="Price" name = "price" required>
    <input type="text" placeholder="Description" name = "description" required>
    <input type="text" placeholder="Your agent ID" name = "agentID" required>
    <button type="submit" class="signup-btn">Submit</button>
</form>


        </div>
    </div>
</body>
</html>