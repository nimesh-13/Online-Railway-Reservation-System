package com.railwayreservation.bookingticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railwayreservation.bookingticket.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	
	

	public void deleteBookingByPNRnumber(String PNRnumber);

}
