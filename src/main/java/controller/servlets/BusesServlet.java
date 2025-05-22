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

import Utils.StringUtils;
import controller.database.DbController;
import model.AgentModel;
import model.BusModel;

/**
 * Servlet implementation class BusesServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_BUSES })
public class BusesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DbController controller = new DbController();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting attributes from current session
				HttpSession userSession = request.getSession();
				String currentUser = (String) userSession.getAttribute("username");
				String currentUserType = (String) userSession.getAttribute("type");
				
				String searchKey = request.getParameter("search");
				String pickup = request.getParameter("pickup");
			    String destination = request.getParameter("destination");
			    String minPrice = request.getParameter("minPrice");
			    String maxPrice = request.getParameter("maxPrice");
				
				// FOR USER
				if (currentUserType == null || currentUserType.equals("user")) {
					
					// Declaring services arraylist to store all services
					ArrayList<BusModel> buses;
					
					if (pickup != null || destination != null || minPrice != null || maxPrice != null) {
			            buses = controller.getFilteredBuses(pickup, destination, minPrice, maxPrice);
			        }
					
					// Get searched services if search button clicked
					else if (searchKey == null) {
						buses = controller.getAllBusInfo();
					}
					
					// Get all services for default
					else {
						buses = controller.getSearchedBusInfo(searchKey);
					}
					
					// adding arraylist of services in request attribute to display in  services jsp
					request.setAttribute(StringUtils.BUS_LISTS, buses);
					request.setAttribute("isUser", true);
					
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
				}
				// FOR MENTOR
				else {
					
					// Declaring services arraylist to store mentor's all services
					ArrayList<BusModel> buses;
					
					// Get searched services if search button clicked
					if (searchKey == null) {
						buses = controller.getAllAgentBusInfo(currentUser);
					}
					
					// Get all mentor's services for default
					else {
						buses = controller.getSearchedBusInfoAgent(searchKey, currentUser);
					}

					// adding arraylist of mentor's services in request attribute to display in  services jsp
					request.setAttribute(StringUtils.AGENT_BUS_LISTS, buses);
					request.setAttribute("isAgent", true);
					
					// Initializing and declaring hashMap to connect mentor details with their service
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
				}
				
				// Redirect to services page with attributes in request
				request.getRequestDispatcher(StringUtils.PAGE_URL_BUSES).forward(request, response);
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
