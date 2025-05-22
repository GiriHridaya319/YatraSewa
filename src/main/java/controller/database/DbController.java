package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.StringUtils;
import model.AdminModel;
import model.AgentModel;
import model.PasswordEncryptionWithAes;
import model.SeatModel;
import model.UserLoginModel;
import model.UserModel;
import model.BusModel;

public class DbController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName(StringUtils.DRIVER_NAME);

		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}
	
	public int register_user(UserModel user_details) {
		try {
			PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);
			
			statement.setString(1, user_details.getUserID());
			statement.setString(2, user_details.getUserFirstName());
			statement.setString(3, user_details.getUserLastName());

			statement.setString(4, PasswordEncryptionWithAes.encrypt(user_details.getUserID(), user_details.getUserPassword()));
			statement.setString(5, user_details.getUserEmail());
			statement.setString(6, user_details.getUserPhoneNumber());
			
			int registerd_user = statement.executeUpdate();
			if (registerd_user>0) {
				return 1;
				
			}
			else {
				return 0;
			}	
		
	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; 
	}
		
	}
	
	
	public int register_agent(AgentModel agent_details) {
		try {
			// query to insert agent data in to the database
			PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_AGENT);
			
			//fetching agent data from agent model class
			statement.setString(1, agent_details.getAgentID());
			statement.setString(2, agent_details.getAgentFirstName());
			statement.setString(3, agent_details.getAgentLastName());
			statement.setString(4, PasswordEncryptionWithAes.encrypt(agent_details.getAgentID(), agent_details.getAgentPassword()));
			statement.setString(5, agent_details.getAgentEmail());
			statement.setString(6, agent_details.getAgentPhoneNumber());
			
			int registerd_agent = statement.executeUpdate();
			if (registerd_agent>0) {
				return 1;
				
			}
			else {
				return 0;
			}	
		
	} catch (ClassNotFoundException | SQLException ex) {
		// Printing the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; 
	}
		
	}

	public int getUserLoginInfo(UserLoginModel LoginUser) {

		try {
			PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			statement.setString(1, LoginUser.getUser_ID());
			ResultSet resultUser = statement.executeQuery();

			if (resultUser.next()) {
				String UserData = resultUser.getString(StringUtils.USER_ID);

				String encryptedPwdUser = resultUser.getString(StringUtils.USER_PASSWORD);

				String decryptedPwdUser = PasswordEncryptionWithAes.decrypt(encryptedPwdUser,UserData );

				if (UserData.equals(LoginUser.getUser_ID())
						&& decryptedPwdUser.equals(LoginUser.getUser_password())) {
					return 1;

				} else {
					return 0;
				}
			}
			
			PreparedStatement statementAgent = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_AGENT_CHECK);

			// Set the username in the first parameter of the prepared statement
			statementAgent.setString(1, LoginUser.getUser_ID());

			// Execute the query and store the result set
			ResultSet result = statementAgent.executeQuery();
			

				// Check if there's a record returned from the query
				if (result.next()) {
					// Get the agent username from the database
					String userDb = result.getString(StringUtils.AGENT_ID);
					
					// Get the password from the database
					String passwordDb = result.getString(StringUtils.AGENT_PASSWORD);

					// Decrypted password
					String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userDb);
					
					
					// Check if the username and password match the credentials from the database
					if (userDb.equals(LoginUser.getUser_ID()) && decryptedPwd.equals(LoginUser.getUser_password())) {
						// Login successful, return 1
						return 2;
					} else {
						// Username or password mismatch, return 0
						return 0;
					}
				} 
			
			else {
					// Username not found in the user table or agent table, try admin table
				
				// Prepare a statement using the predefined query for agent login check
				PreparedStatement statementAdmin = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_ADMIN_CHECK);

				// Set the username in the first parameter of the prepared statement
				statementAdmin.setString(1, LoginUser.getUser_ID());

				// Execute the query and store the result set
				ResultSet resultadmin = statementAdmin.executeQuery();

				// Check if there's a record returned from the query
				if (resultadmin.next()) {
					// Get the agent username from the database
					String userDb = resultadmin.getString(StringUtils.ADMIN_ID);
					
					// Get the password from the database
					String passwordDb = resultadmin.getString(StringUtils.ADMIN_PASSWORD);

					// Decrypted password
					
					
					// Check if the username and password match the credentials from the database
					if (userDb.equals(LoginUser.getUser_ID()) && passwordDb.equals(LoginUser.getUser_password())) {
						// Login successful, return 1
						return 3;
					} else {
						// Username or password mismatch, return 0
						return 0;
					}
				} else {
					// Username not found in the user table (both) and admin also,  return -1
					return -1;
				}
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}

	}
	
	
	
	
	
	
	public String getUserImageName(String username){
		
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(StringUtils.QUERY_GET_USER_IMAGE);
			
			// Set the username in the prepared statement
			stmt.setString(1, username);
						
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) { // Move cursor to the first row
			    return result.getString("user_photo");
			
			}	
			return null;
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
			
		}
	}
	
