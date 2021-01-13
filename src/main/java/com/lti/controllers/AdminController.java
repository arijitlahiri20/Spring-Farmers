package com.lti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ListStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.Claim;
import com.lti.entities.ContactUs;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
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
			
			ListStatus<UserDetails> status = new ListStatus<UserDetails>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User Approval List Successfully sent!");
			status.setList(userList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/approve-user")
	public @ResponseBody Status approveUser(@RequestBody Users user) {
		try {
			int result = adminService.approveUser(user.getUser_id());
			if(result==0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("User Not found/updated!");
				return status;
			}
			else {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("User Credentials Approved by Admin for Login!");
				return status;
			}
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/admin/sell-approval-list")
	public @ResponseBody Status getSellApprovalList() {
		try {
			List<SellRequests> sellRequestList = adminService.getSellPendingList();
			
			ListStatus<SellRequests> status = new ListStatus<SellRequests>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Sell Request Approval List Successfully sent!");
			status.setList(sellRequestList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/approve-sell-request")
	public @ResponseBody Status approveUser(@RequestBody SellRequests SellRequests) {
		try {
			int result = adminService.approveSellRequest(SellRequests.getSell_id());
			if(result==0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Sell Request Not found/updated!");
				return status;
			}
			else {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Sell Request Approved by Admin for Marketplace!");
				return status;
			}
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/admin/insurance-approval-list")
	public @ResponseBody Status getInsuranceApprovalList() {
		try {
			List<Insurance> sellRequestList = adminService.getInsurancePendingList();
			
			ListStatus<Insurance> status = new ListStatus<Insurance>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Insurance Approval List Successfully sent!");
			status.setList(sellRequestList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/approve-insurance")
	public @ResponseBody Status approveInsurance(@RequestBody Insurance insurance) {
		try {
			int result = adminService.approveInsurance(insurance.getInsurance_id());
			if(result==0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Insurance Request Not found/updated!");
				return status;
			}
			else {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Insurance Request Approved by Admin for User!");
				return status;
			}
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/admin/claim-approval-list")
	public @ResponseBody Status getClaimApprovalList() {
		try {
			List<Claim> sellRequestList = adminService.getClaimPendingList();
			
			ListStatus<Claim> status = new ListStatus<Claim>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim Approval List Successfully sent!");
			status.setList(sellRequestList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/approve-claim")
	public @ResponseBody Status approveClaim(@RequestBody Claim claim) {
		try {
			int result = adminService.approveInsurance(claim.getClaim_id());
			if(result==0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Claim Request Not found/updated!");
				return status;
			}
			else {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Claim Request Approved by Admin for User!");
				return status;
			}
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/admin/contact-us-list")
	public @ResponseBody Status getContactUsList() {
		try {
			List<ContactUs> contactUsList = adminService.getContactUsList();
			
			ListStatus<ContactUs> status = new ListStatus<ContactUs>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Contact Us List Successfully sent!");
			status.setList(contactUsList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
