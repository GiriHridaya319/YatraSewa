package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import controller.database.DbController;
import model.AgentModel;
import model.BusModel;
import Utils.StringUtils;

/**
 * Servlet implementation class indesServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_INDEX })
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DbController controller = new DbController();
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	HttpSession userSession = request.getSession();
	String currentUser = (String) userSession.getAttribute("username");
	
	String pickup = request.getParameter("starting_location");
	String destination = request.getParameter("destination");
	String date = request.getParameter("travel_date");
	
	ArrayList<BusModel> buses;
	
	// Get searched services if search button clicked
	if (pickup == null || destination == null || date ==null) {
		
		request.getRequestDispatcher(StringUtils.PAGE_URL_WELCOME).forward(request, response);
	}
	
	else {
		
		buses = controller.getSearchedBusInfoWithDestination(pickup,destination,date);
		request.setAttribute(StringUtils.BUS_LISTS, buses);
		
		HashMap<String, AgentModel> hashMap = new HashMap<>();
	    
	    // Loop on each items on arraylist
	    for (BusModel bus: buses) {	
	    	// Add mentor name as key and its model as value
	    	String currUsername = bus.getAgentUsername();
	    	AgentModel agent = controller.getAgentInfo(currUsername);
	        hashMap.put(bus.getAgentUsername(), agent);
	    }
	    
	    // adding the mentor hashmap in request attribute to display in services page
	    request.setAttribute("AgentMap", hashMap);	
	    request.getRequestDispatcher(StringUtils.PAGE_URL_SEARCH_DEST_BUS).forward(request, response);
	}
	

	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
}

}
