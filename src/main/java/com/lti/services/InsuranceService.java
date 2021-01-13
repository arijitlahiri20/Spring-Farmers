package com.lti.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.InsuranceDAO;
import com.lti.entities.Claim;
import com.lti.entities.Insurance;

@Service
@Transactional
public class InsuranceService implements IInsuranceService {

	@Autowired
	private InsuranceDAO insuranceDAO;
	
	@Override
	public Insurance calculatePremiums(Insurance insurance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerPolicy(Insurance insurance) {
		Insurance reg =(Insurance) insuranceDAO.save(insurance);
		
		return reg.getInsurance_id();
	}

	@Override
	public int registerClaim(Claim claim) {
		
		Claim request =(Claim) insuranceDAO.save(claim);
		
		return request.getClaim_id();
		
	}

	@Override
	public Insurance getInsuranceById(int insurance_id) {
		
		return null;
	}

	@Override
	public Claim getClaimById(int claim_id) {
		
		return null;
	}

	@Override
	public List<Insurance> getInsurancesByUserId(int user_id) {

		return null;
	}

	@Override
	public List<Claim> getClaimsByUserId(int user_id) {

		return null;
	}



}