public String getAgentImageName(String username){
		
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement(StringUtils.QUERY_GET_Agent_IMAGE);
			
			// Set the username in the prepared statement
			stmt.setString(1, username);
						
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) { // Move cursor to the first row
			    return result.getString("agent_photo");
			
			}	
			return null;
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
			
		}
	}



public ArrayList<BusModel> getAllBusInfo(){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_ALL_BUSES);
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> bus = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel busDetail = new BusModel();
			busDetail.setBusID(result.getString("BusID"));
			busDetail.setBusName(result.getString("BusName"));
			busDetail.setDate(result.getDate("Date").toLocalDate());
			busDetail.setDepartureTime(result.getString("DepartureTime"));
			busDetail.setDescription(result.getString("Description"));
			busDetail.setDestination(result.getString("Destination"));
			busDetail.setPickup(result.getString("Pickup"));
			busDetail.setPrice(result.getString("Price"));
			busDetail.setReachingTime(result.getString("ReachingTime"));
			busDetail.setAgentUsername(result.getString("agentID"));
			busDetail.setStatus(result.getString("status"));
			bus.add(busDetail);
		}
		return bus;
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
	}
}


public ArrayList<BusModel> getSearchedBusInfoAgent(String bus_Name, String agentID) {	
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_SEARCH_ALL_AGENT_BUSES_FROM_TITLE);
		
		// Set the username in the prepared statement
		stmt.setString(1, agentID);
		stmt.setString(2, "%" + bus_Name + "%");
					
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> searched_services = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
			found_buses.setStatus(result.getString("status"));
			
			searched_services.add(found_buses);
		}
		return searched_services;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public ArrayList<BusModel> getSearchedBusInfoWithDestination(String bus_pickup, String bus_destination, String date) {	
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_SEARCH_ALL_BUSES_FROM_PICKUP_AND_DEST);
		
		// Set the username in the prepared statement
		stmt.setString(1, bus_pickup);
		stmt.setString(2, bus_destination);
		stmt.setString(3, date);
		
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> searched_services = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
			
			searched_services.add(found_buses);
		}
		return searched_services;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public AgentModel getAgentInfo(String agent_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_AGENT_INFO);
		
		// Set the username in the prepared statement
		stmt.setString(1, agent_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			AgentModel agent_info = new AgentModel();
		    agent_info.setAgentFirstName(result.getString("AgentFirstName"));
		    agent_info.setAgentEmail(result.getString("AgentEmail"));
		    agent_info.setAgentID(result.getString("AgentID"));
		    agent_info.setAgentLastName(result.getString("AgentLastName"));
		    agent_info.setAgentPassword(PasswordEncryptionWithAes.decrypt(result.getString("AgentPassword"), agent_id));
		    agent_info.setAgentPhoneNumber(result.getString("AgentPhoneNumber"));
		    
		    
		    return agent_info;
		
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public UserModel getUserInfo(String user_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_USER_INFO);
		
		// Set the username in the prepared statement
		stmt.setString(1, user_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			
			UserModel agent_info = new UserModel();
		    agent_info.setUserFirstName(result.getString("userFirstName"));
		    agent_info.setUserEmail(result.getString("userEmail"));
		    agent_info.setUserID(result.getString("userID"));
		    agent_info.setUserLastName(result.getString("userLastName"));
		    agent_info.setUserPassword(PasswordEncryptionWithAes.decrypt(result.getString("userPassword"), user_id));
		    agent_info.setUserPhoneNumber(result.getString("userPhoneNumber"));
		    
		    
		    return agent_info;
		
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public ArrayList<UserModel> getAllUserInfo(){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_ALL_USER_INFO);
		
					
		ResultSet result = stmt.executeQuery();
		ArrayList<UserModel> allUsers = new ArrayList<UserModel>();
		
		while(result.next()) { // Move cursor to the first row
			UserModel user_info = new UserModel();
		    user_info.setUserID(result.getString("userID"));
		    user_info.setUserFirstName(result.getString("userFirstName"));
		    user_info.setUserLastName(result.getString("userLastName"));
		    user_info.setUserEmail(result.getString("userEmail"));
		    user_info.setUserPassword(result.getString("userPassword"));
		    user_info.setUserPhoneNumber(result.getString("userPhoneNumber"));
		    
		    allUsers.add(user_info);
		   
		
		}	
		 return allUsers;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public ArrayList<AgentModel> getAllAgentInfo( ){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_ALL_AGENT_INFO);
		
					
		ResultSet result = stmt.executeQuery();
		ArrayList<AgentModel> allAgents = new ArrayList<AgentModel>();
		
		while(result.next()) { // Move cursor to the first row
			AgentModel agent_info = new AgentModel();
			 agent_info.setAgentID(result.getString("agentID"));
		    agent_info.setAgentFirstName(result.getString("agentFirstName"));
		    agent_info.setAgentLastName(result.getString("agentLastName"));
		    agent_info.setAgentPhoneNumber(result.getString("agentPhoneNumber"));
		    agent_info.setAgentEmail(result.getString("agentEmail"));
		    agent_info.setAgentPassword(result.getString("agentPassword"));
		   
		   
			   
		    
		    allAgents.add(agent_info);
		    
		
		}	
		return allAgents;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}






public ArrayList<BusModel> getAllAgentBusInfo(String agent_username){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_AGENTS_BUSES);
		
		// Set the username in the prepared statement
		stmt.setString(1, agent_username);
					
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> buses = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
			found_buses.setStatus(result.getString("status"));
			
			buses.add(found_buses);
		}
		return buses;
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
	}
}






