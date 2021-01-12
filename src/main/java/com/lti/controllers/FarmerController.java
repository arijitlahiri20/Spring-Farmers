package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.SellRequests;
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
}