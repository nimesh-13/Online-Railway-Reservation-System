package com.railwayreservation.bookingticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railwayreservation.bookingticket.model.PassangerDetails;

public interface PassangerDetailsRepository extends JpaRepository<PassangerDetails, Integer> {
	
	

}
