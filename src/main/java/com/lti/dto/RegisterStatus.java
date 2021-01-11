package com.lti.dto;

public class RegisterStatus extends Status {

	private int registeredUserId;

	public int getRegisteredCustomerId() {
		return registeredUserId;
	}

	public void setRegisteredCustomerId(int registeredUserId) {
		this.registeredUserId = registeredUserId;
	}
}
