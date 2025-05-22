<%@page import="Utils.StringUtils"%>
<%@page import="model.AgentModel"%>
<%@page import="model.UserModel"%>
<%@page import="model.BusModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%
	//Get the session and request objects
	HttpSession adminSession = request.getSession();
     String currentAdmin = (String) adminSession.getAttribute(StringUtils.ADMIN_ID);

    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/adminPanel.css">

<style>
    /* Add styles for content sections to show and hide */
    .content-section {
        display: none;
    }
    .active-section {
        display: block;
    }

    .approve-btn { background-color: green; color: white; padding: 5px; border: none; cursor: pointer; }
    .reject-btn { background-color: red; color: white; padding: 5px; border: none; cursor: pointer; }
    .delete-btn { background-color: gray; color: white; padding: 5px; border: none; cursor: pointer; }

</style>
</head>
<body>

<%
	String userSession = (String) session.getAttribute(StringUtils.ADMIN_ID);

	String cookieUsername = null;
	String cookieSessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(StringUtils.ADMIN))
		cookieUsername = cookie.getValue();
			if (cookie.getName().equals(StringUtils.JSESSIONID))
		cookieSessionID = cookie.getValue();
		}
	}
	%>



 <!-- for header part -->
    <header>

        <div class="logosec">
            
            <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210182541/Untitled-design-(30).png"
                class="icn menuicn" 
                id="menuicn" 
                alt="menu-icon">
        </div>
		<div class="logo">
				HELLO <span><%=cookieUsername%></span>, WELCOME!
			</div>
       

        

    </header>

    <div class="main-container">
        <div class="navcontainer">
            <nav class="nav">
                <div class="nav-upper-options">
                    <div id="dashboard" class="nav-option option1">
                        <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210182148/Untitled-design-(29).png"
                            class="nav-img" 
                            alt="dashboard">
                        <h3>Users</h3>
                    </div>

                    <div id="users" class="nav-option option1">
                        <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210183322/9.png"
                            class="nav-img" 
                            alt="users">
                        <h3> Agents</h3>
                    </div>

                    <div id="agents" class="nav-option option1">
                        <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210183320/5.png"
                            class="nav-img" 
                            alt="agents">
                        <h3> Buses </h3>
                    </div>

                    <div id="bus" class="nav-option option1">
                        <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210183321/6.png"
                            class="nav-img" 
                            alt="bus">
                        <h3> Profile</h3>
                    </div>

                    

                    <div id="logout" class="nav-option logout">
                        <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210183321/7.png"
                            class="nav-img" 
                            alt="logout">
                       <a href="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT%>">
	            	<h3> Logout </h3>
	            </a>
                    </div>

                </div>
            </nav>
        </div>
        
        <div class="main">
            <!-- Define content sections for each menu option -->
            <div id="dashboardContent" class="content-section active-section">
    <div class="main">
        
        <!-- Search bar similar to the HTML -->
        <div class="searchbar2">
            <input type="text" name="" id="" placeholder="Search">
            <div class="searchbtn">
                <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180758/Untitled-design-(28).png" 
                     class="icn srchicn" alt="search-button">
            </div>
        </div>
        
        
       
            
        <!-- Coach Listings Section -->
        <div class="report-container">
            <div class="report-header">
                <h1 class="recent-Articles">All Users</h1>
                
            </div>
            
            <div class="report-body">
                <div class="report-topic-heading">
                    <h3 class="t-op">User ID</h3>
                    <h3 class="t-op">User First Name</h3>
                    <h3 class="t-op">User Email</h3>
                    <h3 class="t-op">User Phone Number</h3>
                    <h3 class="t-op">Action</h3>
                </div>
                
                <c:if test="${empty userlists}">
                    <p>No Users Registered</p>
                </c:if>
                
                <c:if test="${not empty userlists}">
                    <div class="items">
                        <c:forEach var="user" items="${userlists}">
                            <div class="item1">
                                <h3 class="t-op-nextlvl">${user.userID}</h3>
                                <h3 class="t-op-nextlvl">${user.userFirstName}</h3>
                                <h3 class="t-op-nextlvl">${user.userEmail}</h3>
                                <h3 class="t-op-nextlvl">${user.userPhoneNumber}</h3>
                                <button class="t-op-nextlvl label-tag">Delete</button> 
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
        
        <!-- Additional dashboard content can go here -->
        
    </div>
</div>
            
            
            <!--  *************************** -->
            <div id="usersContent" class="content-section">
                <div id="dashboardContent" class="content-section active-section">
                 <div class="main">

            <div class="searchbar2">
                <input type="text" 
                       name="" 
                       id="" 
                       placeholder="Search">
                <div class="searchbtn">
                  <img src=
