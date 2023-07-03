package com.railway.reservation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="TrainFare")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class TrainFare {
	
	@Id
	private int stationId;
	private int trainId;
	private int platformNo;
	private String stationName;
	
	private String stationFrom;
	private String stationTo;
	
	private String departureTime;
	private String arrivalTime;
	
	private String destinationArrivalTime;
	
	private int acfare;
	private int slfare;
	
	
	
	
	
	
	

}
