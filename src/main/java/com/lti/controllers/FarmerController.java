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
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.entities.SoldHistory;
import com.lti.entities.Users;
import com.lti.services.FarmerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;
	
	@PostMapping("/farmer/place-sell-request")
	public @ResponseBody Status placeSellRequest(@RequestBody SellRequests sell) {
		try {
			int id = farmerService.placeSellRequest(sell);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Crop Sell Request has been placed.");
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
	
	@PostMapping("/farmer/sold-history")
	public @ResponseBody Status getSoldHistory(@RequestBody Users user) {
		try {
			List<SoldHistory> soldHistory = farmerService.getSoldHistory(user.getUser_id());
			ListStatus<SoldHistory> status = new ListStatus<SoldHistory>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Your Sold History is: ");
			status.setList(soldHistory);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/farmer/marketplace")
	public @ResponseBody Status getMarketPlace(@RequestBody Users user) {
		try {
			List<SellRequests> sellList = farmerService.getSellRequestsByUserId(user.getUser_id());
			
			ListStatus<SellRequests> status = new ListStatus<SellRequests>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Current Marketplace Status for your Sell Requests are as follows : ");
			status.setList(sellList);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/farmer/marketplace/sellrequest")
	public @ResponseBody Status getBidsForSell(@RequestBody SellRequests sell) {
		try {
			List<Bids> marketplace1 = farmerService.getApprovedBids(sell.getSell_id());
			
			ListStatus<Bids> status = new ListStatus<Bids>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Current Marketplace Status for your Sell Requests are as follows : ");
			status.setList(marketplace1);
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