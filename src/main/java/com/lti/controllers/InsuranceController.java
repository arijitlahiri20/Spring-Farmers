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
import com.lti.entities.Claim;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
import com.lti.services.InsuranceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
	@PostMapping("/insurance/registerPolicy")
	public @ResponseBody Status registerPolicy(@RequestBody Insurance insurance) {
		try {
			int id = insuranceService.registerPolicy(insurance);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Insurance applied");
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
		
		@PostMapping("/insurance/registerClaim")
		public @ResponseBody Status registerClaim(@RequestBody Claim claim) {
			try {
				int id = insuranceService.registerClaim(claim);
				RegisterStatus status = new RegisterStatus();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Applied for claim");
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
