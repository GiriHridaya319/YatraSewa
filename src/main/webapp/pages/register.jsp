
<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    
<%
String contextPath = request.getContextPath();
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/Register.css" />



<title>Register Page</title>
</head>

<body>
	<div class="container" id="container">

		<div class="form-container sign-up-container">
			<form action="<%=contextPath%>/RegistrationServlet" method="post"
				enctype="multipart/form-data">
				<h1 id="title">Create Account for a user</h1>

				<label for="name" id="name-label">Username <input
					type="text" id="name" name="userID" placeholder="Ram@11" required>
				</label> <label for="password" id="name-label">Password <input
					type="password" id="password" name="userPassword" required>
				</label> <label for="password" id="name-label">Retype Password <input
					type="password" id="password" name="RetypeUser_Password" required>
				</label> <label for="name" id="name-label">First Name <input
					type="text" id="name" name="user_FirstName" placeholder="Ram"
					required>
				</label> <label for="name" id="name-label">Last Name <input
					type="text" id="name" name="user_LastName" placeholder="Bahadur"
					required>
				</label>  
				<label for="PhoneNumber" id="number-label">Phone Number <input
					type="text" id="PhoneNumber" name="user_PhoneNum"
					placeholder="9877211233" required></label>
				 <label for="email" id="email-label">Email <input
					type="text" id="PhoneNumber" name="user_Email"
					placeholder="iliketurtles@turtleclub.com" required>
				</label>

				<button type="submit">Submit</button>
			</form>

			<!-- messages -->

			<%
		String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
		String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

		if (errMsg != null) {
			// print
		%>
			<h4 class="error-msg">
				<%
			out.println(errMsg);
			%>
			</h4>
			<%
		}

		if (successMsg != null) {
		// print
		%>
			<h4 class="success-msg">
				<%
			out.println(successMsg);
			%>
			</h4>
			<%
		}
		%>

		</div>

		<div class="form-container sign-in-container" >
		


			<form action="<%=contextPath%>/RegisterAgentServlet" method="post"
				enctype="multipart/form-data">
				<h1 id="title">Create Account for a Agent</h1>
				<label for="username" id="name-label">Username <input
					type="text" id="name" name="agentID" placeholder="Ram@11"
					required>
				</label> <label for="password" id="password-label">Password <input
					type="password" id="password" name="agentPassword" required>
				</label> <label for="password" id="password-label">Retype Password <input
					type="password" id="password" name="agent_password_retype" required>
				</label> <label for="name" id="firstname-label">First Name <input
					type="text" id="name" name="agent_FirstName" placeholder="Ram"
					required>
				</label> <label for="lastname" id="lastname-label">Last Name <input
					type="text" id="name" name="agent_LastName" placeholder="Bahadur"
					required>
				</label> <label for="email" id="email-label">Email Address <input
					type="email" id="email" name="agent_Email"
					placeholder="iliketurtles@turtleclub.com" required>
				</label> <label for="PhoneNumber" id="number-label">Phone Number <input
					type="text" id="name" name="agent_PhoneNumber"
					placeholder="9866072017" required>
				</label>  


				<button type="submit">Submit</button>
			</form>


			<%
		String errMsgAgent = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
		String successMsgAgent = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

		if (errMsgAgent != null) {
			// print
		%>
			<h4 class="error-msg">
				<%
			out.println(errMsgAgent);
			%>
			</h4>
			<%
		}

		if (successMsgAgent != null) {
		// print
		%>
			<h4 class="success-msg">
				<%
			out.println(successMsgAgent);
			%>
			</h4>
			<%
		}
		%>



		</div>

		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left" style="background-image: url('${pageContext.request.contextPath}/resources/other/mainregister.jpg');">>
					<h1 id="title">Hello! If you are registering for Agent</h1>
					<p>Press the button bellow</p>

					<button class="ghost" id="signIn">Sign Up</button>
				</div>


				<div class="overlay-panel overlay-right" style="background-image: url('${pageContext.request.contextPath}/resources/other/mainregister.jpg');">>
					<h1 id="title">Hello! If you are registering for User</h1>
					<p>Press the button bellow</p>

					<button class="ghost" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>

<script>

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});
</script>
</body>


</html>