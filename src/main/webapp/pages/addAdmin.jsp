<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/addAdmin.css">
</head>
<body>
<div class="container">

        <main class="main-content">
            <div class="card">
            <button type="submit" class="save-button" onclick="location.href='${pageContext.request.contextPath}/adminApproveBus'">BACK</button>
                <br>
                <br>
                <h1>Account Settings</h1>
                
                <form class="settings-form" action="${pageContext.request.contextPath}/addAdmin" method="post" >
                    <label for="first-name">First Name</label>
                    <input type="text" id="first-name" name="adminFirstName" placeholder="Enter your first name">

                    <label for="last-name">Last Name</label>
                    <input type="text" id="last-name" name="adminLastName" placeholder="Enter your last name">

                    <label for="username">Username</label>
                    <input type="text" id="username" name=adminID placeholder="Enter your username">

					 <label for="username">Password</label>
                    <input type="password" id="username" name=adminPassword placeholder="Enter password">
                    
                     <label for="username">Retype Password</label>
                    <input type="password" id="username" name= admiRetypePassword placeholder="Retype Password">
                    
                    <label for="email">Role </label>
                    <input type="text" id="email" name="adminRole" placeholder="Enter your Role">

                    <label for="phone-number">Phone Number</label>
                    <input type="tel" id="phone-number" name="adminPhoneNumber" placeholder="Enter your phone number">

                    <button type="submit" class="save-button">ADD</button>
                </form>
            </div>
        </main>
    </div>
</body>
</html>