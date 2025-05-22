package model;

import java.time.LocalDate;

public class BusModel {

	private String busID;	
	private String busName;
	private String destination;
	private String departureTime;
	private String reachingTime;
	private String price;
	private String description;
	private String pickup;
	private LocalDate date;
	private String agentUsername;
	private String status ;
	
	public BusModel() {
		
	}

	public BusModel(String busID, String busName, String destination, String departureTime, String reachingTime,
			String price, String description, String pickup, LocalDate date, String agentUsername, String status) {
		this.busID = busID;
		this.busName = busName;
		this.destination = destination;
		this.departureTime = departureTime;
		this.reachingTime = reachingTime;
		this.price = price;
		this.description = description;
		this.pickup = pickup;
		this.date = date;
		this.agentUsername = agentUsername;
		this.status = status;
	}
	
	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}
	public String getBusID() {
		return busID;
	}

	public void setBusID(String busID) {
		this.busID = busID;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getReachingTime() {
		return reachingTime;
	}

	public void setReachingTime(String reachingTime) {
		this.reachingTime = reachingTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getAgentUsername() {
		return agentUsername;
	}

	public void setAgentUsername(String agentUsername) {
		this.agentUsername = agentUsername;
	}
}
