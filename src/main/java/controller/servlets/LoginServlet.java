package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import controller.database.DbController;
import model.UserLoginModel;
import Utils.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_LOGIN })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbController dbController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        this.dbController = new DbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userID = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);
        
        UserLoginModel loginModelUser = new UserLoginModel(userID,password);
        
        int loginResultUser = dbController.getUserLoginInfo(loginModelUser);
        
        
        if(loginResultUser == 1) { // for user
        	
        	HttpSession userSession = request.getSession();
        	userSession.setAttribute("type", "user");
        	userSession.setAttribute("username", userID);
			
			userSession.setMaxInactiveInterval(30*30);
			
			Cookie userCookie= new Cookie("user", userID);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			

            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
            response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_INDEX);}
            
            else if (loginResultUser == 2) {	 // FOR AGENT (LOGIN SUCCESS)
    			
    			// getting image from database
    			
    					
    			// creating session and setting attributes
            	HttpSession userSession = request.getSession();
            	userSession.setAttribute("type", "agent");
    			userSession.setAttribute("username", userID);
    			userSession.setMaxInactiveInterval(30*30);
    			
    			// creating cookie
    			Cookie userCookie= new Cookie("agent", userID);
    			userCookie.setMaxAge(30*60);
    			response.addCookie(userCookie);
    			
    			// redirect to welcome page
    			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
    			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_INDEX);
    					
    		} 
            else if (loginResultUser == 3) {	 // FOR ADMIN (LOGIN SUCCESS)
  
    					
    			// creating session and setting attributes
            	HttpSession userSession = request.getSession();
            	userSession.setAttribute("type", "admin");
    			userSession.setAttribute("username", userID);
    			userSession.setMaxInactiveInterval(30*30);
    			
    			// creating cookie
    			Cookie userCookie= new Cookie("admin", userID);
    			userCookie.setMaxAge(30*60);
    			response.addCookie(userCookie);
    			
    			// redirect to welcome page
    			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
    			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_ADMIN_PANEL_PEN);
    					
    		} 
            

        else if(loginResultUser == 0) {
        	
        	 request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
        	 request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
             
        	
        }else if(loginResultUser ==-1) {
        	request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
        	request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
           
        }else {
        	request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
        	request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
            
        }
        
		
		
		
}
	}
