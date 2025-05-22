package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Utils.StringUtils;
import controller.database.DbController;
import model.AdminModel;
import model.AgentModel;
import model.BusModel;
import model.UserModel;

/**
 * Servlet implementation class adminApproveBus
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminApproveBus" })
public class adminApproveBus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbController controller = new DbController();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busId = request.getParameter("busId");
        String action = request.getParameter("action");

        String status = action.equals("approve") ? "APPROVED" : "REJECTED";

        
        boolean updateSuccess = controller.updateBusStatus(busId, status);
        
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Step 1: Get the list of pending buses from the database
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
		 if(currentUserType.equals("admin"))  {
 			AdminModel admin  =  controller.getAdminInfoFromId(currentUser);
 			request.setAttribute("updateProfile", admin);
 			request.setAttribute("isAdmin", true);
 			
 		}
		ArrayList<AgentModel> agent = controller.getAllAgentInfo();
		ArrayList<UserModel> user = controller.getAllUserInfo();
		ArrayList<BusModel> bus = controller.getAllBusInfo();
		request.setAttribute(StringUtils.AGENT_LISTS, agent);
		request.setAttribute(StringUtils.USER_LISTS, user);
		request.setAttribute(StringUtils.BUS_LISTS, bus);
            ArrayList<BusModel> pendingBuses = controller.getPendingBuses();
            
            // Step 2: Set the list as a request attribute to be accessed in the JSP
            request.setAttribute("pendingBusLists", pendingBuses);
           
            
            // Step 3: Forward the request to the JSP for rendering
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_PANEL).forward(request, response);
            
        
    }
}
