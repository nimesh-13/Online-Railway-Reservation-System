package com.railway.reservation.traindetailservice;

import java.util.ArrayList;

import java.util.List;

import com.railway.reservation.exception.TrainDetailNotFoundException;
import com.railway.reservation.model.TrainDetails;
import com.railway.reservation.model.TrainFare;

public interface TrainDetailService {
	
	public  TrainDetails createTrainDetails(TrainDetails traindDetails);
	
	public 	ArrayList<TrainDetails> getTrainDetailsByLocation( String date ,String startFrom , String endTo)throws TrainDetailNotFoundException;
	
	public void deleteTrainDetailsById(int trainId);
	
	public  TrainDetails updateTrainDetails(int trainId ,TrainDetails trainDetails) throws TrainDetailNotFoundException;
	
	public List<TrainDetails> getAllTrainDetail	() throws TrainDetailNotFoundException;
	
	public TrainFare createTrainFare(TrainFare trainFare);

	public List<TrainFare> getAllTrainFare();

	
	public TrainDetails getTrainByTrainIdAndDate(int trainId, String date);
	
	public TrainFare  getTrainFareByTrainIdAndStationFromStationTo(int trainId , String stationFrom , String stationTo );

	public TrainDetails updateTrainDetails(int trainId, String trainClass, String bookingStatus);
}
