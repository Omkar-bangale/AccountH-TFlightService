package com.flightservice.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.flightservice.app.model.Flight;

public interface FlightRepository  extends MongoRepository<Flight, String> {

	@Query(value="{flightName:'?0'}")
	Flight findByFlightNameIn(String flightName);
	
	@Query(value="{flightName:'?0'}", delete=true)
	void deleteFlight(String flightName);
	
}
