package com.lti.dto;

public class LoginStatus extends Status{

	private int userid;
	private String userName;
	
	public int getCustomerId() {
		return userid;
	}
	public void setCustomerId(int customerId) {
		this.userid = customerId;
	}
	public String getCustomerName() {
		return userName;
	}
	public void setCustomerName(String customerName) {
		this.userName = customerName;
	}
}
