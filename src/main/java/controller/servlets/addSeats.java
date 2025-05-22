package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Utils.StringUtils;
import controller.database.DbController;
import model.SeatModel;

/**
 * Servlet implementation class AddSeats
 */
@WebServlet(StringUtils.SERVLET_ADD_SEATS)
public class addSeats extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the number of seats from the form
        int numberOfSeats = Integer.parseInt(request.getParameter("numSeats"));
        
        // Loop through each seat and get the values
        for (int i = 1; i <= numberOfSeats; i++) {
            // Get Seat ID, Seat Name, Bus ID, and Availability from form
            String seatId = request.getParameter("seatId" + i);  // seatId1, seatId2, seatId3...
            String seatName = request.getParameter("seatName" + i);  // seatName1, seatName2, seatName3...
            String busId = request.getParameter("busId" + i);  // assuming busId is provided for each seat (you might pass this from the JSP)
            String availabilityStr = request.getParameter("availability" + i);  // availability1, availability2, availability3...

            // Convert Availability to a boolean
            boolean availability = "Available".equalsIgnoreCase(availabilityStr);

            // Create SeatModel object with the data
            SeatModel seat = new SeatModel(seatId, seatName, availability, busId);

            // Call the method to save seat to the database
            DbController dbController = new DbController();
            int result = dbController.addSeat(seat);

            // Log the result (or you can redirect to a different page)
            if (result == 1) {
                System.out.println("Seat " + seatId + " added successfully");
            } else {
                System.out.println("Failed to add seat " + seatId);
            }
        }

        // After processing all seats, redirect to a confirmation page or back to add seats page
        response.sendRedirect(request.getContextPath() + "/pages/addSeats.jsp");
    }
}
