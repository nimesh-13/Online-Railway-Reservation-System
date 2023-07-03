package com.railwayreservation.paymentgateway.model;

public class PaymentDetails 
{
	private String orderId;
	private String currency ;
	private int amount ;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public PaymentDetails(String orderId, String currency, int amount) {
		super();
		this.orderId = orderId;
		this.currency = currency;
		this.amount = amount;
	}
	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
