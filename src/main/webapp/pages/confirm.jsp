<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/confirm.css">
</head>
<body>
<div class="confirmation-container">
        <div class="icon-circle">
            <i class="checkmark">&#10004;</i>
        </div>
        <h2>Booking Success</h2>
        <p>Thank you! We have sent a confirmation email to <span id="user-email">your.email@example.com</span>.<br>
        Please check your inbox to confirm your booking.</p>
        
        <button type="button"  id="close-btn" onclick="location.href='${pageContext.request.contextPath}/BusesServlet'">
    Close
</button>
            
    </div>
</body>

</html>