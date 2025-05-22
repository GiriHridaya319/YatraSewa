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
import model.AdminModel;
import model.AgentModel;
import model.UserModel;

/**
 * Servlet implementation class ModifyProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_UPDATE_PROFILE })
public class ModifyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbController dbController = new DbController();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		if ( currentUserType.equals("user")) {
		UserModel user = dbController.getUserInfoFromId(request.getParameter("update_user_id"));
		
		request.setAttribute("updateProfile", user);
		request.setAttribute("isUser", true);
		request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);	}
		else if ( currentUserType.equals("agent"))  {
			AgentModel agent  =  dbController.getAgentInfoFromId(request.getParameter("update_user_id"));
			request.setAttribute("updateProfile", agent);
			request.setAttribute("isAgent", true);
			request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
		}
		else  {
			AdminModel admin  =  dbController.getAdminInfoFromId(request.getParameter("update_user_id"));
			request.setAttribute("updateProfile", admin);
			request.setAttribute("isAdmin", true);
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_PANEL).forward(request, response);
		}
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter("update_user_id");
		String deleteId = request.getParameter("delete_user_id");
		
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
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
		if ( currentUserType.equals("user")) {
		String firstname = request.getParameter(StringUtils.USER_FIRST_NAME);
		String lastname = request.getParameter(StringUtils.USER_LAST_NAME);
		String email = request.getParameter(StringUtils.USER_EMAIL);
		String phonenumber = request.getParameter(StringUtils.USER_PHONE_NUM);
		
		
		
		UserModel user = new UserModel();
		user.setUserFirstName(firstname);
		user.setUserLastName(lastname);
		user.setUserEmail(email);
		user.setUserPhoneNumber(phonenumber);
		
		
		int result = dbController.updateUser(user, request.getParameter("update_user_id"));
	        
	        if (result > 0) {
				resp.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_INDEX);

			} else {
				// Internal server error
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			}
		}
		else if ( currentUserType.equals("agent")) {
			String firstname = request.getParameter(StringUtils.AGENT_FIRST_NAME);
			String lastname = request.getParameter(StringUtils.AGENT_LAST_NAME);
			String email = request.getParameter(StringUtils.AGENT_EMAIL);
			String phonenumber = request.getParameter(StringUtils.AGENT_PHONE_NUM);
			
		AgentModel agent  = new AgentModel();
		agent.setAgentFirstName(firstname);
		agent.setAgentLastName(lastname);
		agent.setAgentEmail(email);
		agent.setAgentPhoneNumber(phonenumber);
		
		int result = dbController.updateAgent(agent, request.getParameter("update_user_id"));
		 if (result > 0) {
				resp.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_INDEX);

			} else {
				// Internal server error
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			}
		}
		else {
			String firstname = request.getParameter(StringUtils.ADMIN_FIRST_NAME);
			String lastname = request.getParameter(StringUtils.ADMIN_LAST_NAME);
			String role = request.getParameter(StringUtils.ADMIN_ROLE);
			String phonenumber = request.getParameter(StringUtils.ADMIN_PHONE_NUMBER);
			
		AdminModel admin  = new AdminModel();
		admin.setAdminFirstName(firstname);
		admin.setAdminLstName(lastname);
		admin.setAdminRole(role);
		admin.setAdminPhoneNumber(phonenumber);
		
		int result = dbController.updateAdmin(admin, request.getParameter("update_user_id"));
		 if (result > 0) {
				resp.sendRedirect(request.getContextPath() + StringUtils.SERVLET_ADMIN_PANEL);

			} else {
				// Internal server error
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			}
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession userSession = req.getSession();
		String currentUser = (String) userSession.getAttribute("username");
		String currentUserType = (String) userSession.getAttribute("type");
		
		if ( currentUserType.equals("user")) {
		int result = dbController.deleteUserInfo(req.getParameter("delete_user_id"));
		if ( result== 1) {
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			req.getRequestDispatcher(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
		} else {
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			req.getRequestDispatcher(req.getContextPath() + StringUtils.SERVLET_UPDATE_PROFILE);
		}
		}
		else if(currentUserType.equals("agent")) {
			int result = dbController.deleteAgentInfo(req.getParameter("delete_user_id"));
			if ( result== 1) {
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
				req.getRequestDispatcher(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
			} else {
				req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
				req.getRequestDispatcher(req.getContextPath() + StringUtils.SERVLET_UPDATE_PROFILE);
			}
		}
		
	}

}
