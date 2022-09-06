package com.flightservice.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightservice.app.model.Flight;
import com.flightservice.app.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightrepo;
	
	public Integer getSeats(String flightName)
	{
		Flight  f=new Flight();
			f=flightrepo.findByFlightNameIn(flightName);
		
		return f.getFlightseatCapacity();
		
	}
	
	public Integer adjustSeatnumbers(String flightName, int numberoftickets)
	{
		Flight f= new Flight();
		f=flightrepo.findByFlightNameIn(flightName);
		if(numberoftickets<=f.getFlightseatCapacity())
		{
		f.setFlightseatCapacity(f.getFlightseatCapacity() - numberoftickets);
		flightrepo.save(f);
		return f.getFlightseatCapacity();
		}
		else
		{
			throw new RuntimeException("Error in adjusting the seats");
		}
	}
	public Integer adjustAfterCancellationofTicket(String flightName, int numberoftickets)
	{
		Flight f=new Flight();
		f=flightrepo.findByFlightNameIn(flightName);
		f.setFlightseatCapacity(f.getFlightseatCapacity() + numberoftickets);
		flightrepo.save(f);
		return f.getFlightseatCapacity();
	}
	
	public List<Flight> getallFlights()
	{
		return flightrepo.findAll();
	}
	
	public boolean postFlight(Flight flight)
	{
		try {
		flightrepo.save(flight);
		return true;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error in adding the flight");
		}
	}
	
	public String updateFlightdata(Flight flight, String flightName)
	{
		try {
			Flight f= new Flight();
			f=flightrepo.findByFlightNameIn(flightName);
			f.setFlightId(f.getFlightId());
			f.setFlightseatCapacity(flight.getFlightseatCapacity());
			f.setAirlineName(flight.getAirlineName());
			flightrepo.save(f);
			return "Flight data updated successfully";
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error in updating the Flight");
		}
	}
	
	public String removeFlight(String flightName)
	{
		try {
			
			flightrepo.deleteFlight(flightName);
			return "Flight Deleted successfully";
		}catch(Exception e)
		{
			throw new RuntimeException("Error in deleting the FLight");
		}
	}

}
