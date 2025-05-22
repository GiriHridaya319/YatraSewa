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
import model.UserModel;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER_USER})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        this.dbController =  new DbController();
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userID = request.getParameter(StringUtils.USER_ID);
		String userFirstName = request.getParameter(StringUtils.USER_FIRST_NAME);
		String userLastName = request.getParameter(StringUtils.USER_LAST_NAME);
		String userEmail = request.getParameter(StringUtils.USER_EMAIL);
		String userPassword = request.getParameter(StringUtils.USER_PASSWORD);
		//String userRetypePassword = request.getParameter(StringUtils.USER_RETYPE_PASSWORD);
		String userPhoneNumber = request.getParameter(StringUtils.USER_PHONE_NUM);

		
		UserModel user = new UserModel( userID,  userFirstName,  userLastName,  userPassword,
				userEmail,  userPhoneNumber);
   
   int register_user = dbController.register_user(user);

   
   if(register_user == 1) {
	    
		request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
		request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
		
	}
	else if(register_user==0) {
		request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
		request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	}
	
	else {
		request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
		request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	}
   
		
	}
	
	

}
