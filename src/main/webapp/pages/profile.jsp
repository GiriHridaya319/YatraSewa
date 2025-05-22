<%@page import="Utils.StringUtils"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/profile.css">
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <button class="sidebar-btn active">Profile</button>
             <form id="deleteForm-${updateProfile.userID}" method="post" action="<%=contextPath + StringUtils.SERVLET_UPDATE_PROFILE %>">
            <input type="hidden" name="<%="delete_user_id" %>" value="${updateProfile.userID}" />
            <button class="sidebar-btn active" type = "button" onclick="confirmDelete('${updateProfile.userID}')">
                <i class="fas fa-trash-alt"></i> DELETE ACCOUNT
            </button>
        </form>
                     
        </div>
        <c:if test="${isUser}">
        <div class="profile-form">
            <h2>Profile</h2>
            <form action="${pageContext.request.contextPath}/ModifyProfile" method="post" class="login-form">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" placeholder="First Name" name  = "user_FirstName" value="${updateProfile.userFirstName}" required>
                
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" placeholder="Last Name" name  = "user_LastName" value="${updateProfile.userLastName}" required>
                
                <label for="email">Email</label>
                <input type="email" id="email" placeholder="Email" name  = "user_Email" value="${updateProfile.userEmail}" required>
                
                <label for="phoneNumber">Phone Number</label>
                <input type="tel" id="phoneNumber" placeholder="Phone Number" name  = "user_PhoneNum" value="${updateProfile.userPhoneNumber}" required>
                
                <input type="hidden" name="update_user_id" value="${updateProfile.userID}" required>
                
                <button type="submit" class="submit-btn">Save Changes</button>
            </form>
        </div>
        </c:if>
        
         <c:if test="${isAgent}">
        <div class="profile-form">
            <h2>Profile</h2>
            <form action="${pageContext.request.contextPath}/ModifyProfile" method="post" class="login-form">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" placeholder="First Name" name  = "agent_FirstName" value="${updateProfile.agentFirstName}" required>
                
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" placeholder="Last Name" name  = "agent_LastName" value="${updateProfile.agentLastName}" required>
                
                <label for="email">Email</label>
                <input type="email" id="email" placeholder="Email" name  = "agent_Email" value="${updateProfile.agentEmail}" required>
                
                <label for="phoneNumber">Phone Number</label>
                <input type="tel" id="phoneNumber" placeholder="Phone Number" name  = "agent_PhoneNumber" value="${updateProfile.agentPhoneNumber}" required>
                
                <input type="hidden" name="update_user_id" value="${updateProfile.agentID}" required>
                
                <button type="submit" class="submit-btn" name  = "update_user_id">Save Changes</button>
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
</body>
<script>
	function confirmDelete(userName) {
		if (confirm("Are you sure you want to delete your account: " + userName
				+ "?")) {
			document.getElementById("deleteForm-" + userName).submit();
		}
	}
</script>
</html>