package com.lti.services;

import java.util.List;

import com.lti.entities.BidHistory;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;

public interface IBidderService {

	public int placeBid(Bids bid);

	public List<BidHistory> getBidHistory(int user_id);

	public List<SellRequests> getApprovedSellRequests();
}
