package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.UserDAO;
import com.lti.entities.UserDetails;
import com.lti.entities.Users;

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

	public Users login(String email, String password) {
		
		int id = userDAO.findByEmailAndPassword(email, password);
		Users user = userDAO.fetch(Users.class, id);
		return user;
	}

	@Override
	public int forgotpassword(String email) {
		
		// Update the Password of user with given email and send back the user ID to controller
		int id = userDAO.findByEmail(email);
		UserDetails user = userDAO.fetch(UserDetails.class, id);
		
		//Logic for generating new password
		//Pending.....
		String newPassword = "12345";
		
		user.setPassword(newPassword);
		userDAO.save(user);
		
		return user.getUser_id();
	}

	@Override
	public UserDetails getUserDetails(int id) {
		UserDetails user = userDAO.fetch(UserDetails.class, id);
		return user;
	}
	@Override
	public Users getUser(int id) {
		Users user = userDAO.fetch(Users.class, id);
		return user;
	}

	public void updateUserPassword(int id, String password) {
		Users user = userDAO.fetch(Users.class, id);
		user.setPassword(password);
		userDAO.save(user);
		
	}
	

}
