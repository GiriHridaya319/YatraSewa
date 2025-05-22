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
 * Servlet implementation class ModifyBusDetailsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY })
public class ModifyBusDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbController dbController = new DbController();
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusModel bus = dbController.getBusInfoFromId(request.getParameter("update_bus_id"));
		request.setAttribute("updateBus", bus);
		request.getRequestDispatcher(StringUtils.PAGE_URL_UPDATE).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter("update_bus_id");
		String deleteId = request.getParameter("delete_bus_id");

		// if update button clicked
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		
		// if delete button clicked
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse resp)
	        throws ServletException, IOException {
		String name = request.getParameter(StringUtils.BUS_NAME);
		String destination = request.getParameter(StringUtils.BUS_DESTINATION);
		String departureTime = request.getParameter(StringUtils.BUS_DEPARTURE_TIME);
		String reachingTime = request.getParameter(StringUtils.BUS_REACHING_TIME);
		String price = request.getParameter(StringUtils.BUS_PRICE);
		String description = request.getParameter(StringUtils.BUS_DESCRIPTION);
		String pickup = request.getParameter(StringUtils.BUS_PICKUP);
		LocalDate date = LocalDate.parse(request.getParameter(StringUtils.BUS_DATE));
		
		
		BusModel bus = new BusModel();
		bus.setBusName(name);
		bus.setDestination(destination);
		bus.setDepartureTime(departureTime);
		bus.setReachingTime(reachingTime);
		bus.setPrice(price);
		bus.setDescription(description);
		bus.setPrice(price);
		bus.setPickup(pickup);
		bus.setDate(date);
		
		int result = dbController.updateBus(bus, request.getParameter("update_bus_id"));
	        
	        if (result > 0) {
				resp.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_BUSES);

			} else {
				// Internal server error
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			}
		}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Deleting the selected service from database
		if (dbController.deleteBus(request.getParameter("delete_bus_id")) == 1) {
			
			// send success message through request and redirect to services servlet
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_BUSES);
		
		// If deletion failed
		} else {
			// Internal server error
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
		}
	}
	    
   

}
