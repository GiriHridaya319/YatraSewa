<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/HomePage.css">
</head>
<body>
<jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
<div class="hero" style="background-image: url('${pageContext.request.contextPath}/resources/other/Homee.png');">
    <div class="search-bar-container">
        <div class="search-bar">
            <form>
                <select name="starting_location">
                    <option value="">Start your adventure at?</option>
                    <option value="pokhara">Pokhara</option>
                    <option value="kathmandu">Kathmandu</option>
                    <option value="chitwan">Chitwan</option>
                    <option value="bhairawa">Bhairawa</option>
                    <option value="dang">Dang</option>
                    <!-- Add more locations -->
                </select>
                <span class="icon"></span>
                <select name="destination">
                    <option value="">Your destination awaits at?</option>
                    <option value="pokhara">Pokhara</option>
                    <option value="kathmandu">Kathmandu</option>
                    <option value="chitwan">Chitwan</option>
                    <option value="bhairawa">Bhairawa</option>
                    <option value="dang">Dang</option>
                    <!-- Add more destinations -->
                </select>
                <input type="date" name="travel_date">
                <button class="search-button" type="submit">Search</button>
            </form>
        </div>
    </div>
</div>


    <section class="offers-section">
        <div class="section-header">
            <h2>Offers</h2>
            <a href="#" class="view-all">View All</a>
        </div>
        <div class="offers-container">
            <div class="offer-card">
                <div class="offer-title">BHAITIKA25</div>
                <p>Valid Till Nov 03</p>
                <button>Apply Code</button>
            </div>
            <div class="offer-card">
                <div class="offer-title">Tihar Discount</div>
                <p>Valid Till Nov 03</p>
                <button>Apply Code</button>
            </div>
            <div class="offer-card">
                <div class="offer-title">Chhath81</div>
                <p>Valid Till Nov 07</p>
                <button>Apply Code</button>
            </div>
        </div>
    </section>


    <section class="tour-package-section">
        <div class="tour-package-card">
            <div class="tour-package-header">
                <span>Tour Package</span>
                <span>Reservation</span>
            </div>
            <p>Book bus tickets on Yatra-Sewa with fast, hassle-free booking and 
                <br> no service charges.</p>
            <ul>
                <li><img src="busicon.jpeg" alt="Bus Icon"> Unique Packages</li>
                <li><img src="jeep.png" alt="Transport Icon"> Comfortable Transport</li>
                <li><img src="destination.png" alt="Destination Icon"> Popular Destinations</li>
            </ul>
            <button class="tour-button">Book Tour Package</button>
            <div class="image-overlay">
                <img src="download.jpeg" alt="Temple Image">
            </div>
        </div>
    </section>

    <section class="milestones-section">
        <div class="container">
            <h2 class="section-title">Our Milestones</h2>
            <p class="section-description">
                GoBus is committed to providing a seamless and efficient travel experience across Nepal, enabling fast, convenient booking and travel services.
            </p>
            <div class="milestone-cards">
                <div class="milestone-card">
                    <img src="routepng.png" alt="Bus Routes">
                    <h3>400+ Bus Routes</h3>
                    <p>Covering all major districts and towns, ensuring easy connectivity and convenient travel options.</p>
                </div>
                <div class="milestone-card">
                    <img src="inventory-icon.png" alt="Daily Inventory">
                    <h3>60k+ Daily Inventory</h3>
                    <p>Multiple seat options across a range of buses to meet various travel preferences.</p>
                </div>
                <div class="milestone-card">
                    <img src="partners-icon.png" alt="Bus Partners">
                    <h3>500+ Bus Partners</h3>
                    <p>Trusted partners to ensure timely and reliable bus services for passengers.</p>
                </div>
                <div class="milestone-card">
                    <img src="buses-icon.png" alt="Daily Buses">
                    <h3>2k+ Daily Buses</h3>
                    <p>A large fleet of comfortable and well-maintained buses operating across Nepal.</p>
                </div>
            </div>
        </div>
    </section>

    <section class="testimonials-section">
        <div class="container">
            <h2 class="section-title">What Our Clients Say</h2>
            <div class="testimonials">
                <div class="testimonial-card">
                    <div class="testimonial-header">
                        <img src="company1-icon.png" alt="Company Logo" class="company-logo">
                        <div>
                            <h3>Global Travels</h3>
                           
                        </div>
                    </div>
                    <blockquote>
                        "I have been a regular traveler with Global Travels and I must say their services have been exceptional!"
                    </blockquote>
                    <div class="client-info">
                        <img src="client1.jpg" alt="Client Photo" class="client-photo">
                        <p class="client-name">John Doe</p>
                    </div>
                </div>
                <div class="testimonial-card">
                    <div class="testimonial-header">
                        <img src="company2-icon.png" alt="Company Logo" class="company-logo">
                        <div>
                            <h3>Skyline Express</h3>
                            
                        </div>
                    </div>
                    <blockquote>
                        "Skyline Express has the best bus routes across the country, and I have  always enjoyed traveling with them."
                    </blockquote>
                    <div class="client-info">
                        <img src="client2.jpg" alt="Client Photo" class="client-photo">
                        <p class="client-name">Jane Smith</p>
                    </div>
                </div>
                <div class="testimonial-card">
                    <div class="testimonial-header">
                        <img src="company3-icon.png" alt="Company Logo" class="company-logo">
                        <div>
                            <h3>Mountain View Travels</h3>
                           
                        </div>
                    </div>
                    <blockquote>
                        "Great service, friendly staff, and reliable transportation. Mountain View Travels is my go-to choice."
                    </blockquote>
                    <div class="client-info">
                        <img src="client3.jpg" alt="Client Photo" class="client-photo">
                        <p class="client-name">Emily Williams</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
<jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
</body>
</html>