package com.lti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidderHistoryStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.UserApprovalListStatus;
import com.lti.entities.Bids;
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
	
	@GetMapping("/bidder/bid-history")
	public @ResponseBody Status getBidHistory() {
		try {
			List<Bids> bidHistory = bidderService.getBidHistory();
			
			BidderHistoryStatus status = new BidderHistoryStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Your Bid History is as Follows: ");
			status.setBidHistory(bidHistory);
			return status;
		}
		catch(Exception e) {
			BidderHistoryStatus status = new BidderHistoryStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
}
