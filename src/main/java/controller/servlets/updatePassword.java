package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import Utils.StringUtils;
import controller.database.DbController;

/**
 * Servlet implementation class updatePassword
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_UPDATE_PASSWORD })
public class updatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DbController dbController = new DbController();
	
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
		String oldPassword = request.getParameter("old_password");
		
		
		String correctPassword;
		if (currentUserType.equals("user")) { // FOR MENTOR
			correctPassword = dbController.getUserInfo(currentUser).getUserPassword();
		}
		else { 
			correctPassword = dbController.getAgentInfo(currentUser).getAgentPassword();
        }
		if (oldPassword.equals(correctPassword)) {
			doPut(request, response);
		}
		else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_OLD_PASSWORD);
			request.setAttribute("isUpdate", 1);
			request.getRequestDispatcher(StringUtils.SERVLET_URL_INDEX).include(request, response);
		}
	}
	
	
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting attributes from current session
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
if (currentUserType.equals("user")) {
			
			// Update the mentor in database
			int result = dbController.updateUserPassword(currentUser, request.getParameter("new_password"));
			
			//checking for exception when updating
			if (result > 0) {
				request.setAttribute("isUpdate", 1);
				request.getRequestDispatcher(StringUtils.SERVLET_URL_INDEX).include(request, response);
			} else {
				// Internal server error
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			}
			
		}
else {
	
	// Update the seeker in database
	int result = dbController.updateAgentPassword(currentUser, request.getParameter("new_password"));
	
	//checking for exception when updating
	if (result > 0) {
		request.setAttribute("isUpdate", 1);
		request.getRequestDispatcher(StringUtils.SERVLET_URL_INDEX).include(request, response);
	} else {
		// Internal server error
		request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	}
}
}

}
