<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
	//Get the session and request objects
	HttpSession userSession = request.getSession();
	String currentUserType = (String) userSession.getAttribute("type");
	String currentUser = (String) userSession.getAttribute("username");
    String contextPath = request.getContextPath();
%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/updateBus.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<div class="container">
        <!-- Welcome Section -->
        <div class="card welcome-section" style="background-image: url('${pageContext.request.contextPath}/resources/other/addBus.jpeg');">
            <h2>Welcome!</h2>
            
            <button type="button" class="signin-btn" onclick="location.href='${pageContext.request.contextPath}/BusesServlet'"> Go Back</button>
        </div>

        <!-- Login Section -->
        <div class="card login-section">
            <h2>Update Bus Information </h2>
            
            <form action="${pageContext.request.contextPath}/ModifyBusDetailsServlet" method="post" class="login-form">
                <input type="text" placeholder="Bus Name" name  = "busName" value="${updateBus.busName}" required>
                <input type="text" placeholder="Bus destination" name = "destination"  value="${updateBus.destination}" required>
                 <input type="time" placeholder="Departure Time " name = "departureTime" value="${updateBus.departureTime}" required>
                 <input type="time" placeholder="reaching Time " name = "reachingTime" value="${updateBus.reachingTime}" required>
                 <input type="text" placeholder="Price" name = "price" value="${updateBus.price}" required>
                 <input type="text" placeholder="Description" name = "description" value="${updateBus.description}" required>
                 <input type="text" placeholder="Starting Location " name = "pickup" value="${updateBus.pickup}" required>
                 <input type="date" placeholder="Date" name = "date" name = "date" value="${updateBus.date}"  required>
                 <input type="hidden" name="update_bus_id" value="${updateBus.busID}" required>
                <button type="submit" class="login-btn">UPDATE</button>
            </form>
        </div>
    </div>

</body>
</html>