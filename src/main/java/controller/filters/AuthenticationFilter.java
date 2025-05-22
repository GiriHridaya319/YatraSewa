package controller.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Utils.StringUtils;

/**
 * Servlet implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {

			chain.doFilter(request, response);
			return;
		}

		if ((uri.endsWith(StringUtils.PAGE_URL_HOMEPAGE) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_MAIN_PAGE).forward(request, response);
			
			return;
		}

		else if ((uri.endsWith(StringUtils.PAGE_URL_HOME_PLAYER) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_HOME_COACH).forward(request, response);
			
			return;
		} else if ((uri.endsWith(StringUtils.PAGE_URL_HOME_COACH) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_HOME).forward(request, response);
			
			return;
		}

		else if ((uri.endsWith(StringUtils.PAGE_URL_RESULT_PLAYER) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_TEST_INFO).forward(request, response);
			
			return;
		} else if ((uri.endsWith(StringUtils.PAGE_URL_RESULT_COACH) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_COACH_RESULT).forward(request, response);
			
			return;
		} else if ((uri.endsWith(StringUtils.PAGE_URL_PROFILE_COACH) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_COACH_PROFILE).forward(request, response);
			
			return;
		} else if ((uri.endsWith(StringUtils.PAGE_URL_PROFILE_PLAYER) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_PLAYER_PROFILE).forward(request, response);
			
			return;
		}

		else if ((uri.endsWith(StringUtils.PAGE_URL_HOME_COACH) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_URL_HOME).forward(request, response);
			
			return;
		} else if ((uri.endsWith(StringUtils.PAGE_URL_UPDATE_NEW_TEST) || uri.endsWith("/"))) {
			request.getRequestDispatcher(StringUtils.SERVLET_UPDATE_TEST).forward(request, response);
			
			return;
		}

		boolean isRegisterServletCoach = uri.endsWith(StringUtils.SERVLET_URL_REGISTER_COACH);
		boolean isLogin = uri.endsWith(StringUtils.PAGE_URL_LOGIN_PLAYER);
		boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN_PLAYER);
		boolean isLoginServletCoach = uri.endsWith(StringUtils.SERVLET_URL_LOGIN_COACH);
		boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
		boolean isRegisterPage = uri.endsWith(StringUtils.PAGE_URL_REGISTER);
		boolean isHomePage = uri.endsWith(StringUtils.PAGE_URL_HOMEPAGE);
		boolean isLoginCoach = uri.endsWith(StringUtils.PAGE_URL_LOGIN_COACH);

		boolean isRegisterServlet = uri.endsWith(StringUtils.SERVLET_URL_REGISTER_PLAYER);
		boolean isAboutUs = uri.endsWith(StringUtils.PAGE_URL_ABOUT_US);
		boolean isHomeServlet = uri.endsWith(StringUtils.SERVLET_URL_HOME);


		HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
		boolean isLoggedIn = session != null && session.getAttribute(StringUtils.PLAYER_USERNAME_ID) != null;
		boolean isLoggedInCoach = session != null && session.getAttribute(StringUtils.COACH_USERNAME_ID) != null;


		if (!isLoggedIn && !isLoggedInCoach
				&& !(isAboutUs || isRegisterServletCoach || isLoginServletCoach || isLoginCoach || isHomePage
						|| isHomeServlet || isLogin || isLoginServlet || isRegisterPage || isRegisterServlet)) {
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN_PLAYER);
		}

		else if (isLoggedIn && !(!isLogin || isLogoutServlet || !isRegisterPage)) { 
																					
																					
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_HOME_PLAYER);
		} else if (isLoggedInCoach && !(!isLoginCoach || isLogoutServlet || !isRegisterPage)) { 
																								
			res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_HOME_COACH);
		} else {
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}