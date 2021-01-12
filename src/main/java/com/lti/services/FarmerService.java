package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.FarmerDAO;
import com.lti.entities.SellRequests;

@Service 
@Transactional
public class FarmerService implements IFarmerService{

	@Autowired
	private FarmerDAO farmerDAO;

	public int placeSellRequest(SellRequests sell) {
		SellRequests sr = (SellRequests) farmerDAO.save(sell);
		return sr.getUser_id();
	}
}