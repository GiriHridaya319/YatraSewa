package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Utils.StringUtils;
import controller.database.DbController;
import model.BusModel;

/**
 * Servlet implementation class AddBusServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD_BUSS })
public class AddBusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DbController dbController;
    
    public AddBusServlet() {
        this.dbController = new DbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Get parameters from request
            String id = request.getParameter(StringUtils.BUS_ID);
            String name = request.getParameter(StringUtils.BUS_NAME);
            String destination = request.getParameter(StringUtils.BUS_DESTINATION);
            String departureTime = request.getParameter(StringUtils.BUS_DEPARTURE_TIME);
            String reachingTime = request.getParameter(StringUtils.BUS_REACHING_TIME);
            String price = request.getParameter(StringUtils.BUS_PRICE);
            String description = request.getParameter(StringUtils.BUS_DESCRIPTION);
            String pickup = request.getParameter(StringUtils.BUS_PICKUP);
            LocalDate date = LocalDate.parse(request.getParameter(StringUtils.BUS_DATE));
            String agent_id = request.getParameter(StringUtils.BUS_AGENT);
            
            // Create bus model
            BusModel buses = new BusModel(id, name, destination,
                    departureTime, reachingTime, price, description, 
                    pickup, date, agent_id, "PENDING");
            
            // Add bus to database
            int add = dbController.addNewBus(buses);
            
            if(add == 1) {
                // Success - redirect to add seat page with success parameter
                response.sendRedirect(request.getContextPath() + 
                    StringUtils.PAGE_URL_ADD_SEAT + "?success=true&busId=" + id);
            } 
            else {
                // Failure - forward back to form with error message
                request.setAttribute(StringUtils.MESSAGE_ERROR, 
                    (add == 0) ? StringUtils.MESSAGE_ERROR_REGISTER : 
                                StringUtils.MESSAGE_ERROR_SERVER);
                request.getRequestDispatcher(StringUtils.PAGE_URL_ADD)
                      .forward(request, response);
            }
            
        } catch (Exception e) {
            // Handle any exceptions
            request.setAttribute(StringUtils.MESSAGE_ERROR, 
                "Error processing request: " + e.getMessage());
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD)
                  .forward(request, response);
        }
    }
}