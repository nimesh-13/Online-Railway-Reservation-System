package com.railwayreservation.bookingticket.controller;


import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.railwayreservation.bookingticket.exception.BookingDetailsNotFoundException;
import com.railwayreservation.bookingticket.model.Booking;
import com.railwayreservation.bookingticket.model.TrainDetailProxy;
import com.railwayreservation.bookingticket.model.PassangerDetails;

import com.railwayreservation.bookingticket.service.BookingService;

import net.bytebuddy.implementation.bytecode.Throw;


@RestController
public class BookingController {
	
	
	private static Logger log = Logger.getLogger(BookingController.class);
		
		@Autowired
		private BookingService bookingService;

		
		/**
		 * Author-Nimesh Waghaye
		 * 
		 * @return All Booking details
		 */
		
		@GetMapping("/getAllBooking")
		public List<Booking> getAllBooking(){
		    return bookingService.getAllBookingDetails();
		}
		
		/**
		 * Author - nimesh waghaye
		 * method - booking ticket
		 * @param trainId
		 * @param date
		 * @param from
		 * @param to
		 * @param trainClass
		 * @return booking ticket
		 */
		@PostMapping("/ticketBooking/{trainId}/{date}/{from}/{to}/{trainClass}")
		public Booking addTicketBooking(@PathVariable int trainId,@PathVariable String date,@PathVariable String from,@PathVariable String to,@PathVariable String trainClass)
		{

		//	"http://localhost:8081/traindetails/trainId/{trainId}/startFrom/{startFrom}/endTo/{endTo}/date/{date}"
			
			String url =  String.format("http://localhost:8081/traindetails/%d/%s/%s/%s",trainId,from,to,date);
			
			ResponseEntity<TrainDetailProxy> responseEntity =
					new RestTemplate().getForEntity(url,
							TrainDetailProxy.class);
			
			log.info("Train ticket book successfuly");
			return bookingService.createTicketBooking(responseEntity,trainClass);
			

		}
		
		/**
		 * Author - nimesh waghaye
		 * @param PNRnumber
		 * @return
		 * @throws BookingDetailsNotFoundException
		 */
		@DeleteMapping("/deleteBookingDetails/{PNRnumber}") 
		public void deleteBookingDetailsByPNRnumber(@PathVariable String PNRnumber ){
			
			bookingService.deleteBookingDetails(PNRnumber);
			 
		}
		
		
		
		@PostMapping("/addpassanger")
		public PassangerDetails createPassangerDetails(@RequestBody PassangerDetails passangerDetails) {
			return bookingService.addPassanger(passangerDetails);
		}
		
		@GetMapping("/getpassangerDetail")
		List<PassangerDetails> getAllPassangerDetails(){
			return bookingService.getAllDetails();
		}
	}
		
		
		
		
		
		
		

