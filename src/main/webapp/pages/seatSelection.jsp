<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seat Selection</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/seatselection.css">
    <style>
       
    </style>
</head>
<body>
    <div class="container">
       
        <c:if test="${not empty busLists}">
                <c:forEach var="bus" items="${busLists}">
                    <div class="card">
                        <div class="bus-info">
                            <div class="bus-details">
                                <h4 class="job-title">${bus.busName}</h4>
                                <h4>Date: ${bus.date}</h4>
                            </div>
                            
                        </div>
                    </div>
                     <h2>Seat Selection</h2>
        
        <div class="info-panel">
            <h3>Boarding Point</h3>
            <select class="boarding-point">
                <option>Select Boarding Points</option>
                <option>Point 1</option>
                <option>Point 2</option>
            </select>
                    <!-- Fare is fetched from the selected bus -->
                    <p>Fare: Rs. <span id="fare">${bus.price}</span></p>
                    <p>Total Amount: Rs. <span id="total-amount">0</span></p>
                </c:forEach>
            </c:if>
             
        </div>
        <br>
        
        <!-- Seat selection form -->
        <form method="post" action="${pageContext.request.contextPath}/SeatSelection">
            <div class="seat-container">
                <!-- JSTL loop to display each seat -->
                <c:forEach var="seat" items="${seats}">
                    <button 
                        type="button" 
                        class="seat ${seat.isAvailable() ? 'available' : 'booked'}"
                        onclick="selectSeat('${seat.seatID}', this)"
                        ${seat.isAvailable() == false ? 'disabled' : ''}>
                        ${seat.seatNumber}
                    </button>
                </c:forEach>
            </div>
        
            <!-- Hidden field to store selected seats -->
            <input type="hidden" id="selectedSeats" name="selectedSeats">
            <button type="submit" class="submit-btn" onclick="return validateSeats()">Confirm Selection</button>
        </form>

        <!-- Seat Status Legend -->
        <div class="legend">
            <div class="legend-item">
                <div class="legend-box" style="background-color: lightgreen;"></div>
                <span>Available</span>
            </div>
            <div class="legend-item">
                <div class="legend-box" style="background-color: orange;"></div>
                <span>Selected</span>
            </div>
            <div class="legend-item">
                <div class="legend-box" style="background-color: lightcoral;"></div>
                <span>Booked</span>
            </div>
        </div>

        <!-- Additional info or action -->
       
    </div>

    <script>
    <c:forEach var="bus" items="${busLists}">
        let selectedSeats = [];
        let seatPrice = ${bus.price}; // Dynamic price based on selected bus

        // Function to handle seat selection
        function selectSeat(seatID, seatButton) {
            if (!seatButton.classList.contains('booked') && !seatButton.classList.contains('disabled')) {
                // Toggle seat selection: if it's available, mark as selected and turn orange
                if (selectedSeats.includes(seatID)) {
                    // Deselect seat and update total amount
                    selectedSeats = selectedSeats.filter(seat => seat !== seatID);
                    seatButton.classList.remove('selected');
                    updateTotalAmount(-seatPrice);  // Decrease total amount
                } else {
                    // Select seat and update total amount
                    selectedSeats.push(seatID);
                    seatButton.classList.add('selected');
                    updateTotalAmount(seatPrice);  // Increase total amount
                }
            }
        }

        // Function to update the total amount based on the number of selected seats
        function updateTotalAmount(amount) {
            const totalAmountElement = document.getElementById('total-amount');
            // Calculate the new total amount
            const newTotalAmount = selectedSeats.length * seatPrice;
            totalAmountElement.textContent = newTotalAmount;
        }

        // Function to validate if any seat is selected before submitting
        function validateSeats() {
            if (selectedSeats.length === 0) {
                alert("Please select at least one seat!");
                return false;
            }
            // Set the hidden field with the selected seats
            document.getElementById('selectedSeats').value = selectedSeats.join(',');
            return true;
        }

        // Once form is submitted, change the selected seats to booked (Red)
        function bookSelectedSeats() {
            selectedSeats.forEach(seatID => {
                const seatButton = document.querySelector(`button[onclick="selectSeat('${seatID}')"]`);
                if (seatButton) {
                    seatButton.classList.remove('selected');
                    seatButton.classList.add('booked');
                    seatButton.disabled = true; // Disable the button to prevent further selection
                }
            });
        }

        // On form submission, update seat statuses to booked for selected seats
        document.querySelector('form').onsubmit = function() {
            bookSelectedSeats();
        }

        // Set the seat price dynamically when the bus is selected
        document.querySelectorAll('.card').forEach(function(card) {
            card.addEventListener('click', function() {
                // Set the seat price based on the bus price
                seatPrice = parseInt(card.querySelector('.price h4').textContent.replace('NPR', '').trim());
                updateTotalAmount(0); // Update total amount with new price
            });
        });
        </c:forEach>
    </script>
</body>
</html>
