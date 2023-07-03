package com.railwayreservation.bookingticket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class PNRGenerator {
	 
	private static final String PNR_PREFIX = "PNR";
    private static final int RANDOM_NUMBER_BOUND = 10000;

    public  String generatePNR() {
        // Generate a timestamp string
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(RANDOM_NUMBER_BOUND);

        // Combine the timestamp and random number to create the PNR
        String pnr = PNR_PREFIX + timestamp + randomNumber;

        return pnr;
    }
}

