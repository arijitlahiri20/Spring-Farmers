package com.lti.daos;

import org.springframework.stereotype.Repository;

import com.lti.entities.Claim;
import com.lti.entities.Insurance;

import java.util.List;

@Repository
public class InsuranceDAO extends GenericDAO{
	
	public List<Insurance> getInsurancesByUserId(int user_id){
		
		return (List<Insurance>)
				entityManager
				.createQuery("select  b from Insurance b where b.user_id = :user_id", Insurance.class)
				.setParameter("user_id",user_id)
				.getResultList();				
	}
		
	public List<Claim> getClaimsByUserId(int insurance_id){
		
		return (List<Claim>)
				entityManager
				.createQuery("select  b from Claim b where b.insurance_id = :insurance_id", Claim.class)
				.setParameter("insurance_id",insurance_id)
				.getResultList();				
	}
	
	
}
