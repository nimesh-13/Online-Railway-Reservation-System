package com.railway.reservation.Controller;

import java.util.ArrayList;

import java.util.List;
import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.reservation.exception.TrainDetailNotFoundException;
import com.railway.reservation.model.BookingDetail;
import com.railway.reservation.model.TrainDetails;
import com.railway.reservation.model.TrainFare;
import com.railway.reservation.traindetailservice.TrainDetailService;


@RestController
@RequestMapping("/traindetails")
public class TrainDetailController {
	
	private static Logger log = Logger.getLogger(TrainDetailController.class);
	
	@Autowired
	private TrainDetailService service;
	
    
	@PostMapping("/postTrainDetail")
	public TrainDetails addTrainDetails(@RequestBody TrainDetails trainDetails) {
		log.info("TrainDetails added successfully.");
		return service.createTrainDetails(trainDetails);
	}
	
	@GetMapping("/searchTrainDetail/{date}/{startFrom}/{endTo}")
	public ArrayList<TrainDetails> searchTrain(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  String date ,@PathVariable String startFrom ,@PathVariable String endTo )throws TrainDetailNotFoundException{
		return service.getTrainDetailsByLocation(date,startFrom, endTo);
	}
	
	@DeleteMapping("/deleteTrainDetail/{trainId}")
	public void deleteTrainDetails(@PathVariable int trainId) {
		log.info("TrainDetails deleted successfully.");
		service.deleteTrainDetailsById(trainId);
		
	}
	
	@PutMapping("/updateTrainDetail/{trainId}")
	public TrainDetails updateTrainDetails(@PathVariable int trainId ,@RequestBody TrainDetails trainDetails) throws TrainDetailNotFoundException {
		log.info("TrainDetails updated successfully.");
		return service.updateTrainDetails(trainId, trainDetails);
		
	}
	
	@GetMapping("/getTrainDetail")  
	public List<TrainDetails> getAllTrainDetails() throws TrainDetailNotFoundException{
		return service.getAllTrainDetail();
	}
	
	
	@PostMapping("/addTrainFare")
	public TrainFare addTrainFare(@RequestBody TrainFare trainFare) {
		return service.createTrainFare(trainFare);
	}
	
	@GetMapping("/getAllTrainFare")	
	public List<TrainFare> getAll(){
		return service.getAllTrainFare();
	}
	
	@GetMapping("/get/{trainId}/{stationFrom}/{stationTo}")
	public TrainFare getdata( @PathVariable int  trainId, @PathVariable String stationFrom, @PathVariable String stationTo) {
		return service.getTrainFareByTrainIdAndStationFromStationTo(trainId, stationFrom, stationTo);
		
	}
	
	
//	updte API call
	@PutMapping("/update/{trainId}/{trainClass}/{bookingStatus}")
	public TrainDetails update(@PathVariable int trainId,@PathVariable String trainClass,@PathVariable String bookingStatus) {
		return service.updateTrainDetails(trainId,trainClass,bookingStatus);

	}
	
	//send details to booking controller
	@GetMapping("/{trainId}/{stationFrom}/{stationTo}/{date}")
	public BookingDetail getBookingDetail(@PathVariable int trainId , @PathVariable String stationFrom,@PathVariable String stationTo ,@PathVariable String date) {
		
		TrainDetails trainDetailsObj = service.getTrainByTrainIdAndDate(trainId ,date);
		TrainFare trainFareobj = service.getTrainFareByTrainIdAndStationFromStationTo(trainId, stationFrom, stationTo);
		
		BookingDetail  bookingDetailObj = new BookingDetail();
		
		
		
		bookingDetailObj.setTrainId(trainDetailsObj.getTrainId());
		bookingDetailObj.setName(trainDetailsObj.getName());
		bookingDetailObj.setStartFrom(trainDetailsObj.getStartFrom());
		bookingDetailObj.setEndTo(trainDetailsObj.getEndTo());
		bookingDetailObj.setDate(trainDetailsObj.getDate());
		bookingDetailObj.setArrivalTime(trainFareobj.getArrivalTime());
		bookingDetailObj.setDepartureTime(trainDetailsObj.getDepartureTime());
		//bookingDetailObj.setArrivalTime(trainDetailsObj.getArrivalTime());
		bookingDetailObj.setAvailableACseat(trainDetailsObj.getAvailableACseat());
		bookingDetailObj.setAvailableSLseat(trainDetailsObj.getAvailableSLseat());
		bookingDetailObj.setAvailableACseat(trainDetailsObj.getACwaiting());
		bookingDetailObj.setSLWaiting(trainDetailsObj.getSLwaiting());
		bookingDetailObj.setAcfare(trainFareobj.getAcfare());
		bookingDetailObj.setSlfare(trainFareobj.getSlfare());
		return  bookingDetailObj;
		
	}
}
