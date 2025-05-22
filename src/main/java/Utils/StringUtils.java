package Utils;

import java.io.File;

public class StringUtils {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/yatrasewa";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	// uploaded image path
	public static final String IMAGE_DIR_USER ="Users\\Nitro\\OneDrive - London Metropolitan University\\Desktop\\YatraSewaa\\src\\main\\webapp\\resources\\images\\User\\";
	public static final String IMAGE_DIR_SAVE_PATH_USER ="C:" + File.separator + IMAGE_DIR_USER;
	
	
	public static final String IMAGE_DIR_AGENT ="Users\\Nitro\\OneDrive - London Metropolitan University\\Desktop\\YatraSewaa\\src\\main\\webapp\\resources\\images\\Agent\\";
	public static final String IMAGE_DIR_SAVE_PATH_AGENT ="C:" + File.separator + IMAGE_DIR_AGENT;
	
	
	
	// registration query
	
	public static final String QUERY_REGISTER_USER = "INSERT INTO user("+
			 "userID,userFirstName, userLastName, userPassword, userEmail, userPhoneNumber) "
			+ "VALUES  (?, ?, ?, ?, ?, ?)";
	
	public static final String QUERY_REGISTER_ADMIN = "INSERT INTO admin("+
			 "adminID, adminFirstName, adminLastName, adminPassword,adminRole, adminPhoneNumber) "
			+ "VALUES  (?, ?, ?, ?, ?, ?)";
	
	
	public static final String QUERY_REGISTER_AGENT = "INSERT INTO agent ("+
			 "agentID,agentFirstName, agentLastName, agentPassword, agentEmail, agentPhoneNumber) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	public static final String QUERY_ADD_BUS = "INSERT INTO bus ("+
			 "busID,busName, destination, departureTime, reachingTime, price, description,pickup,date,agentID,status) "
			+ "VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?)";
	
	public static final String QUERY_GET_ALL_BUSES = "SELECT * FROM bus";
	//user details
	
	public static final String AGENT_ID = "agentID";
	public static final String AGENT_FIRST_NAME = "agent_FirstName";
	public static final String AGENT_LAST_NAME = "agent_LastName";
	public static final String AGENT_PHONE_NUM = "agent_PhoneNumber";
	public static final String AGENT_EMAIL = "agent_Email";
	public static final String AGENT_PASSWORD = "agentPassword";
	public static final String AGENT_RETYPE_PASSWORD = "agent_password_retype";
	
	public static final String AGENT_IMAGE = "agent_image";
	
	
	public static final String ADMIN_ID = "adminID";
	public static final String ADMIN_PASSWORD = "adminPassword";
	public static final String ADMIN_FIRST_NAME = "adminFirstName";
	public static final String ADMIN_LAST_NAME = "adminLastName";
	public static final String ADMIN_ROLE = "adminRole";
	public static final String ADMIN_PHONE_NUMBER = "adminPhoneNumber";
	public static final String ADMIN_RETYPE_PASSWORD = "admiRetypePassword";
	//USER DETAILS
	
	public static final String USER_ID = "userID";
	public static final String USER_FIRST_NAME = "user_FirstName";
	public static final String USER_LAST_NAME = "user_LastName";
	public static final String USER_EMAIL = "user_Email";
	public static final String USER_PASSWORD = "userPassword";
	public static final String USER_RETYPE_PASSWORD = "RetypeUser_Password";
	public static final String USER_PHONE_NUM = "user_PhoneNum";
	public static final String USER_IMAGE = "user_Image";
	
	public static final String BUS_ID = "busId";
	public static final String BUS_NAME = "busName";
	public static final String BUS_DESTINATION = "destination";
	public static final String BUS_DEPARTURE_TIME = "departureTime";
	public static final String BUS_REACHING_TIME = "reachingTime";
	public static final String BUS_PRICE = "price";
	public static final String BUS_DESCRIPTION = "description";
	public static final String BUS_PICKUP = "pickup";
	public static final String BUS_DATE = "date";
	public static final String BUS_AGENT = "agentID";
	
	
	
	// Login Parameter names
			public static final String USERNAME = "username";
			public static final String PASSWORD = "password";
	
	
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";
	
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	public static final String MESSAGE_ERROR_INCORRECT_OLD_PASSWORD = "Your old password does not match please check";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
	public static final String MESSAGE_ERROR_UPDATE = "Cannot update the user!";
	public static final String MESSAGE_SUCCESS_ADD = "Successfully Added!";

	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	
	
	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user WHERE userID = ?";
	public static final String QUERY_LOGIN_AGENT_CHECK = "SELECT * FROM agent WHERE agentID = ?";
	public static final String QUERY_LOGIN_ADMIN_CHECK = "SELECT * FROM admin WHERE adminID = ?";
	public static final String QUERY_GET_USER_IMAGE = "SELECT userImage FROM user WHERE userID = ?";
	public static final String QUERY_GET_Agent_IMAGE = "SELECT agentImage FROM user WHERE agentID = ?";
	public static final String QUERY_GET_AGENT_INFO = "SELECT * FROM agent WHERE agentID = ?";
	public static final String QUERY_GET_USER_INFO = "SELECT * FROM user WHERE userID = ?";
	public static final String QUERY_GET_AGENTS_BUSES = "SELECT * FROM bus WHERE agentID = ?";
	public static final String QUERY_SELECT_SEATS = "SELECT * FROM seats WHERE busID = ?";
	public static final String QUERY_UPDATE_SEATS = "UPDATE seats SET isAvailable = ? WHERE seatID = ?";
	
	public static final String QUERY_ADD_SEAT = "INSERT INTO seats("+
			 "seatID,seatNumber, isAvailable, busID) "
			+ "VALUES  (?, ?, ?, ?)";
	
	public static final String QUERY_GET_BUS_INFO_FROM_BUS_ID = "SELECT * FROM bus WHERE busID = ?";
	public static final String QUERY_GET_USER_INFO_FROM_USER_ID = "SELECT * FROM user WHERE userID = ?";
	public static final String QUERY_GET_AGENT_INFO_FROM_AGENT_ID = "SELECT * FROM agent WHERE agentID = ?";
	public static final String QUERY_GET_ADMIN_INFO_FROM_ADMIN_ID = "SELECT * FROM admin WHERE adminID = ?";
	public static final String QUERY_GET_ALL_USER_INFO = "SELECT * FROM user";
	public static final String QUERY_GET_ALL_AGENT_INFO = "SELECT * FROM agent";
	public static final String QUERY_UPDATE_BUS = "UPDATE bus SET busName = ?, destination = ?, departureTime = ?, reachingTime = ?, price = ?, description = ?, pickup = ?, date = ?  WHERE busID = ?";
	public static final String QUERY_UPDATE_PROFILE = "UPDATE user SET  userFirstName = ?, userLastName = ?, userEmail = ? , userPhoneNumber = ? WHERE userID = ?";
	public static final String QUERY_UPDATE_PROFILE_AGENT = "UPDATE agent SET  agentFirstName = ?, agentLastName = ?, agentEmail = ? , agentPhoneNumber = ? WHERE agentID = ?";
	public static final String QUERY_UPDATE_PROFILE_ADMIN = "UPDATE admin SET  adminFirstName = ?, adminLastName = ?, adminRole = ? , adminPhoneNumber = ? WHERE adminID = ?";
	public static final String QUERY_DELETE_BUS = "DELETE FROM bus WHERE busID = ?";
	public static final String QUERY_BUS_FROM_ID = "SELECT * FROM bus WHERE busID = ?";
	
	public static final String DELETE_USER_QUERY = "DELETE FROM user WHERE userID = ?";
	public static final String DELETE_AGENT_QUERY = "DELETE FROM agent WHERE agentID = ?";
	public static final String DELETE_ADMIN_QUERY = "DELETE FROM admin WHERE adminID = ?";
	
	
	public static final String UPDATE_USER_PASSWORD_QUERY = "UPDATE user SET userPassword = ? WHERE userID = ?";
	public static final String UPDATE_AGENT_PASSWORD_QUERY = "UPDATE agent SET agentPassword = ? WHERE agentID = ?";
	
	public static final String QUERY_SEARCH_ALL_AGENT_BUSES_FROM_TITLE = "SELECT * FROM bus WHERE agentID = ? AND busName LIKE ?";
	public static final String QUERY_SEARCH_ALL_BUS_FROM_TITLE = "SELECT * FROM bus WHERE busName LIKE ?";
	
	public static final String QUERY_SEARCH_ALL_BUSES_FROM_PICKUP_AND_DEST =  
		    "SELECT * FROM bus WHERE pickup = ? AND destination = ? AND date = ? AND status = 'APPROVED'";

	
	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_ADD_ADMIN = "/pages/addAdmin.jsp";
	
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_SEARCH_DEST_BUS = "/pages/SearchBus.jsp";
	public static final String PAGE_URL_FOOTER = "/pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "/pages/header.jsp";
	public static final String PAGE_URL_BUSES = "/pages/buses.jsp";
	public static final String PAGE_URL_PROFILE = "/pages/profile.jsp";
	public static final String PAGE_URL_ADD = "/pages/addBus.jsp";
	public static final String PAGE_URL_UPDATE = "/pages/updateBus.jsp";
	public static final String PAGE_URL_ADMIN_PANEL = "/pages/adminPanel.jsp";
	public static final String PAGE_URL_SEAT_SELECTION = "/pages/seatSelection.jsp";
	public static final String PAGE_URL_CONTACT_US = "/pages/ContactUs.jsp";
	public static final String PAGE_URL_ADD_SEAT = "/pages/addSeats.jsp";
	public static final String PAGE_URL_CONFIRM = "/pages/confirm.jsp";
	public static final String PAGE_URL_BLOG = "/pages/blog.jsp";
	
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/HomePage.jsp";
	public static final String URL_WELCOME = "/welcome.jsp";
	public static final String URL_REGISTER = "/pages/register.jsp";
	public static final String URL_PROFILE = "/profile.jsp";
	public static final String URL_SERVICE = "/buses.jsp";
	public static final String URL_HEADER = "/header.jsp";
	public static final String URL_FOOTER = "/footer.jsp";
	public static final String URL_ADD = "/addBus.jsp";
	public static final String URL_UPDATE = "/update.jsp";
		// End: JSP Route
			
			
	public static final String SERVLET_ADD_SEATS =  "/addSeats"; 
	public static final String SERVLET_URL_REGISTER_USER = "/RegistrationServlet";
	public static final String SERVLET_URL_LOGIN = "/LoginServlet";
	public static final String SERVLET_URL_REGISTER_AGENT =  "/RegisterAgentServlet";
	public static final String SERVLET_URL_LOGOUT = "/LogOutServlet" ;
	public static final String SERVLET_URL_BUSES = "/BusesServlet";
	public static final String SERVLET_URL_INDEX = "/index";
	public static final String SERVLET_URL_ADD_BUSS = "/AddBusServlet";
	public static final String SERVLET_URL_MODIFY = "/ModifyBusDetailsServlet";
	public static final String SERVLET_URL_UPDATE_PASSWORD = "/updatepassword";
	public static final String SERVLET_ADMIN_PANEL = "/adminPanel";
	public static final String SERVLET_SEAT_SELECTION = "/SeatSelection";
	public static final String SERVLET_ADMIN_PANEL_PEN = "/adminApproveBus";
	public static final String SERVLET_UPDATE_PROFILE  = "/ModifyProfile" ;
	public static final String SERVLET_UPDATE_PASSWORD   = "/updatePassword";
	
	
	
	
	
	
	public static final String SUCCESS = "success"; 
	
	
	public static final String BUS_LISTS = "busLists";
	public static final String AGENT_BUS_LISTS = "agentBusesLists";
	public static final String TOP_BUSES_LISTS = "topBusesLists";
	public static final String AGENT_LISTS = "agentlists";
	public static final String USER_LISTS = "userlists";
	
	
	public static final String USER = "user";
	public static final String AGENT = "agent";
	public static final String ADMIN = "admin";
	public static final String JSESSIONID = "JSESSIONID";
}
