package com.railwayreservation.bookingticket.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.railwayreservation.bookingticket.exception.BookingDetailsNotFoundException;
import com.railwayreservation.bookingticket.model.Booking;
import com.railwayreservation.bookingticket.model.TrainDetailProxy;
import com.railwayreservation.bookingticket.model.PassangerDetails;

public interface BookingService {


	public List<Booking> getAllBookingDetails();

	public Booking createTicketBooking(ResponseEntity<TrainDetailProxy> responseEntity, String trainClass)throws BookingDetailsNotFoundException;
	
	public PassangerDetails addPassanger(PassangerDetails passangerDetails);

	public List<PassangerDetails> getAllDetails();

	

	 public void deleteBookingDetails(String PNRnumber) throws BookingDetailsNotFoundException;

	}


