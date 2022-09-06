package com.flightservice.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightservice.app.model.Flight;
import com.flightservice.app.services.FlightService;

@RestController
public class FlightServiceController {
	
	
	
	@Autowired
	private FlightService flightservice;
	
	@GetMapping("/flight/seatsavailable/{flightName}")
	public Integer getSeatstatus(@PathVariable String flightName)
	{
		return flightservice.getSeats(flightName);
	}
	@GetMapping("/flight/adjustseat/{flightName}/{numberoftickets}")
	public Integer adjustSeats(@PathVariable String flightName, @PathVariable Integer numberoftickets)
	{
		return flightservice.adjustSeatnumbers(flightName, numberoftickets);
	}
	@GetMapping("/flight/adjustseatsaftercancellation/{flightName}/{numberoftickets}")
	public Integer adjustSeatsAfterBookingCancellation(@PathVariable String flightName, @PathVariable Integer numberoftickets)
	{
		return flightservice.adjustAfterCancellationofTicket(flightName, numberoftickets);
	}
	
	@GetMapping("/flight/getall")
	public List<Flight> getAllFlights(@RequestHeader Map<String, String> headers)
	{
		FlightServiceController.validateToken(headers);
		if(validated)
			return flightservice.getallFlights();
		else
			throw new RuntimeException("Error in validating the token");
	}
	
	@PostMapping("/flight/add")
	public String addFlight(@RequestHeader Map<String, String> headers, @RequestBody Flight flight)
	{
		FlightServiceController.validateToken(headers);
		if(validated)
			{if(flightservice.postFlight(flight)) {
				return "Flight Added successfully";
			}
			else {
				throw new RuntimeException("Error in saving the Flight");
			}}
		else
			throw new RuntimeException("Error in validating the token");	
	}
	
	@PutMapping("/flight/update")
	public String updateFlight(@RequestHeader Map<String, String> headers, @RequestBody Flight flight, @RequestParam String flightName)
	{
		FlightServiceController.validateToken(headers);
		if(validated)
			return flightservice.updateFlightdata(flight, flightName);
		else 
			throw new RuntimeException("Token is not valid");
	}
	
	@DeleteMapping("/flight/remove/{flightName}")
	public String deleteFlightdata(@RequestHeader Map<String, String> headers,@PathVariable String flightName )
	{
		FlightServiceController.validateToken(headers);
		if(validated)
			return flightservice.removeFlight(flightName);
		else 
			throw new RuntimeException("Token is not valid");
	}
	
	
	
	
	private static boolean validated=false;
	
	
	public  static void validateToken (Map<String, String> header)
	{
	
		String token="";
		for(String key : header.keySet())
		{
			if(key.equals("authorization"))
				token=header.get(key);
		}
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.set("Authorization", token);
		HttpEntity<Void> requestentity = new HttpEntity<>(httpheader);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:9004/validatejwt",HttpMethod.GET, requestentity,boolean.class);
		validated=response.getBody().booleanValue();
	}

}