public ArrayList<BusModel> getSearchedBusInfo(String busName) {
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_SEARCH_ALL_BUS_FROM_TITLE);
		
		// Set the username in the prepared statement
		stmt.setString(1, "%" + busName + "%");
					
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> buses = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
			found_buses.setStatus(result.getString("status"));
			
			buses.add(found_buses);
		}
		return buses;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	
	
	
}
}


public int addNewBus(BusModel Bus) {
    try {
        PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_ADD_BUS);
        statement.setString(1, Bus.getBusID());
        statement.setString(2, Bus.getBusName());
        statement.setString(3, Bus.getDestination());
        statement.setString(4, Bus.getDepartureTime());
        statement.setString(5, Bus.getReachingTime());
        statement.setString(6, Bus.getPrice());
        statement.setString(7, Bus.getDescription());
        statement.setString(8, Bus.getPickup());
        statement.setDate(9, Date.valueOf(Bus.getDate()));
        statement.setString(10, Bus.getAgentUsername());
        statement.setString(11, "PENDING");  // Set status as PENDING for approval

        int bus_added = statement.executeUpdate();

        if (bus_added > 0) {
            return 1;
        } else {
            return 0;
        }
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        return -1;
    }
}



public UserModel getUserInfoFromId(String user_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_USER_INFO_FROM_USER_ID);
		
		// Set the username in the prepared statement
		stmt.setString(1, user_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			UserModel user = new UserModel();
			user.setUserFirstName(result.getString("userFirstName"));
			user.setUserLastName(result.getString("userLastName"));
			user.setUserEmail(result.getString("userEmail"));
			user.setUserPhoneNumber(result.getString("userPhoneNumber"));
			user.setUserID(result.getString("userID"));
		    return user;
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}


