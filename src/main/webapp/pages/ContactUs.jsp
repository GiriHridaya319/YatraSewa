<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/stylesheets/ContactUs.css" />
<style>
  body {
    background-image: url('${pageContext.request.contextPath}/resources/other/background.jpeg');
    background-size: cover;
    background-repeat: no-repeat;
    background-attachment: fixed;
    color: #ffffff; /* Optional: makes the text stand out on the background */
  }
  
</style>
</head>
<body>
  <div class="container">
    <div class="logo">
      <h1>Yatra Sewa</h1>
      <p>Your Trusted Travel Partner</p>
    </div>
    <div class="form-title">Get in Touch</div>
    <p class="subtitle">We would love to hear from you! Please fill out the form below.</p>
    <form class="contact-form">
      <div class="row">
        <input type="text" placeholder="First Name" required>
        <input type="text" placeholder="Last Name" required>
      </div>
      <div class="row">
        <input type="email" placeholder="Email" required>
        <input type="tel" placeholder="Mobile" required>
      </div>
      <input type="text" placeholder="Subject" required>
      <textarea placeholder="Message" rows="4" required></textarea>
      <button type="submit">Send Message</button>
    </form>
  </div>
</body>
</html>
