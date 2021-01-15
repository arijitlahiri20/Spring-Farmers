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
		
		int amount=insurance.getSum_insured()*insurance.getArea();
		int prem_amount = 0;
		if(insurance.getSeason().equals("SUMMER")) {
			prem_amount = Math.round(amount/18);
		}
		else if(insurance.getSeason().equals("WINTER")) {
			prem_amount = Math.round(amount/6);
		}
		if(insurance.getSeason().equals("SPRING")) {
			prem_amount = Math.round(amount/12);
		}
		insurance.setPremium_amount(prem_amount);
		insurance.setPolicy_no(insurance.getInsurance_id());
		insurance.setInsurance_company("Farmers Inc.");

		return insurance;
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
		List <Insurance> list = insuranceDAO.getInsurancesByUserId(user_id);

		return list;
	}

	@Override
	public List<Claim> getClaimsByUserId(int insurance_id) {
		List <Claim> list = insuranceDAO.getClaimsByUserId(insurance_id);

		return list;
	}



}