public AgentModel getAgentInfoFromId(String agent_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_AGENT_INFO_FROM_AGENT_ID);
		
		// Set the username in the prepared statement
		stmt.setString(1, agent_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			AgentModel user = new AgentModel();
			user.setAgentFirstName(result.getString("agentFirstName"));
			user.setAgentLastName(result.getString("agentLastName"));
			user.setAgentEmail(result.getString("agentEmail"));
			user.setAgentPhoneNumber(result.getString("agentPhoneNumber"));
			user.setAgentID(result.getString("agentID"));
		    return user;
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}

public AdminModel getAdminInfoFromId(String admin_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_ADMIN_INFO_FROM_ADMIN_ID);
		
		// Set the username in the prepared statement
		stmt.setString(1, admin_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			AdminModel admin = new AdminModel();
			admin.setAdminFirstName(result.getString("adminFirstName"));
			admin.setAdminLstName(result.getString("adminLastName"));
			admin.setAdminRole(result.getString("adminRole"));
			admin.setAdminPhoneNumber(result.getString("adminPhoneNumber"));
			admin.setAdminID(result.getString("adminID"));
		    return admin;
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}




public BusModel getBusInfoFromId(String bus_id){
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils.QUERY_GET_BUS_INFO_FROM_BUS_ID);
		
		// Set the username in the prepared statement
		stmt.setString(1, bus_id);
					
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) { // Move cursor to the first row
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
		    
		    return found_buses;
		}	
		return null;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	}
}




public int updateBus(BusModel bus, String bus_id) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_BUS);

		// Set the student information in the prepared statement
		statement.setString(1, bus.getBusName());
		statement.setString(2, bus.getDestination() );
		statement.setString(3, bus.getDepartureTime());
		statement.setString(4, bus.getReachingTime());
		statement.setString(5, bus.getPrice());
		statement.setString(6, bus.getDescription());
		statement.setString(7, bus.getPickup());
		statement.setDate(8, Date.valueOf(bus.getDate()));
		
		statement.setString(9, bus_id);

		// Execute the update statement and store the number of affected rows
		int result = statement.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; 
		} else {
			return 0; 
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}


public int updateUser(UserModel user, String user_id) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PROFILE);

		// Set the student information in the prepared statement
		statement.setString(1, user.getUserFirstName());
		statement.setString(2, user.getUserLastName() );
		statement.setString(3, user.getUserEmail());
		statement.setString(4, user.getUserPhoneNumber());
		
		statement.setString(5, user_id);

		// Execute the update statement and store the number of affected rows
		int result = statement.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; 
		} else {
			return 0; 
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}


public int updateAgent(AgentModel user, String agent_id) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PROFILE_AGENT);

		// Set the student information in the prepared statement
		statement.setString(1, user.getAgentFirstName());
		statement.setString(2, user.getAgentLastName() );
		statement.setString(3, user.getAgentEmail());
		statement.setString(4, user.getAgentPhoneNumber());
		
		statement.setString(5, agent_id);

		// Execute the update statement and store the number of affected rows
		int result = statement.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; 
		} else {
			return 0; 
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}



