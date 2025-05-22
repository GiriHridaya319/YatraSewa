package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Utils.StringUtils;
import controller.database.DbController;
import model.AdminModel;

/**
 * Servlet implementation class addAdmin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addAdmin" })
public class addAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DbController dbController;

    public addAdmin() {
        this.dbController = new DbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adminID = request.getParameter(StringUtils.ADMIN_ID);
        String adminFirstName = request.getParameter(StringUtils.ADMIN_FIRST_NAME);
        String adminLastName = request.getParameter(StringUtils.ADMIN_LAST_NAME);
        String adminPassword = request.getParameter(StringUtils.ADMIN_PASSWORD);
        String adminRole = request.getParameter(StringUtils.ADMIN_ROLE);
        String adminPhoneNumber = request.getParameter(StringUtils.ADMIN_PHONE_NUMBER);

        AdminModel admin = new AdminModel(adminID, adminFirstName, adminLastName, adminPassword, adminRole, adminPhoneNumber);

        int register_admin = dbController.register_admin(admin);

        if (register_admin == 1) {
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else if (register_admin == 0) {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_ADMIN).forward(request, response);
        } else {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_ADMIN).forward(request, response);
        }
    }
}
