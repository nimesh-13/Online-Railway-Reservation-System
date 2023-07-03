package com.railway.reservation.repository;



import java.util.ArrayList;


import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.railway.reservation.model.TrainDetails;


@Repository
public interface TrainDetailRepository extends MongoRepository<TrainDetails,Integer>  {
	
	
	
	public TrainDetails findById(int trainId);

	public ArrayList<TrainDetails> findByDateAndStartFromAndEndTo(String date, String startFrom, String endTo);

	public TrainDetails getTrainByTrainIdAndDate(int trainId, String date);

	public TrainDetails findByTrainId(int trainId);

	
	
	

}
