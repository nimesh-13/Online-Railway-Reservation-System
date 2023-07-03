package com.railwayreservation.paymentgateway.service;

import org.springframework.stereotype.Service;

import com.railwayreservation.paymentgateway.model.PaymentDetails;

@Service
public interface PaymentService 
{

	PaymentDetails createTransaction(Double amount);

}
