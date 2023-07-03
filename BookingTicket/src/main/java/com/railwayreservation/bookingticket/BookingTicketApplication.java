package com.railwayreservation.bookingticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingTicketApplication.class, args);
	}

}
