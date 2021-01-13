package com.lti.services;

import java.util.List;

import com.lti.entities.SellRequests;

public interface IFarmerService {

	public int placeSellRequest(SellRequests sell);
	
	public List<SellRequests> getSellRequestsByUserId(int user_id);
}
