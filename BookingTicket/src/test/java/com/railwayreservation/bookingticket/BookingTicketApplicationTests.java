package com.railwayreservation.bookingticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.railwayreservation.bookingticket.exception.BookingDetailsNotFoundException;
import com.railwayreservation.bookingticket.model.Booking;
import com.railwayreservation.bookingticket.model.PassangerDetails;
import com.railwayreservation.bookingticket.model.TrainDetailProxy;
import com.railwayreservation.bookingticket.repository.BookingRepository;
import com.railwayreservation.bookingticket.repository.PassangerDetailsRepository;
import com.railwayreservation.bookingticket.service.BookingServiceImpl;

@SpringBootTest
class BookingTicketApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	 @Mock
	    private BookingRepository bookingRepo;

	    @InjectMocks
	    private BookingServiceImpl bookingServiceImpl;
	    
	    @Mock
	    private PassangerDetailsRepository prepo;

	    public void BookingServiceTest() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testGetAllBookingDetails() {
	        // Create a list of dummy booking objects
	        List<Booking> bookings = new ArrayList<>();
	        bookings.add(new Booking(1, 2, "Vidharbh", "mumbai", "nagpur", "2023-06-28", "8.00 pm", "7.00 am", 0, 500, "AC", "confirm", "PNR202306272112272796", 1, "Nimesh", 23, "male"));

	        bookings.add(new Booking(1, 2, "Vidharbh", "mumbai", "nagpur", "2023-06-28", "8.00 pm", "7.00 am", 0, 500, "SL", "confirm", "PNR202306272112272796", 1, "Nimesh", 23, "male"));

	        // Mock the behavior of the bookingRepo.findAll() method
	        when(bookingRepo.findAll()).thenReturn(bookings);

	        // Call the method under test
	        List<Booking> result = bookingServiceImpl.getAllBookingDetails();

	        // Verify the result
	        assertEquals(bookings.size(), result.size());
	        assertEquals(bookings.get(0), result.get(0));
	        assertEquals(bookings.get(1), result.get(1));
	       // assertEquals(bookings.get(2), result.get(2));

	        // Verify that the bookingRepo.findAll() method was called exactly once
	        verify(bookingRepo, times(1)).findAll();
	    }
	    
	    
	    @Test
	    void testCreateTicketBooking() {
	        // Create a dummy TrainDetailProxy object
	        TrainDetailProxy proxyObj = new TrainDetailProxy();
	        proxyObj.setTrainId(1);
	        proxyObj.setName("Train 1");
	        proxyObj.setArrivalTime("10:00 AM");
	        proxyObj.setDepartureTime("12:00 PM");
	        proxyObj.setStartFrom("Station A");
	        proxyObj.setEndTo("Station B");
	        proxyObj.setDate("2023-07-01");
	        proxyObj.setSlfare(100);
	        proxyObj.setAvailableSLseat(2);

	        // Create a dummy PassengerDetails object
	        PassangerDetails passenger = new PassangerDetails();
	        passenger.setPId(1);
	        passenger.setAge(25);
	        passenger.setName("John Doe");
	        passenger.setGender("Male");

	        // Create a dummy ResponseEntity object
	        ResponseEntity<TrainDetailProxy> responseEntity = new ResponseEntity<>(proxyObj, HttpStatus.OK);

	        // Mock the behavior of bookingRepo.findAll() method
	        when(bookingRepo.findAll()).thenReturn(new ArrayList<>());

	        // Mock the behavior of prepo.findById() method
	        when(prepo.findById(1)).thenReturn(Optional.of(passenger));

	        // Mock the behavior of RestTemplate.put() method
	        RestTemplate restTemplate = mock(RestTemplate.class);
	        doNothing().when(restTemplate).put(anyString(), any());

	        // Call the method under test
	        Booking result = bookingServiceImpl.createTicketBooking(responseEntity, "SL");

	        // Verify the behavior
	        assertEquals(proxyObj.getTrainId(), result.getTrainId());
	        assertEquals(proxyObj.getName(), result.getTrainName());
	        assertEquals(proxyObj.getArrivalTime(), result.getArrivalTime());
	        assertEquals(proxyObj.getDepartureTime(), result.getDepartureTime());
	        assertEquals(proxyObj.getStartFrom(), result.getStationFrom());
	        assertEquals(proxyObj.getEndTo(), result.getStationTo());
	        assertEquals(proxyObj.getDate(), result.getDate());
	        assertEquals("SL", result.getTrainClass());

	        assertEquals(passenger.getPId(), result.getPId());
	        assertEquals(passenger.getAge(), result.getAge());
	        assertEquals(passenger.getName(), result.getName());
	        assertEquals(passenger.getGender(), result.getGender());

	        verify(prepo, times(1)).findById(1);
	        verify(bookingRepo, times(1)).findAll();
	        verify(bookingRepo, times(1)).save(result);
	        verify(restTemplate, times(1)).put(anyString(), any());
	    }
	
	    
	    @Test
	    public void testAddPassanger() {
	        // Create a mock PassangerDetails object
	        PassangerDetails passanger = new PassangerDetails();
	        passanger.setPId(1);
	        passanger.setName("John Doe");
	        passanger.setAge(30);
	        
	        // Mock the repository save method
	        when(prepo.save(any(PassangerDetails.class))).thenReturn(passanger);
	        
	        // Call the service method
	        PassangerDetails result = bookingServiceImpl.addPassanger(passanger);
	        
	        // Verify that the repository save method was called with the correct argument
	        verify(prepo).save(passanger);
	        
	        // Assert the result
	        assertEquals(passanger, result);
	    }
	    
	    
	    @Test
	    public void testDeleteBookingDetails() throws BookingDetailsNotFoundException {
	        // Mock the repository deleteBookingByPNRnumber method
	        doNothing().when(bookingRepo).deleteBookingByPNRnumber(anyString());
	        
	        // Call the service method
	        String pnrNumber = "ABC123";
	        bookingServiceImpl.deleteBookingDetails(pnrNumber);
	        
	        // Verify that the repository deleteBookingByPNRnumber method was called with the correct argument
	        verify(bookingRepo).deleteBookingByPNRnumber(pnrNumber);
	    }
	    
	
	}


