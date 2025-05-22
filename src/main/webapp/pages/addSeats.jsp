<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Seats</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/addSeat.css">
</head>
<body>
    <h1>Add Seats</h1>

    <!-- Form to submit number of seats first -->
    <form method="post">
        <label for="numSeats">Enter the number of seats:</label>
        <input type="number" id="numSeats" name="numSeats" required>
        <button type="submit">Next</button>
    </form>

    <% 
        String numSeatsParam = request.getParameter("numSeats");
        if (numSeatsParam != null) {
            int numSeats = Integer.parseInt(numSeatsParam);
    %>
        <!-- Form to submit seat details -->
        <form action="${pageContext.request.contextPath}/addSeats" method="post">
            <!-- Hidden input to pass the number of seats -->
            <input type="hidden" name="numSeats" value="<%= numSeats %>">
            
            <table>
                <tr>
                    <th>Seat ID</th>
                    <th>Seat Name</th>
                    <th>Bus ID</th>
                    <th>Availability</th>
                </tr>
                <% for (int i = 1; i <= numSeats; i++) { %>
                    <tr>
                        <td><input type="text" name="seatId<%= i %>" required></td>
                        <td><input type="text" name="seatName<%= i %>" required></td>
                        <td><input type="text" name="busId<%= i %>" required></td>
                        <td>
                            <select name="availability<%= i %>" required>
                                <option value="Available">Available</option>
                                <option value="Not Available">Not Available</option>
                            </select>
                        </td>
                    </tr>
                <% } %>
            </table>

            <button type="submit">Submit Seats</button>
        </form>
    <% } %>

</body>
</html>
