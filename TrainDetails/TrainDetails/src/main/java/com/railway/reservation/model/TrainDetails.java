package com.railway.reservation.model;



import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="TrainDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainDetails {
	
    @Id
    private int trainId;
    private String name;
    private String date;
    private String startFrom;
    private String endTo; 
    private String departureTime;
    private String arrivalTime;
    private List<String> stationName;
    
    private int totalSeat;
	private int availableACseat;
	private int availableSLseat;
	
	private int ACwaiting;
	private int SLwaiting;
	
    
   
    
    
   
    
    

}
