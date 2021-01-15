package com.lti.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.FarmerDAO;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.entities.SoldHistory;

@Service 
@Transactional
public class FarmerService implements IFarmerService{

	@Autowired
	private FarmerDAO farmerDAO;

	public int placeSellRequest(SellRequests sell) {
		SellRequests sr = (SellRequests) farmerDAO.save(sell);
		return sr.getUser_id();
	}

	@Override
	public List<SellRequests> getSellRequestsByUserId(int user_id) {
		List<SellRequests> list = farmerDAO.getSellRequestById(user_id);
		return list;
	}

	public List<SoldHistory> getSoldHistory(int user_id) {
		List<SoldHistory> list = farmerDAO.getSoldHistory(user_id);
		return list;
	}

	public List<Bids> getApprovedBids(int sell_id) {
		List<Bids> list = farmerDAO.getApprovedBids(sell_id);
		return list;
	}

	
	
	
}