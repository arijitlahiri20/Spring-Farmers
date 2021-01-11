package com.lti.services;

import com.lti.entities.UserDetails;

public interface IUserService {

	public int registerFarmer(UserDetails user);
	
	public int registerBidder(UserDetails user);
	
	public UserDetails login(String email, String password);
}
