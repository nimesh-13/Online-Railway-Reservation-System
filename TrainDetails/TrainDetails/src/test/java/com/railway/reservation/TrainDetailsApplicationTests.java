package com.railway.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.railway.reservation.exception.TrainDetailNotFoundException;
import com.railway.reservation.model.TrainDetails;

import com.railway.reservation.repository.TrainDetailRepository;
import com.railway.reservation.traindetailservice.TrainDetailServiceImpl;



@SpringBootTest
class TrainDetailsApplicationTests {
	
	ArrayList<TrainDetails> trainDetail = new ArrayList<>();
	
	
	
	TrainDetails trainDetails;
	TrainDetails trainDetails1;
	
	 @Autowired
	 private TrainDetailServiceImpl trainDetailServiceImpl;
	 
	 @MockBean
	 private TrainDetailRepository trainDetailRepo;
	 
	 @BeforeEach
	 public void setTrainDetails() {
		 
		 ArrayList<String> stationList = new ArrayList<>();
		   stationList.add("badnera");
		   stationList.add("akola");
		   stationList.add("bhusavad");
		   
		   
		   
		   
		    
		    trainDetails = new TrainDetails();
		    trainDetails.setTrainId(1);
	        trainDetails.setName("Duranto");
	        trainDetails.setDate("2023-03-28");
	        trainDetails.setStartFrom("mumbai");
	        trainDetails.setEndTo("pune");
	        trainDetails.setArrivalTime("7.00 am");
	        trainDetails.setDepartureTime("8.00 pm");
	        trainDetails.setStationName(stationList);
	        trainDetails.setTotalSeat(50);
	        trainDetails.setAvailableACseat(25);
	        trainDetails.setAvailableSLseat(25);
	        trainDetails.setACwaiting(13);
	        trainDetails.setSLwaiting(10);
	        trainDetail.add(trainDetails);
	        
	 }
	
	  
	    
	    @Test
	     void createTrainDetailsTest() {
	    	
	    	Mockito.when(trainDetailRepo.save(trainDetails)).thenReturn(trainDetails);
	    	Assertions.assertEquals(trainDetailServiceImpl.createTrainDetails(trainDetails), trainDetails);
	    	
	    }
	    
	    
	    @Test
	     void getAllTrainDetailsTest() {
	    	Mockito.when(trainDetailRepo.findAll()).thenReturn(trainDetail);
	    	assertEquals(trainDetailServiceImpl.getAllTrainDetail(), trainDetail);
	    }
	    
	    
	 
	    
	    @Test
	    void deleteTrainDetailsByIdTest() {
	    	
	    	//int trainId = (int) 1L;
	    	trainDetailServiceImpl.deleteTrainDetailsById(1);
	    	Mockito.verify(trainDetailRepo,Mockito.times(1)).deleteById(1);
	    }
	    
	    
	    
	    @Test
       void updateTrainDetailTest(){
	    
        int trainId = 1;
        TrainDetails existingTrainDetails = new TrainDetails();
        existingTrainDetails.setTrainId(trainId);
        existingTrainDetails.setName("Duranto");

        TrainDetails updatedTrainDetails = new TrainDetails();
        updatedTrainDetails.setTrainId(trainId);
        updatedTrainDetails.setName("Vidharbh");

        Mockito.when(trainDetailRepo.findById(trainId)).thenReturn(existingTrainDetails);
        Mockito.when(trainDetailRepo.save(updatedTrainDetails)).thenReturn(updatedTrainDetails);

      
        TrainDetails result = trainDetailServiceImpl.updateTrainDetails(trainId, updatedTrainDetails);

        // Assert
        Assertions.assertEquals(trainId, result.getTrainId());
        Assertions.assertEquals("Duranto", result.getName());
        Mockito.verify(trainDetailRepo, Mockito.times(1)).findById(trainId);
        Mockito.verify(trainDetailRepo, Mockito.times(1)).save(updatedTrainDetails);
    }

	    
	    
	    @Test
	     void updateTrainDetails_InvalidTrainId_ThrowsException() {
	       

	        int trainId = 4;
	        TrainDetails updatedTrainDetails = new TrainDetails();
	        updatedTrainDetails.setTrainId(trainId);
	        updatedTrainDetails.setName("Duranto");

	        Mockito.when(trainDetailRepo.findById(trainId)).thenReturn(null);

	        // Act and Assert
	        Assertions.assertThrows(TrainDetailNotFoundException.class, () -> {
	            trainDetailServiceImpl.updateTrainDetails(trainId, updatedTrainDetails);
	        });

	        Mockito.verify(trainDetailRepo, Mockito.times(1)).findById(trainId);
	        Mockito.verify(trainDetailRepo, Mockito.never()).save(updatedTrainDetails);
	    }
	    
	    
	    
	    

	    @Test
	     void getTrainDetailsByLocationTestvalid() throws TrainDetailNotFoundException {
	    	 String date = "2023-03-28";
	    	 String startFrom = "mumbai";
	    	 String endTo = "pune";
	    	    List<TrainDetails> expectedTrainDetails = new ArrayList<>();
	    	    expectedTrainDetails.addAll(trainDetail); // Add the product to the expected products list

	    	    Mockito.when(trainDetailRepo.findByDateAndStartFromAndEndTo(date, startFrom, startFrom)).thenReturn( (ArrayList<TrainDetails>) expectedTrainDetails);

	    	    List<TrainDetails> result = trainDetailServiceImpl.getTrainDetailsByLocation(date, startFrom, startFrom);

	    	    Assertions.assertEquals(expectedTrainDetails, result);
	    }
	    
	    
	    @Test
	     void getTrainDetailsByLocationTestInvalid() {
	    	String date = "2023-03-28";
	    	String startFrom = "mumbai";
	    	String endTo = "pune";
	       
	    	Mockito.when(trainDetailRepo.findByDateAndStartFromAndEndTo(date, startFrom, endTo)).thenReturn(new ArrayList<>());

	        Assertions.assertThrows(TrainDetailNotFoundException.class, () -> {
	            trainDetailServiceImpl.getTrainDetailsByLocation(date, startFrom, endTo);
	        });
	    }
	    
	    
	  
	}



