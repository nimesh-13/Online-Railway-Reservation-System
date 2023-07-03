package com.railway.reservation.traindetailservice;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railway.reservation.exception.TrainDetailNotFoundException;
import com.railway.reservation.model.TrainDetails;
import com.railway.reservation.model.TrainFare;
import com.railway.reservation.repository.TrainDetailRepository;
import com.railway.reservation.repository.TrainFareRepository;

@Service
public class TrainDetailServiceImpl implements TrainDetailService{
	
	@Autowired
	private TrainDetailRepository repo;
	
	@Autowired 
	TrainFareRepository trainFareRepo;
	
	
	@Override
	public TrainDetails createTrainDetails(TrainDetails traindDetails) {

		return repo.save(traindDetails);
	}

	
	@Override
	public ArrayList<TrainDetails> getTrainDetailsByLocation(String date, String startFrom, String endTo)
			throws TrainDetailNotFoundException {

		ArrayList<TrainDetails> traindetails = repo.findByDateAndStartFromAndEndTo(date, startFrom, endTo);

		if (traindetails.isEmpty()) {
			throw new TrainDetailNotFoundException("Plese enter the valid details");
		}
		return traindetails;
	}
	
	

	@Override
	public void deleteTrainDetailsById(int trainId) {
		repo.deleteById(trainId);

	}

	
	
	@Override
	public List<TrainDetails> getAllTrainDetail() throws TrainDetailNotFoundException {
		List<TrainDetails> trainDetails = repo.findAll();
		if (trainDetails.isEmpty()) {
			throw new TrainDetailNotFoundException("No train details found");
		}
		return trainDetails;
	}
	
	

	@Override
	public TrainDetails updateTrainDetails(int trainId, TrainDetails trainDetails) throws TrainDetailNotFoundException {
		TrainDetails trn = repo.findById(trainId);
		if (trn != null) {

			repo.save(trainDetails);

		} else {
			throw new TrainDetailNotFoundException("Train details not found for the specified trainId: " + trainId);
		}

		return trn;
	}

	
	@Override
	public TrainFare createTrainFare(TrainFare trainFare) {
		return trainFareRepo.save(trainFare);
	}


	@Override
	public List<TrainFare> getAllTrainFare() {
		return trainFareRepo.findAll();
	}


	@Override
	public TrainDetails getTrainByTrainIdAndDate(int trainId, String date) {
		
		return repo.getTrainByTrainIdAndDate(trainId , date);
	}


	@Override
	public TrainFare getTrainFareByTrainIdAndStationFromStationTo(int trainId, String stationFrom, String stationTo) {
		return trainFareRepo.findByTrainIdAndStationFromAndStationTo(trainId , stationFrom , stationTo );
	}


	@Override
	public TrainDetails updateTrainDetails(int trainId, String trainClass,String bookingStatus) 
	{

		TrainDetails trainDetails = repo.findByTrainId(trainId);
		int ACAvailableSeats = trainDetails.getAvailableACseat();
		int SLAvailableSeats = trainDetails.getAvailableSLseat();
		int SLWaiting = trainDetails.getSLwaiting();
		int ACWaiting = trainDetails.getACwaiting();
		
		if(trainClass.equals("SL"))
		{
			if(bookingStatus.equals("Confirm"))
			{
				SLAvailableSeats = SLAvailableSeats - 1;
				trainDetails.setAvailableSLseat(SLAvailableSeats);
				return repo.save(trainDetails);
			}
			else {
				SLWaiting = SLWaiting + 1;
				trainDetails.setSLwaiting(SLWaiting);
				return repo.save(trainDetails);
			}
			
		}
		
		else
		{
			if(bookingStatus.equals("Confirm"))
			{
				ACAvailableSeats = ACAvailableSeats - 1;
				trainDetails.setAvailableACseat(ACAvailableSeats);
				return repo.save(trainDetails);
			}
			else {
				ACWaiting = ACWaiting + 1;
				trainDetails.setACwaiting(ACWaiting);
				return repo.save(trainDetails);
			}
			
		}
//		return null;
	}

	
}
	
	
	

	
    


