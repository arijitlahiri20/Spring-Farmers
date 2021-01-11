package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.UserDAO;
import com.lti.entities.UserDetails;

@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	private UserDAO userDAO;
	
	public int registerFarmer(UserDetails user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int registerBidder(UserDetails user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public UserDetails login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