public int updateAdmin(AdminModel admin, String admin_id) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PROFILE_ADMIN);

		// Set the student information in the prepared statement
		statement.setString(1, admin.getAdminFirstName());
		statement.setString(2, admin.getAdminLstName());
		statement.setString(3, admin.getAdminRole());
		statement.setString(4, admin.getAdminPhoneNumber());
		
		statement.setString(5, admin_id);

		// Execute the update statement and store the number of affected rows
		int result = statement.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; 
		} else {
			return 0; 
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}





public int deleteBus(String bus_id) {
    try {
        // Start a transaction
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        // Step 1: Delete seats associated with the bus
        PreparedStatement deleteSeatsStmt = connection.prepareStatement("DELETE FROM Seats WHERE busID = ?");
        deleteSeatsStmt.setString(1, bus_id);
        deleteSeatsStmt.executeUpdate();

        // Step 2: Delete the bus itself
        PreparedStatement deleteBusStmt = connection.prepareStatement("DELETE FROM Bus WHERE busID = ?");
        deleteBusStmt.setString(1, bus_id);
        int result = deleteBusStmt.executeUpdate();

        // Commit transaction
        connection.commit();

        // Return success if at least one bus was deleted
        if (result > 0) {
            return 1;
        } else {
            return 0; // No bus deleted (bus ID not found)
        }

    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        return -1; // Internal error
    } catch (Exception ex) {
        ex.printStackTrace();
        return -2; // Server error
    }
}



public List<SeatModel> getSeatsByBusId(String busId) throws SQLException {
    
    try {
    PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_SELECT_SEATS);
    
        stmt.setString(1, busId);
        ResultSet resultSet = stmt.executeQuery();
        ArrayList<SeatModel> seats = new ArrayList<>();
        while (resultSet.next()) {
        	SeatModel seat = new SeatModel();
            seat.setSeatID(resultSet.getString("seatID"));
            seat.setBusID(resultSet.getString("busID"));
            seat.setSeatNumber(resultSet.getString("seatNumber"));
            seat.setAvailable(resultSet.getBoolean("isAvailable"));
            seats.add(seat);
        }
    
    return seats;
    }catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
	}
}

public boolean updateSeatAvailability(String seatID, boolean isAvailable) throws SQLException {
    try {
        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_SEATS);
        stmt.setBoolean(1, isAvailable);
        stmt.setString(2, seatID);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        return false;
    }
}


public ArrayList<BusModel> getBus(String busid) {
	try {
		PreparedStatement stmt = getConnection()
				.prepareStatement(StringUtils. QUERY_BUS_FROM_ID);
		
		// Set the username in the prepared statement
		stmt.setString(1, busid);
					
		ResultSet result = stmt.executeQuery();
		
		ArrayList<BusModel> buses = new ArrayList<BusModel>();
		
		while(result.next()) {
			BusModel found_buses = new BusModel();
			found_buses.setBusID(result.getString("BusID"));
			found_buses.setBusName(result.getString("BusName"));
			found_buses.setDate(result.getDate("Date").toLocalDate());
			found_buses.setDepartureTime(result.getString("DepartureTime"));
			found_buses.setDescription(result.getString("Description"));
			found_buses.setDestination(result.getString("Destination"));
			found_buses.setPickup(result.getString("Pickup"));
			found_buses.setPrice(result.getString("Price"));
			found_buses.setReachingTime(result.getString("ReachingTime"));
			found_buses.setAgentUsername(result.getString("agentID"));
			
			buses.add(found_buses);
		}
		return buses;
		
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
		
	
	
	
}
}


