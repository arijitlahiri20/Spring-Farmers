package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.UserDetails;
import com.lti.entities.Users;
import com.lti.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSender mailSender;
	
	@GetMapping("/hello")
	public @ResponseBody Status hellotest() {
		try {
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("API Access Successful!");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/send-email")
	public @ResponseBody Status sendEmail(@RequestParam("email") String email) {
		try {
			//Updating password in user_details table 
			int id = userService.forgotpassword(email);
			Users user = userService.getUser(id);
			
			//Updating password in Users table 
			userService.updateUserPassword(id,user.getPassword());
			
			String name = user.getFull_name();
			String password = user.getPassword();
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo("arijit.lahiri20@gmail.com");
			message.setSubject("New Password from Schemes for Farmers");
			message.setText("User Name : "+name+"\nNew Password : " +password);
			mailSender.send(message);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Updated Password Sent Successfully!");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
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
			status.setUserid(user.getUser_id());
			status.setUserName(user.getFull_name());
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
