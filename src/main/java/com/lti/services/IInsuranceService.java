package com.lti.services;

import java.util.List;

import com.lti.entities.Claim;
import com.lti.entities.Insurance;

public interface IInsuranceService {

	public int registerPolicy(Insurance insurance);
	
	public int registerClaim(Claim claim);
	
	public Insurance getInsuranceById(int insurance_id);
	
	public Claim getClaimById(int claim_id);
	
	public List<Insurance> getInsurancesByUserId(int user_id);
	
	public List<Claim> getClaimsByUserId(int user_id);
	
}
