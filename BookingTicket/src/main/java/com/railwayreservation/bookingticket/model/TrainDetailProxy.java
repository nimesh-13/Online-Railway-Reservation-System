package com.railwayreservation.bookingticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainDetailProxy {
	
	private int trainId;
	private String name;
	private String startFrom;
	private String endTo;
	private String date;
	private String departureTime;
	private String arrivalTime;
	
	private int availableACseat;
	private int availableSLseat;
	private int ACwaiting;
	private int SLwaiting;
	private int acfare;
	private int slfare;
	

}
