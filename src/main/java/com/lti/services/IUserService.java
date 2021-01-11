package com.lti.services;

import com.lti.entities.UserDetails;
import com.lti.entities.Users;

public interface IUserService {

	public int registerFarmer(UserDetails user);
	
	public int registerBidder(UserDetails user);
	
	public UserDetails login(String email, String password);
	
	public int forgotpassword(String email);
	
	public UserDetails getUserDetails(int id);
	
	public Users getUser(int id);


}
