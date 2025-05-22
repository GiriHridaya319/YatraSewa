<%@page import="Utils.StringUtils"%>
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/header.css" />
</head>
<body>
    <div class="nav">
    <div class="logo">
    	<a href="<%=contextPath + "/HomePage.jsp"%>">
    		<img src="<%=contextPath + "/resources/other/LOGO.png"%>" alt="Logo Image" >
    	</a>
         </div>
    <div class="hamburger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
    <ul class="nav-links">
                <li><a href=
                
                "<%
        				// Conditionally set the navbar text(service) based on user type
                        if ((currentUserType == "user") || (currentUserType == "agent")) {
                            out.print(contextPath + StringUtils.SERVLET_URL_INDEX);
                        } else {
                            out.print(contextPath + StringUtils.SERVLET_URL_INDEX);
                        }
                     %>">Home</a></li>
        <li><a href="<%=contextPath + StringUtils.SERVLET_URL_BUSES%>"><%
                        // Conditionally set the navbar text(service) based on user type
                        if (currentUserType == "user" || currentUserType =="agent") {
                            out.print("Buses");
                        } else if (currentUserType == null){
                            out.print("Buses");
                        }
                    %></a></li>
        <li><a href="<%=contextPath + StringUtils.PAGE_URL_CONTACT_US%>">CONTACT US</a></li>
        <li><a href="<%=contextPath + StringUtils.PAGE_URL_BLOG%>">BLOG</a></li>
        
        <div class="nav-buttons">       
	  		<!-- Handling Buttons When Login/ Logout -->      
	        <% 
	        // if not logged in the display login and register buttons
	        if (currentUser == null) {
	        %>
	        	<a href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">
	            	<button class="register-button">Be An Agent</button>
	            </a> 
	            
	            <a href="<%=contextPath + StringUtils.PAGE_URL_LOGIN%>">
	            	<button class="login-button">Login</button>
	            </a>
	        
	            <a href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">
	            	<button class="register-button">Register</button>
	            </a> 
	              
        	 <%
        	 // else display profile and logout button
        	 } else {
        	 %>
	        	
				<a href="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT%>">
	            	<button class="logout-button">Logout</button>
	            </a>
	            
	            <form action="<%= contextPath + StringUtils.SERVLET_UPDATE_PROFILE %>" method="GET">
    <input type="hidden" name="update_user_id" value="<%= currentUser %>" />
    <button type="submit" class="register-button">Profile</button>
</form>

            <% } %>
            
        </div>
        
         
     </ul>
</div>

    <script>
        const hamburger = document.querySelector(".hamburger");
        const navLinks = document.querySelector(".nav-links");
        const links = document.querySelectorAll(".nav-links li");

        hamburger.addEventListener('click', ()=>{
            //Animate Links
            navLinks.classList.toggle("open");
            links.forEach(link => {
                link.classList.toggle("fade");
            });

            //Hamburger Animation
            hamburger.classList.toggle("toggle");
        });
    </script>
</body>
             
</html>