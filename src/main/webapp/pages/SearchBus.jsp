<%@page import="Utils.StringUtils"%>
<%@page import="model.BusModel"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/buses.css">
</head>
<body>

<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
<section class="search">
	    <!-- search bar starts -->
	    <div class="search-bar">
	        <!-- Dropdown start -->
	        <div class="dropdown">
	          <div id="drop-text" class="dropdown-text">
	            <span id="span">Service Name</span>
	          </div>
	          <ul id="list" class="dropdown-list">
	            <li class="dropdown-list-item">Bus Name</li>
	           
	            
	          </ul>
	        </div>
	        <!-- Dropdown ends -->
	  
	        <!-- Search box input start -->
	        <div class="search-box">
	          <form>
		          <input type="text" id="search-input" placeholder="Search by Bus Name..." name="search"/>
		          <button type="submit" style="height: 30px; margin-right: 11px;"><img src="<%=contextPath%>/resources/other/search.png"></button>
		          <a href="<%=contextPath + StringUtils.SERVLET_URL_BUSES%>"><img src="<%=contextPath%>/resources/other/clear.png" style="height: 30px;"></a>  
	          </form>
	        </div>
	        
	         <!-- Search box input ends -->
	    </div>
	    <!-- search bar ends -->
	    
	</section>
 
 
 <c:if test="${empty busLists}">
					<p>No buses information found.</p>
				</c:if>
			
	        	<c:forEach var="bus" items="${busLists}">
	        	
		<div class="card">
        	<div class="bus-info">
            	<div class="bus-details">
                	<h2>${bus.busName}</h2>
                	<p>${bus.description}</p>
            <br>
            <br>
                <b><h2>Date : ${bus.date} </h2></b>
            </div>
            <div class="timing">
                <h4>${bus.departureTime}</h4>
                <p>|</p>
                <p>|</p>
                <p>|</p>
                <h4>${bus.reachingTime}</h4>
            </div>
            <div class="route">
                <p>${bus.pickup} </p>
                <p>|</p>
                <p>|</p>
                <p>|</p>
                
                <p>${bus.destination} </p>
            </div>
            <div class="extras">
                <div class="price">
                    <h4>NPR:${bus.price}</h4>
                    
                </div>
            </div>
        </div>

        <div class="agent">
            <div class="agentname">
                <c:forEach var="agent" items="${AgentMap}">
       <c:if test="${bus.agentUsername eq agent.value.agentID}">
       
           <div>
               <h3>${agent.value.agentPhoneNumber}</h3>
                   <p>${agent.value.agentFirstName}</p>
                   <p>${agent.value.agentLastName}</p>
           </div>
       </c:if>
   </c:forEach>
   
            </div>
            
        </div>
         <form id="viewSeatsForm-${bus.busID}" action="${pageContext.request.contextPath}/SeatSelection" method="post">
    <input type="hidden" name="busId" value="${bus.busID}">
    <button type="submit" class="view-seats-btn">View Seats</button>
</form>
        
    </div>
    
				</c:forEach>
</body>
</html>