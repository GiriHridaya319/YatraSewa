package model;

public class SeatModel {
	private String seatID;
	private String seatNumber;
	private boolean isAvailable;
	private String busID;
	
	public SeatModel(String seatID, String seatNumber,  boolean isAvailable, String busID) {
		this.seatID = seatID;
		this.seatNumber = seatNumber;
		this.isAvailable = isAvailable;
		this.busID = busID;
	}
	public SeatModel() {
		
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	// Changed this method name to isAvailable (to follow JavaBean convention)
	public boolean isAvailable() {
	    return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getBusID() {
		return busID;
	}

	public void setBusID(String busID) {
		this.busID = busID;
	}
}
