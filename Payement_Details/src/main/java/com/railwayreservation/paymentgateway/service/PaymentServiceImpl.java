package com.railwayreservation.paymentgateway.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.railwayreservation.paymentgateway.model.PaymentDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService 
{
	private static final String KEY = "rzp_test_wKCHnP0GKL9kQG";
	private static final String KEY_SECRET = "CoJQdrzOQL94CyKdxfFSmJYU";
	private static final String CURRENCY = "INR";

	
		public PaymentDetails createTransaction(Double amount)
		{
			
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("amount", (amount * 100));
				jsonObject.put("currency", CURRENCY);
				
				
				RazorpayClient razorpayClient = new RazorpayClient(KEY,KEY_SECRET);
				
				Order order = razorpayClient.orders.create(jsonObject);
				PaymentDetails paymentDetails = prepareTrasactionDetails(order);
				return paymentDetails;
			} catch (RazorpayException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public PaymentDetails prepareTrasactionDetails(Order order)
		{
			String orderId = order.get("id");
			String currency = order.get("currency");
			int amount = order.get("amount");
			System.out.println(order);
			PaymentDetails paymentDetails = new PaymentDetails(orderId,currency,amount);
			return paymentDetails;
		}
}
