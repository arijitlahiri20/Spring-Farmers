package com.lti.services;

import java.util.ArrayList;
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
		
		insurance.setSeason(insurance.getSeason().toUpperCase());
		insurance.setCrop(insurance.getCrop().toUpperCase());
		Insurance ins = (Insurance) insuranceDAO.save(insurance);
		int amount=ins.getSum_insured()*ins.getArea();
		int prem_amount = 0;
		if(ins.getSeason().equals("SUMMER")) {
			prem_amount = Math.round(amount/18);
		}
		else if(ins.getSeason().equals("WINTER")) {
			prem_amount = Math.round(amount/6);
		}
		if(ins.getSeason().equals("SPRING")) {
			prem_amount = Math.round(amount/12);
		}
		float share = 0;
		if(ins.getSeason().equals("RABI")) {
			share = Math.round(prem_amount*1.5/100);
		}
		else if(ins.getSeason().equals("KHARIF")) {
			share = Math.round(prem_amount*2/100);
		}
		if(ins.getSeason().equals("HORTICULTURE")) {
			share = Math.round(prem_amount*5/100);
		}
		ins.setShare_premium(share);
		ins.setPremium_amount(prem_amount);
		ins.setPolicy_no(insurance.getInsurance_id());
		ins.setInsurance_company("Farmers Inc.");

		return ins;
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
	public List<Claim> getClaimsByInsuranceId(int insurance_id) {
		List <Claim> list = insuranceDAO.getClaimsByInsuranceId(insurance_id);

		return list;
	}

	public List<Insurance> getInuranceHistory(int user_id) {
		List <Insurance> list = insuranceDAO.getInsurancesHistoryByUserId(user_id);
		return list;
	}

	public List<Claim> getClaimHistory(int user_id) {
		List<Claim> claims = new ArrayList<Claim>();
		List <Insurance> list = insuranceDAO.getInsurancesHistoryByUserId(user_id);
		for(Insurance i : list) {
			List<Claim> temp = insuranceDAO.getClaimsByInsuranceId(i.getInsurance_id());
			for(Claim c : temp) {
				claims.add(c);
			}
		}
		return claims;
	}



}
