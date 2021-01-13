package com.lti.services;

import java.util.List;

import com.lti.entities.Bids;
import com.lti.entities.SellRequests;

public interface IBidderService {

	public int placeBid(Bids bid);
	
	public List<Bids> getBidHistory();
	
	public List<SellRequests> getApprovedSellRequests();
}
