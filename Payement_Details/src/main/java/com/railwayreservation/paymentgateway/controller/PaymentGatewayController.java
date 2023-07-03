package com.railwayreservation.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.railwayreservation.paymentgateway.model.PaymentDetails;
import com.railwayreservation.paymentgateway.service.PaymentService;

@RestController
public class PaymentGatewayController 
{
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payment/{amount}")
	public PaymentDetails createTransaction(@PathVariable Double amount)
	{
		return paymentService.createTransaction(amount);
	}
}
