package com.railwayreservation.bookingticket.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.railwayreservation.bookingticket.exception.BookingDetailsNotFoundException;
import com.railwayreservation.bookingticket.model.Booking;
import com.railwayreservation.bookingticket.model.TrainDetailProxy;
import com.railwayreservation.bookingticket.model.PNRGenerator;
import com.railwayreservation.bookingticket.model.PassangerDetails;

import com.railwayreservation.bookingticket.repository.BookingRepository;
import com.railwayreservation.bookingticket.repository.PassangerDetailsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	
	
	private static Logger log = Logger.getLogger(BookingServiceImpl.class);
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	PassangerDetailsRepository prepo;
	

	

	@Override
	public List<Booking> getAllBookingDetails() throws BookingDetailsNotFoundException{
	    List<Booking> allBooking =  bookingRepo.findAll();
	    if(allBooking.isEmpty()) {
	    	throw new BookingDetailsNotFoundException("Booking Details is not available");
	    }
	    
	    return allBooking;
	}
	

	@Override
	public Booking createTicketBooking(ResponseEntity<TrainDetailProxy> responseEntity, String trainClass) {
		
		
		TrainDetailProxy proxyobj = responseEntity.getBody();
        
		Booking booking = new Booking();
		
		List<Booking> bookingObj = bookingRepo.findAll();
		
		
		
		booking.setTrainId(proxyobj.getTrainId());
		booking.setTrainName(proxyobj.getName());
		booking.setArrivalTime(proxyobj.getArrivalTime());
		booking.setDepartureTime(proxyobj.getDepartureTime());
		booking.setStationFrom(proxyobj.getStartFrom());
		booking.setStationTo(proxyobj.getEndTo());
		booking.setDate(proxyobj.getDate());
		booking.setStationTo(proxyobj.getEndTo());
		booking.setTrainClass(trainClass);
		
		
		Optional<PassangerDetails> passanger = prepo.findById(1);
		booking.setPId(passanger.get().getPId());
		booking.setAge(passanger.get().getAge());
		booking.setName(passanger.get().getName());
		booking.setGender(passanger.get().getGender());
		

		PNRGenerator pnr = new PNRGenerator();
		booking.setPNRnumber(pnr.generatePNR());
		
		
		if(trainClass.equals("SL"))
		{
			int trainId = responseEntity.getBody().getTrainId();
			int SLFare = responseEntity.getBody().getSlfare();
			int SLAvailableSeats =  responseEntity.getBody().getAvailableSLseat();
			
			booking.setFare(SLFare);
			String bookingStatus = null;
			
			
			if(SLAvailableSeats > 0)
			{
				bookingStatus = "Confirm";
				booking.setBookingStatus(bookingStatus);
				
				RestTemplate restTemplate = new RestTemplate();
				// availble seats update with -1
				String sf2=String.format("http://localhost:8081/traindetails/update/%d/%s/%s",trainId,trainClass,bookingStatus);
				restTemplate.put(sf2, TrainDetailProxy.class);
				
				// add booking details
				 return bookingRepo.save(booking);
				 
			}
			else {
				bookingStatus = "Waiting";
				// waiting update with +1		
				
				booking.setBookingStatus(bookingStatus);
				// availble seats update with -1
				
				String sf2=String.format("http://localhost:8081/traindetails/update/%d/%s/%s",trainId,trainClass,bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(sf2, String.class);
				
				// add booking details
				 return bookingRepo.save(booking);
			}
			
		}
		
		else {
			int trainId = responseEntity.getBody().getTrainId();
			int ACFare = responseEntity.getBody().getAcfare();
			int ACAvailableSeats = responseEntity.getBody().getAvailableACseat();
			booking.setFare(ACFare);
			String bookingStatus = null;
			
			if(ACAvailableSeats > 0)
			{
				bookingStatus = "Confirm";
				// availble seats update with -1
				
				booking.setBookingStatus(bookingStatus);
				// availble seats update with -1
				
				String Url=String.format("http://localhost:8081/traindetails/update/%d/%s/%s",trainId,trainClass,bookingStatus);
				
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(Url, String.class);
				
				
				// add booking details
				 return bookingRepo.save(booking);
			}
			else {
				bookingStatus = "Waiting";
				// waiting update with +1
				booking.setBookingStatus(bookingStatus);
				// availble seats update with -1
				String sf2=String.format("http://localhost:8081/traindetails/update/%d/%s/%s",trainId,trainClass,bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(sf2, String.class);
				
				// add booking details
				 return bookingRepo.save(booking);
			}
			
		  }
		}

	
	
	@Override
	public void deleteBookingDetails(String PNRnumber) throws BookingDetailsNotFoundException  {
	
		bookingRepo.deleteBookingByPNRnumber(PNRnumber);
		
	}
	
		

	
	

	@Override
	public PassangerDetails addPassanger(PassangerDetails passangerDetails) {
		return prepo.save(passangerDetails);
	}

	@Override
	public List<PassangerDetails> getAllDetails() {
		return prepo.findAll();
	}

	

	}
	



