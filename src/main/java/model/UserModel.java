package model;


public class UserModel {
	
	
	private String userID;	
	private String userFirstName;
	private String userLastName;
	private String userPassword;
	private String userEmail;
	private String userPhoneNumber;
	
	public UserModel() {
		
	}
	
	public UserModel(String userID, String userFirstName, String userLastName, String userPassword, String userEmail,
			String userPhoneNumber) {
		this.userID = userID;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}



	public String getUserEmail() {
		return userEmail;
	}
	

	
}