public ArrayList<BusModel> getPendingBuses()  {
    
    try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM bus WHERE status = 'PENDING'");
         ResultSet resultSet = stmt.executeQuery()) {
    	ArrayList<BusModel> pendingBuses = new ArrayList<>();
        while (resultSet.next()) {
            // Create a new BusModel object for each record
            BusModel bus = new BusModel();
            bus.setBusID(resultSet.getString("busID"));
            bus.setBusName(resultSet.getString("busName"));
            bus.setPrice(resultSet.getString("price"));
            bus.setDestination(resultSet.getString("Destination"));
            bus.setPickup(resultSet.getString("Pickup"));
            bus.setDate(resultSet.getDate("Date").toLocalDate()); 
            bus.setAgentUsername(resultSet.getString("agentID"));
            bus.setStatus(resultSet.getString("status"));
            
            // Add the bus to the list
            pendingBuses.add(bus);
        }
        return pendingBuses;
    } catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return null;
    }
    
}
public boolean updateBusStatus(String busId, String status) {
    try {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE bus SET status = ? WHERE busID = ?");
        statement.setString(1, status);
        statement.setString(2, busId);
        return statement.executeUpdate() > 0;
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return false;
    }
}


public int updateUserPassword(String username, String new_password) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement stmt = getConnection().prepareStatement(StringUtils.UPDATE_USER_PASSWORD_QUERY);

		// Set the student information in the prepared statement
		stmt.setString(1, PasswordEncryptionWithAes.encrypt(username, new_password));
		stmt.setString(2, username);

		// Execute the update statement and store the number of affected rows
		int result = stmt.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; // Registration successful
		} else {
			return 0; // Registration failed (no rows affected)
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}

public int updateAgentPassword(String username, String new_password) {
	try {
		// Prepare a statement using the predefined query for mentor registration
		PreparedStatement stmt = getConnection().prepareStatement(StringUtils.UPDATE_AGENT_PASSWORD_QUERY);

		// Set the student information in the prepared statement
		stmt.setString(1, PasswordEncryptionWithAes.encrypt(username, new_password));
		stmt.setString(2, username);

		// Execute the update statement and store the number of affected rows
		int result = stmt.executeUpdate();

		// Check if the update was successful (i.e., at least one row affected)
		if (result > 0) {
			return 1; // Registration successful
		} else {
			return 0; // Registration failed (no rows affected)
		}

	} catch (ClassNotFoundException | SQLException ex) {
		// Print the stack trace for debugging purposes
		ex.printStackTrace();
		return -1; // Internal error
	} catch (Exception ex) {
		ex.printStackTrace();
		return -2; // Server error
	}
}

public int addSeat(SeatModel seat) {
	 try {
		 PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_SEAT);
	       
	       // Set the seat data into the prepared statement
	   			stmt.setString(1, seat.getSeatID());
	   			stmt.setString(2, seat.getSeatNumber());
	   			stmt.setBoolean(3, seat.isAvailable());
	   			stmt.setString(4, seat.getBusID());  // Set boolean availability

	       // Execute the insert
	       int rowsAffected = stmt.executeUpdate();

	       return rowsAffected > 0 ? 1 : 0; // Return 1 if insertion was successful, else 0
	 }catch(ClassNotFoundException |SQLException e) {
	       e.printStackTrace();
	       return 0; // Failure
}



}


public int register_admin(AdminModel admin_details) {
	try {
		PreparedStatement statement = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_ADMIN);
		
		statement.setString(1, admin_details.getAdminID());
		statement.setString(2, admin_details.getAdminFirstName());
		statement.setString(3, admin_details.getAdminLstName());

		statement.setString(4, admin_details.getAdminPassword());
		statement.setString(5, admin_details.getAdminRole());
		statement.setString(6, admin_details.getAdminPhoneNumber());
		
		int registerd_user = statement.executeUpdate();
		if (registerd_user>0) {
			return 1;
			
		}
		else {
			return 0;
		}	
	
} catch (ClassNotFoundException | SQLException ex) {
	// Print the stack trace for debugging purposes
	ex.printStackTrace();
	return -1; 
}
	
}


