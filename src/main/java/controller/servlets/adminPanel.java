package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class adminPanel
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_ADMIN_PANEL })
public class adminPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DbController controller = new DbController();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
		ArrayList<AgentModel> agent = controller.getAllAgentInfo();
		ArrayList<UserModel> user = controller.getAllUserInfo();
		ArrayList<BusModel> bus = controller.getAllBusInfo();
		request.setAttribute(StringUtils.AGENT_LISTS, agent);
		request.setAttribute(StringUtils.USER_LISTS, user);
		request.setAttribute(StringUtils.BUS_LISTS, bus);
		
		if(currentUserType.equals("admin"))  {
			AdminModel admin  =  controller.getAdminInfoFromId(request.getParameter(currentUser));
			request.setAttribute("updateProfile", admin);
			request.setAttribute("isAdmin", true);
			
		}
		request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_PANEL).forward(request, response);
	}

	

}
