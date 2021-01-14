package com.lti.controllers;

import javax.servlet.http.HttpServletRequest;

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

import com.lti.dto.LoginStatus;
import com.lti.dto.ObjectStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.UploadDocuments;
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
			message.setTo("projectfarmers2@gmail.com");
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
			status.setMessage("Farmer Registration Successful!");
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
			status.setMessage("Bidder Registration Successful!");
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
	
	@PostMapping("/document-upload")
	public @ResponseBody RegisterStatus documentUpload(UploadDocuments docs, HttpServletRequest request) {
		try {
			int res = userService.uploadDocuments(docs,request);
			if(res==0) {
				RegisterStatus status = new RegisterStatus();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Failed to upload Files");
				return status;
			}
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Documents Upload Successful!");
			status.setRegisteredCustomerId(docs.getUser_id());;
			return status;
		}
		catch(Exception e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/getUserDetails")
	public @ResponseBody ObjectStatus getUser(@RequestBody UserDetails user) {
		try {
			UserDetails u = userService.getUserDetails(user.getUser_id());
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bidder Registration Successful!");
			status.setObject(u);
			return status;
		}
		catch(Exception e) {
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/getUser")
	public @ResponseBody ObjectStatus getUser(@RequestBody Users user) {
		try {
			Users u = userService.getUser(user.getUser_id());
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bidder Registration Successful!");
			status.setObject(u);
			return status;
		}
		catch(Exception e) {
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody Users login) {
		try {
			Users user = userService.login(login.getEmail(), login.getPassword());
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			status.setUserid(user.getUser_id());
			status.setUserName(user.getFull_name());
			status.setUserEmail(user.getEmail());
			status.setUserType(user.getUser_type());
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
