package com.flightservice.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection="flightdata")
public class Flight {
	
	@Id
	private String flightId;
	
	
	private String flightName;
	
	private int flightseatCapacity;
	private  String airlineName;
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public int getFlightseatCapacity() {
		return flightseatCapacity;
	}
	public void setFlightseatCapacity(int flightseatCapacity) {
		this.flightseatCapacity = flightseatCapacity;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName + ", flightseatCapacity="
				+ flightseatCapacity + ", airlineName=" + airlineName + "]";
	}
	public Flight(String flightId, String flightName, int flightseatCapacity, String airlineName) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.flightseatCapacity = flightseatCapacity;
		this.airlineName = airlineName;
	}
	
	public Flight() {}

}