"https://media.geeksforgeeks.org/wp-content/uploads/20221210180758/Untitled-design-(28).png"
                        class="icn srchicn" 
                        alt="search-button">
                  </div>
            </div>

            

            <div class="report-container">
            <div class="report-header">
                <h1 class="recent-Articles">All Agents</h1>
                
            </div>
            
            <div class="report-body">
                <div class="report-topic-heading">
                    <h3 class="t-op">Agent ID</h3>
                    <h3 class="t-op">Agent First Name</h3>
                    <h3 class="t-op">Agent Email</h3>
                    <h3 class="t-op">Agent Phone Number</h3>
                    <h3 class="t-op">Action</h3>
                </div>
                
                <c:if test="${empty agentlists}">
                    <p>No Agents Registered</p>
                </c:if>
                
                <c:if test="${not empty agentlists}">
                    <div class="items">
                        <c:forEach var="agent" items="${agentlists}">
                            <div class="item1">
                                <h3 class="t-op-nextlvl">${agent.agentID}</h3>
                                <h3 class="t-op-nextlvl">${agent.agentFirstName}</h3>
                                <h3 class="t-op-nextlvl">${agent.agentEmail}</h3>
                                <h3 class="t-op-nextlvl">${agent.agentPhoneNumber}</h3>
                                <button class="t-op-nextlvl label-tag">Delete</button> 
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
        </div>
            </div>
            </div>
            
            <div id="agentsContent" class="content-section">
    <div id="dashboardContent" class="content-section active-section">
        <div class="main">

            <!-- Search Bar -->
            <div class="searchbar2">
                <input type="text" id="searchBus" placeholder="Search by Bus Name or ID">
                <div class="searchbtn">
                    <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180758/Untitled-design-(28).png"
                         class="icn srchicn" alt="search-button">
                </div>
            </div>

            <!-- Section 1: All Buses -->
           <div class="report-container">
    <div class="report-header">
        <h1 class="recent-Articles">All Buses</h1>
    </div>
    
    <div class="report-body">
        <div class="report-topic-heading">
            <h3 class="t-op">Bus ID</h3>
            <h3 class="t-op">Bus Name</h3>
            <h3 class="t-op">Price</h3>
            <h3 class="t-op">Destination</h3>
            <h3 class="t-op">Pickup</h3>
            <h3 class="t-op">Date</h3>
            <h3 class="t-op">Agent</h3>
            <h3 class="t-op">Status</h3>
            <h3 class="t-op">Actions</h3>
        </div>
        
        <c:if test="${empty busLists}">
            <p>No buses available.</p>
        </c:if>
        
        <c:if test="${not empty busLists}">
            <div class="items">
                <c:forEach var="bus" items="${busLists}">
                    <c:if test="${bus.status == 'APPROVED'}">
                        <div class="item1">
                            <h3 class="t-op-nextlvl">${bus.busID}</h3>
                            <h3 class="t-op-nextlvl">${bus.busName}</h3>
                            <h3 class="t-op-nextlvl">NPR ${bus.price}</h3>
                            <h3 class="t-op-nextlvl">${bus.destination}</h3>
                            <h3 class="t-op-nextlvl">${bus.pickup}</h3>
                            <h3 class="t-op-nextlvl">${bus.date}</h3>
                            <h3 class="t-op-nextlvl">${bus.agentUsername}</h3>
                            <h3 class="t-op-nextlvl">${bus.status}</h3>

                            <!-- Delete Action -->
                            <div class="action-buttons">
                                <form action="DeleteBusServlet" method="post" style="display:inline;">
                                    <input type="hidden" name="busId" value="${bus.busID}">
                                    <button type="submit" class="delete-btn label-tag">Delete</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
           

            <!-- Section 2: Pending Buses -->
            <div class="report-container">
                <div class="report-header">
                    <h1 class="recent-Articles">Pending Buses</h1>
                </div>
                
                <div class="report-body">
                    <div class="report-topic-heading">
                        <h3 class="t-op">Bus ID</h3>
                        <h3 class="t-op">Bus Name</h3>
                        <h3 class="t-op">Price</h3>
                        <h3 class="t-op">Destination</h3>
                        <h3 class="t-op">Pickup</h3>
                        <h3 class="t-op">Date</h3>
                        <h3 class="t-op">Agent</h3>
                        <h3 class="t-op">Actions</h3>
                    </div>
                    
                    <c:if test="${empty pendingBusLists}">
                        <p>No pending buses.</p>
                    </c:if>
                    
                    <c:if test="${not empty pendingBusLists}">
                        <div class="items">
                            <c:forEach var="bus" items="${pendingBusLists}">
                                <div class="item1">
                                    <h3 class="t-op-nextlvl">${bus.busID}</h3>
                                    <h3 class="t-op-nextlvl">${bus.busName}</h3>
                                    <h3 class="t-op-nextlvl">NPR ${bus.price}</h3>
                                    <h3 class="t-op-nextlvl">${bus.destination}</h3>
                                    <h3 class="t-op-nextlvl">${bus.pickup}</h3>
                                    <h3 class="t-op-nextlvl">${bus.date}</h3>
                                    <h3 class="t-op-nextlvl">${bus.agentUsername}</h3>
                                    
                                    <!-- Approve/Reject Buttons -->
                                    <div class="action-buttons">
                                        <form action="${pageContext.request.contextPath}/adminApproveBus" method="post" style="display:inline;">
                                            <input type="hidden" name="busId" value="${bus.busID}">
                                            <input type="hidden" name="action" value="approve">
                                            <button type="submit" class="approve-btn label-tag">Approve</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/adminApproveBus"method="post" style="display:inline;">
                                            <input type="hidden" name="busId" value="${bus.busID}">
                                            <input type="hidden" name="action" value="reject">
                                            <button type="submit" class="reject-btn label-tag">Reject</button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- CSS Styling -->


            <div id="busContent" class="content-section">
                
                 <div class="container">
        <div class="sidebar">
            <button class="sidebar-btn active">Profile</button>
             <a href="<%=contextPath + StringUtils.PAGE_URL_ADD_ADMIN%>">
	            	<button class="sidebar-btn active">Add Admin</button>
	            </a> 
	            <a href="<%=contextPath + StringUtils.PAGE_URL_ADD_ADMIN%>">
	            	<button class="sidebar-btn active">Delete Account</button>
	            </a> 
                     
        </div>
       
       <c:if test="${isAdmin}">
        <div class="profile-form">
            <h2>Profile</h2>
            <form action="${pageContext.request.contextPath}/ModifyProfile" method="post" class="login-form">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" placeholder="First Name" name  = "adminFirstName" value="${updateProfile.adminFirstName}" required>
                
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" placeholder="Last Name" name  = "adminLastName" value="${updateProfile.adminLstName}" required>
                
                <label for="role">Role</label>
                <input type="text" id="email" placeholder="Email" name  = "adminRole" value="${updateProfile.adminRole}" required>
                
                <label for="phoneNumber">Phone Number</label>
                <input type="tel" id="phoneNumber" placeholder="Phone Number" name  = "adminPhoneNumber" value="${updateProfile.adminPhoneNumber}" required>
                
                <input type="hidden" name="update_user_id" value="${updateProfile.adminID}" required>
                
                <button type="submit" class="submit-btn">Save Changes</button>
            </form>
        </div>
        </c:if>
        
         
        
         <div class="password-form">
            <h2>Change Password</h2>
            <form action = "${pageContext.request.contextPath}/updatePassword" method= "post">
                <label for="currentPassword">Current Password</label>
                <input type="password" id="currentPassword"  name = "old_password" placeholder="Enter current password">
                
                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword" name = "new_password" placeholder="Enter new password">
                
                
                
                <button type="submit" class="update-btn">Update Password</button>
            </form>
        </div>
    </div>
                
                
                
                
                
            </div>
            <div id="profileContent" class="content-section">
                <h2>Profile</h2>
                <p>This is the Profile content.</p>
            </div>
            <div id="logoutContent" class="content-section">
                <h2>Logout</h2>
                <p>You have logged out.</p>
            </div>
        </div>
    </div>

    <script>
    // Toggle sidebar navigation
    let menuicn = document.querySelector(".menuicn");
    let nav = document.querySelector(".navcontainer");

    menuicn.addEventListener("click", () => {
        nav.classList.toggle("navclose");
    });

    // Switch content based on menu selection
    const sections = {
        dashboard: document.getElementById('dashboardContent'),
        users: document.getElementById('usersContent'),
        agents: document.getElementById('agentsContent'),
        bus: document.getElementById('busContent'),
        profile: document.getElementById('profileContent'),
        logout: document.getElementById('logoutContent')
    };

    function showSection(sectionId) {
        for (let key in sections) {
            sections[key].classList.remove('active-section');
        }
        sections[sectionId].classList.add('active-section');
    }

    document.getElementById('dashboard').addEventListener('click', () => showSection('dashboard'));
    document.getElementById('users').addEventListener('click', () => showSection('users'));
    document.getElementById('agents').addEventListener('click', () => showSection('agents'));
    document.getElementById('bus').addEventListener('click', () => showSection('bus'));
    document.getElementById('profile').addEventListener('click', () => showSection('profile'));
    document.getElementById('logout').addEventListener('click', () => showSection('logout'));

    </script>
</body>
</html>