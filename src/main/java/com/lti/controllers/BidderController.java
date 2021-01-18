package com.lti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ListStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.BidHistory;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.entities.Users;
import com.lti.services.BidderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BidderController {

	@Autowired
	private BidderService bidderService;
	
	@GetMapping("/bidder/hello")
	public @ResponseBody Status hellotest() {
		try {
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bidder Hello");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	

	@PostMapping("/bidder/place-bid")
	public @ResponseBody Status registerBidder(@RequestBody Bids bid) {
		try {
			bid.setStatus("PENDING");
			int id = bidderService.placeBid(bid);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bid Added ! Please wait for Approval.");
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
	
	@PostMapping("/bidder/bid-history")
	public @ResponseBody Status getBidHistory(@RequestBody Users user) {
		try {
			List<BidHistory> bidHistory = bidderService.getBidHistory(user.getUser_id());
			
			ListStatus<BidHistory> status = new ListStatus<BidHistory>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Your Bid History is as Follows: ");
			status.setList(bidHistory);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/bidder/marketplace")
	public @ResponseBody Status getMarketPlace() {
		try {
			List<SellRequests> marketplace = bidderService.getApprovedSellRequests();
			
			ListStatus<SellRequests> status = new ListStatus<SellRequests>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Current Marketplace for crops are as follows : ");
			status.setList(marketplace);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/bidder/topbid")
	public @ResponseBody Status getBidsForSell(@RequestBody SellRequests sell) {
		try {
			List<Bids> topbids = bidderService.getTopBids(sell.getSell_id());
			
			ListStatus<Bids> status = new ListStatus<Bids>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Current Top Bids are : ");
			status.setList(topbids);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/bidder/active-bids")
	public @ResponseBody Status getActiveBids(@RequestBody Bids bid) {
		try {
			List<Bids> activeBids = bidderService.getActiveBids(bid.getUser_id() , bid.getSell_id());
			
			ListStatus<Bids> status = new ListStatus<Bids>();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Your Active Bids are ");
			status.setList(activeBids);
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/bidder/update-bid")
	public @ResponseBody Status updateBid(@RequestBody Bids bid) {
		try {
			int updateBids = bidderService.updateBids(bid);
			
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Bid Updated !");
			status.setRegisteredCustomerId(updateBids);
			return status;
		}
		catch(Exception e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
}
