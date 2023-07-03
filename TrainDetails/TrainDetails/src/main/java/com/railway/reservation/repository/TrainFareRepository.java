package com.railway.reservation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railway.reservation.model.TrainFare;


@Repository
public interface TrainFareRepository extends MongoRepository<TrainFare, Integer> {

	TrainFare findByTrainIdAndStationFromAndStationTo(int trainId, String stationFrom, String stationTo);

	
}
