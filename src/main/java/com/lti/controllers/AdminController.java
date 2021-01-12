package com.lti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.UserApprovalListStatus;
import com.lti.entities.UserDetails;
import com.lti.entities.Users;
import com.lti.services.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/hello")
	public @ResponseBody Status hellotest() {
		try {
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Admin API Access Successful!");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/admin/user-approval-list")
	public @ResponseBody Status getUserApprovalList() {
		try {
			List<UserDetails> userList = adminService.getUserPendingList();
			
			UserApprovalListStatus status = new UserApprovalListStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User Approval List Successfully sent!");
			status.setUserList(userList);
			return status;
		}
		catch(Exception e) {
			UserApprovalListStatus status = new UserApprovalListStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/approve-user")
	public @ResponseBody Status approveUser(@RequestBody Users user) {
		try {
			int result = adminService.approveUser(user.getUser_id());
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User Credentials Approved by Admin for Login!");
			return status;
		}
		catch(Exception e) {
			UserApprovalListStatus status = new UserApprovalListStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
