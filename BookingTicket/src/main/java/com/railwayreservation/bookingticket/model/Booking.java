package com.railwayreservation.bookingticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Document(collection = "TicketBooking")
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private int trainId;
	private String trainName;
	private String stationFrom;
	private String stationTo;
	private String date;
	private String departureTime;
	private String arrivalTime;
	private int noOfPassanger;
	private int fare;
	private String trainClass;
	private String bookingStatus;
	private String PNRnumber;
	
	private int pId;
	private String name;
	private int age;
	private String gender;		
	
}
