package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import Utils.StringUtils;
import controller.database.DbController;
import model.AgentModel;

/**
 * Servlet implementation class RegisterAgentServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_REGISTER_AGENT })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegisterAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAgentServlet() {
    	this.dbController =  new DbController();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String agentID = request.getParameter(StringUtils.AGENT_ID);
		String agentFirstName = request.getParameter(StringUtils.AGENT_FIRST_NAME);
		String agentLastName = request.getParameter(StringUtils.AGENT_LAST_NAME);		
		String agentEmail = request.getParameter(StringUtils.AGENT_EMAIL);
		String agentPhoneNumber = request.getParameter(StringUtils.AGENT_PHONE_NUM);
		String agentPassword = request.getParameter(StringUtils.AGENT_PASSWORD);
		//String coach_password_retype = request.getParameter(StringUtils.AGENT_RETYPE_PASSWORD);
		
		
			
			AgentModel agent = new AgentModel( agentID,  agentFirstName,  agentLastName,  agentPassword,
					 agentEmail,  agentPhoneNumber);
	    
	    int register_agent = dbController.register_agent(agent);
	
	    
	    if(register_agent == 1) {
	    	
		    
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN ).forward(request, response);
			
		}
		else if(register_agent==0) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
		
		else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
	    
	    
	}

}
