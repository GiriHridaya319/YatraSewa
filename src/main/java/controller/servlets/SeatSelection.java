package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import Utils.StringUtils;
import controller.database.DbController;
import model.BusModel;
import model.SeatModel;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_SEAT_SELECTION })
public class SeatSelection extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DbController controller = new DbController();

    // Handles GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busId = request.getParameter("busId");

        if (busId != null && !busId.isEmpty()) {
            try {
                // Fetch the available seats for the given busId
                List<SeatModel> seats = controller.getSeatsByBusId(busId);
                // Set the seats as a request attribute to be displayed on the JSP
                request.setAttribute("seats", seats);
                // Forward the request to the seat selection page
                request.getRequestDispatcher(StringUtils.PAGE_URL_SEAT_SELECTION).forward(request, response);
            } catch (SQLException e) {
                // Handle any SQL exceptions that might occur during seat retrieval
                throw new ServletException("Cannot retrieve seats", e);
            }
        } else {
            // If busId is null or empty, redirect to a different page or show an error message
            response.sendRedirect("errorPage.jsp");  // Placeholder error page
        }
    }

    // Handles POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busId = request.getParameter("busId");
        String[] selectedSeats = request.getParameterValues("selectedSeats");

        if (selectedSeats == null || selectedSeats.length == 0) {
        	
            // If no seats are selected, show the available seats again
            try {
                List<SeatModel> seats = controller.getSeatsByBusId(busId);
                request.setAttribute("seats", seats);
                
				ArrayList<BusModel> buses;
				buses = controller.getBus(busId);
				request.setAttribute(StringUtils.BUS_LISTS, buses);
                
                request.getRequestDispatcher(StringUtils.PAGE_URL_SEAT_SELECTION).forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Cannot retrieve seats", e);
            }
        } else if (selectedSeats != null && busId == null) {
            // If seats are selected, update their availability to false (booked)
            try {
                for (String seatID : selectedSeats) {
                    // Mark each selected seat as unavailable (booked)
                    controller.updateSeatAvailability(seatID, false);
                }
                // Redirect to the confirmation page after booking seats
                response.sendRedirect("pages/confirm.jsp"); // Placeholder confirmation page
            } catch (SQLException e) {
                throw new ServletException("Error updating seat availability", e);
            }
        } else {
            // If no valid parameters are provided, redirect back to the seat selection page
            response.sendRedirect(StringUtils.SERVLET_SEAT_SELECTION);
        }
    }

}