public int deleteUserInfo(String user_id) {
	try (Connection con = getConnection()) {
		PreparedStatement st = con.prepareStatement(StringUtils.DELETE_USER_QUERY);
		st.setString(1, user_id);
		return st.executeUpdate();
	} catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace(); 
		return -1;
	}
}
public int deleteAgentInfo(String agent_id) {
    try {
        // Start a transaction
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        // Step 1: Get all bus IDs linked to the agent
        PreparedStatement getBusesStmt = connection.prepareStatement("SELECT busID FROM bus WHERE agentID = ?");
        getBusesStmt.setString(1, agent_id);
        ResultSet busesResultSet = getBusesStmt.executeQuery();

        // Step 2: Delete each bus and its associated seats
        while (busesResultSet.next()) {
            String bus_id = busesResultSet.getString("busID");

            // Call deleteBus method to delete the bus and its seats
            int deleteBusResult = deleteBus(bus_id);
            if (deleteBusResult != 1) {
                connection.rollback();
                return -1; // If any bus deletion fails, rollback and return error
            }
        }

        // Step 3: Delete the agent itself
        PreparedStatement deleteAgentStmt = connection.prepareStatement("DELETE FROM agent WHERE agentID = ?");
        deleteAgentStmt.setString(1, agent_id);
        int result = deleteAgentStmt.executeUpdate();

        // Commit transaction
        connection.commit();

        // Return success if at least one agent was deleted
        if (result > 0) {
            return 1; // Success
        } else {
            return 0; // Agent ID not found
        }

    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        return -1; // Internal error
    } catch (Exception ex) {
        ex.printStackTrace();
        return -2; // Server error
    }
}


public int deleteAdminInfo(String admin_id) {
	try (Connection con = getConnection()) {
		PreparedStatement st = con.prepareStatement(StringUtils.DELETE_ADMIN_QUERY);
		st.setString(1, admin_id);
		return st.executeUpdate();
	} catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace(); 
		return -1;
	}
}

public ArrayList<BusModel> getFilteredBuses(String pickup, String destination, String minPrice, String maxPrice) {
    try {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM bus WHERE status = 'APPROVED'");
        ArrayList<Object> params = new ArrayList<>();
        
        if (pickup != null && !pickup.isEmpty()) {
            queryBuilder.append(" AND Pickup LIKE ?");
            params.add("%" + pickup + "%");
        }
        
        if (destination != null && !destination.isEmpty()) {
            queryBuilder.append(" AND Destination LIKE ?");
            params.add("%" + destination + "%");
        }
        
        if (minPrice != null && !minPrice.isEmpty()) {
            queryBuilder.append(" AND CAST(Price AS DECIMAL) >= ?");
            params.add(Double.parseDouble(minPrice));
        }
        
        if (maxPrice != null && !maxPrice.isEmpty()) {
            queryBuilder.append(" AND CAST(Price AS DECIMAL) <= ?");
            params.add(Double.parseDouble(maxPrice));
        }
        
        PreparedStatement stmt = getConnection().prepareStatement(queryBuilder.toString());
        
        // Set parameters
        for (int i = 0; i < params.size(); i++) {
            Object param = params.get(i);
            if (param instanceof String) {
                stmt.setString(i + 1, (String) param);
            } else if (param instanceof Double) {
                stmt.setDouble(i + 1, (Double) param);
            }
        }
        
        ResultSet result = stmt.executeQuery();
        
        ArrayList<BusModel> buses = new ArrayList<>();
        
        while (result.next()) {
            BusModel found_buses = new BusModel();
            found_buses.setBusID(result.getString("BusID"));
            found_buses.setBusName(result.getString("BusName"));
            found_buses.setDate(result.getDate("Date").toLocalDate());
            found_buses.setDepartureTime(result.getString("DepartureTime"));
            found_buses.setDescription(result.getString("Description"));
            found_buses.setDestination(result.getString("Destination"));
            found_buses.setPickup(result.getString("Pickup"));
            found_buses.setPrice(result.getString("Price"));
            found_buses.setReachingTime(result.getString("ReachingTime"));
            found_buses.setAgentUsername(result.getString("agentID"));
            found_buses.setStatus(result.getString("status"));
            
            buses.add(found_buses);
        }
        return buses;
        
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        return null;
    }
}

}



