package model;

public class AdminModel {
	

	private String adminID;
	private String adminFirstName;
	private String adminLstName;
	private String adminPassword;
	private String adminRole;
	private String adminPhoneNumber;
	
	public AdminModel() {
		
	}
	
	public AdminModel(String adminID, String adminFirstName, String adminLstName,String adminPassword,String adminRole,
			 String adminPhoneNumber) {
		this.adminID = adminID;
		this.adminFirstName = adminFirstName;
		this.adminLstName = adminLstName;
		this.adminPassword = adminPassword;
		this.adminRole = adminRole;
		this.adminPhoneNumber = adminPhoneNumber;
	}
	
	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminFirstName() {
		return adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminLstName() {
		return adminLstName;
	}

	public void setAdminLstName(String adminLstName) {
		this.adminLstName = adminLstName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}

	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}
	
}
