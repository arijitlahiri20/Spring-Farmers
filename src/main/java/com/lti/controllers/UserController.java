package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entities.UserDetails;
import com.lti.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup-farmer")
	public @ResponseBody RegisterStatus registerFarmer(@RequestBody UserDetails user) {
		try {
			int id = userService.registerFarmer(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful!");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(Exception e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	@PostMapping("/signup-bidder")
	public @ResponseBody RegisterStatus registerBidder(@RequestBody UserDetails user) {
		try {
			int id = userService.registerBidder(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful!");
			status.setRegisteredCustomerId(id);
			return status;
		}
		catch(Exception e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody Login login) {
		try {
			UserDetails user = userService.login(login.getEmail(), login.getPassword());
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			status.setCustomerId(user.getUser_id());
			status.setCustomerName(user.getFull_name());
			return status;
		}
		catch(Exception e) {
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;			
		}
	}
}
