package com.lti.controllers;

import java.util.List;

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

import com.lti.dto.ListStatus;
import com.lti.dto.ObjectStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.Bids;
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

	@Autowired
	private MailSender mailSender;

	@GetMapping("/admin/hello")
	public @ResponseBody Status hellotest() {
		try {
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Admin API Access Successful!");
			return status;
		} catch (Exception e) {
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
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/admin/view-document")
	public @ResponseBody Status downloadDocuments(@RequestParam("user_id") int id, HttpServletRequest request) {
		try {
			UserDetails u = adminService.downloadDocuments(id, request);
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bidder Registration Successful!");
			status.setObject(u);
			return status;
		} catch (Exception e) {
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/approve-user")
	public @ResponseBody Status approveUser(@RequestBody Users user) {
		try {
			int result = adminService.approveUser(user.getUser_id());
			if (result == 0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("User Not found/updated!");
				return status;
			} else {
				Users u = adminService.getUser(user.getUser_id());

				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("projectfarmers2@gmail.com");
				message.setTo(u.getEmail());
				message.setSubject("Registration Status");
				message.setText("Your Registration Details and Documents have been approved!"
						+ "\n\nNow you can login using your email id and password" + "\n\nRegards\nFarmers.io");
				mailSender.send(message);

				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("User Credentials Approved by Admin for Login!");
				return status;
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/reject-user")
	public @ResponseBody Status rejecteUser(@RequestBody Users user) {
		try {

			UserDetails u = adminService.getUserDetails(user.getUser_id());

			adminService.rejectUser(u.getUser_id());

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo(u.getEmail());
			message.setSubject("Registration Status");
			message.setText("Your Registration Details and Documents have been Rejected!"
					+ "\n\nPlease try to signup again with valid documents" 
					+ "\n\nRegards\nFarmers.io");
			mailSender.send(message);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User Credentials Rejected by Admin!");
			return status;

		} catch (

		Exception e) {
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
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/admin/view-ph")
	public @ResponseBody Status downloadPhCertificate(@RequestParam("sell_id") int id, HttpServletRequest request) {
		try {
			SellRequests sell = adminService.downloadPh(id, request);
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Ph Certificate Download Successful!");
			status.setObject(sell);
			return status;
		} catch (Exception e) {
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/approve-sell-request")
	public @ResponseBody Status approveSellRequest(@RequestBody SellRequests sell) {
		try {
			Users u = adminService.getUser(sell.getUser_id());
			
			int result = adminService.approveSellRequest(sell.getSell_id());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo(u.getEmail());
			message.setSubject("Sell Request Status");
			message.setText("Your Sell Request with ID = " + sell.getSell_id() +" has been Approves!"
					+ "\n\nPlease view its status in Marketplace." 
					+ "\n\nRegards\nFarmers.io");
			
			if (result == 0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Sell Request Not found/updated!");
				return status;
			} else {
				mailSender.send(message);

				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Sell Request Approved by Admin for Marketplace!");
				return status;
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/reject-sell-request")
	public @ResponseBody Status rejectSellRequest(@RequestBody SellRequests sell) {
		try {
			Users u = adminService.getUser(sell.getUser_id());
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo(u.getEmail());
			message.setSubject("Sell Request Status");
			message.setText("Your Sell Request with ID = " + sell.getSell_id() +" has been Rejected!"
					+ "\n\nPlease try to place a new Sell Request with valid details." 
					+ "\n\nRegards\nFarmers.io");
			
			adminService.rejectSellRequest(sell.getSell_id());

			
			mailSender.send(message);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Sell Request Rejected by Admin!");
			return status;
			
		} catch (Exception e) {
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
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/approve-insurance")
	public @ResponseBody Status approveInsurance(@RequestBody Insurance insurance) {
		try {
			
			Users u = adminService.getUser(insurance.getUser_id());

			int result = adminService.approveInsurance(insurance.getInsurance_id());
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(u.getEmail());
			message.setSubject("Insurance Application Status");
			message.setText("Your Insurance Application with Policy No. = " + insurance.getInsurance_id() +" has been Approved!"
					+ "\n\nPlease check for Inurance in Inurance History and apply for claim through that page." 
					+ "\n\nRegards\nFarmers.io");
			if (result == 0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Insurance Request Not found/updated!");
				return status;
			} else {
				mailSender.send(message);
				
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Insurance Request Approved by Admin for User!");
				return status;
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/reject-insurance")
	public @ResponseBody Status rejectInsurance(@RequestBody Insurance insurance) {
		try {
			Users u = adminService.getUser(insurance.getUser_id());
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo(u.getEmail());
			message.setSubject("Insurance Application Status");
			message.setText("Your Insurance Application with Policy No. = " + insurance.getPolicy_no() +" has been Rejected!"
					+ "\n\nPlease try to Apply for Inurance again with valid details." 
					+ "\n\nRegards\nFarmers.io");
			
			adminService.rejectInsurance(insurance.getInsurance_id());
			
			mailSender.send(message);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Insurance Request Rejected by Admin!");
			return status;
			
		} catch (Exception e) {
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
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/approve-claim")
	public @ResponseBody Status approveClaim(@RequestBody Claim claim) {
		try {
			Insurance ins = adminService.getInsurance(claim.getInsurance_id());
			Users u = adminService.getUser(ins.getUser_id());
			int result = adminService.approveClaim(claim.getClaim_id());
			if (result == 0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Claim Request Not found/updated!");
				return status;
			} else {

				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("projectfarmers2@gmail.com");
				message.setTo(u.getEmail());
				message.setSubject("Claim Application Status");
				message.setText("Your Claim Application for Policy No. = " + ins.getPolicy_no() +" has been Approved!"
						+ "\n\nPlease check for Claim in Claim History Page." 
						+ "\n\nRegards\nFarmers.io");
				mailSender.send(message);

				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Claim Request Approved by Admin for User!");
				return status;
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/admin/reject-claim")
	public @ResponseBody Status rejectClaim(@RequestBody Claim claim) {
		try {
			Insurance ins = adminService.getInsurance(claim.getInsurance_id());
			Users u = adminService.getUser(ins.getUser_id());
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("projectfarmers2@gmail.com");
			message.setTo(u.getEmail());
			message.setSubject("Claim Application Status");
			message.setText("Your Claim Application for Policy No. = " + ins.getPolicy_no() +" has been Rejected!"
					+ "\n\nPlease try to Apply for Claim again with valid details." 
					+ "\n\nRegards\nFarmers.io");
			
			adminService.rejectClaim(claim.getClaim_id());
			
			mailSender.send(message);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim Request Rejected by Admin!");
			return status;
			
		} catch (Exception e) {
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
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/admin/approved-sell-list")
	public @ResponseBody Status getApprovedSellList() {
		try {
			List<SellRequests> contactUsList = adminService.getApprovedSellList();

			ListStatus<SellRequests> status = new ListStatus<SellRequests>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Approved Sell Requests List Successfully sent!");
			status.setList(contactUsList);
			return status;
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/bids-list")
	public @ResponseBody Status getContactUsList(@RequestBody SellRequests sell) {
		try {
			List<Bids> bidsList = adminService.getBidsList(sell.getSell_id());

			ListStatus<Bids> status = new ListStatus<Bids>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("BIds List for sell_id Successfully sent!");
			status.setList(bidsList);
			return status;
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/admin/close-auction")
	public @ResponseBody Status approveClaim(@RequestBody Bids bid) {
		try {
			int result = adminService.closeAuction(bid.getSell_id(), bid.getBid_id());
			if (result == 0) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage("Bids and Sell Not found/updated!");
				return status;
			} else {
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Auction closed by admin and sold to highest bidder!");
				return status;
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
