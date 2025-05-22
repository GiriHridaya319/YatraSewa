package model;


public class AgentModel {
	private String agentID;
	private String agentFirstName;
	private String agentLastName;
	private String agentPassword;
	private String agentEmail;
	private String agentPhoneNumber;

	public AgentModel() {
		
	}
	public AgentModel(String agentID, String agentFirstName, String agentLastName, String agentPassword,
			String agentEmail, String agentPhoneNumber) {
		this.agentID = agentID;
		this.agentFirstName = agentFirstName;
		this.agentLastName = agentLastName;
		this.agentPassword = agentPassword;
		this.agentEmail = agentEmail;
		this.agentPhoneNumber = agentPhoneNumber;
	}


	public String getAgentID() {
		return agentID;
	}


	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}


	public String getAgentFirstName() {
		return agentFirstName;
	}


	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}


	public String getAgentLastName() {
		return agentLastName;
	}


	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}


	public String getAgentPassword() {
		return agentPassword;
	}


	public void setAgentPassword(String agentPassword) {
		this.agentPassword = agentPassword;
	}


	public String getAgentEmail() {
		return agentEmail;
	}


	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}


	public String getAgentPhoneNumber() {
		return agentPhoneNumber;
	}


	public void setAgentPhoneNumber(String agentPhoneNumber) {
		this.agentPhoneNumber = agentPhoneNumber;
	}


	
}

