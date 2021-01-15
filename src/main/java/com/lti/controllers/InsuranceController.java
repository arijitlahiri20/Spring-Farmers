package com.lti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ListStatus;
import com.lti.dto.ObjectStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entities.Claim;
import com.lti.entities.Insurance;
import com.lti.entities.Users;
import com.lti.services.InsuranceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
	@PostMapping("/farmer/insurance/calculate")
	public @ResponseBody Status calculatePremium(@RequestBody Insurance insurance) {
		try {
			Insurance i = insuranceService.calculatePremiums(insurance);
			ObjectStatus status = new ObjectStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Insurance applied");
			status.setObject(i);;
			return status;
		}
		catch(Exception e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
		
	}
	
	@PostMapping("/farmer/insurance/registerPolicy")
	public @ResponseBody Status registerPolicy(@RequestBody Insurance insurance) {
		try {
			insurance.setStatus("PENDING");
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
		
		@PostMapping("/farmer/insurance/registerClaim")
		public @ResponseBody Status registerClaim(@RequestBody Claim claim) {
			try {
				claim.setStatus("PENDING");
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
		
		@PostMapping("farmer/insurance/get-Insurances-By-UserId")
		public @ResponseBody Status getInsuranceById(@RequestBody Users user) {
			try {
				List<Insurance> Insurance = insuranceService.getInsurancesByUserId(user.getUser_id());
				
				ListStatus<Insurance> status = new ListStatus<Insurance>();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Your Insurance History is: ");
				status.setList(Insurance);
				return status;
				
			}
			catch(Exception e) {
				Status status = new Status();
				status.setStatus(StatusType.FAILED);
				status.setMessage(e.getMessage());
				return status;
			}
		}

		@PostMapping("farmer/insurance/get-Claims-By-UserId")
		public @ResponseBody Status getClaimssByUserId(@RequestBody Insurance insurance) {
			try {
				List<Claim> Claim = insuranceService.getClaimsByUserId(insurance.getInsurance_id());
				
				ListStatus<Claim> status = new ListStatus<Claim>();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Your claim History is: ");
				status.setList(Claim);
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
