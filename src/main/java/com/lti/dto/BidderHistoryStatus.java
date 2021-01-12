package com.lti.dto;

import java.util.List;

import com.lti.entities.Bids;

public class BidderHistoryStatus extends Status{

	private List<Bids> bidHistory;

	public List<Bids> getBidHistory() {
		return bidHistory;
	}

	public void setBidHistory(List<Bids> bidHistory) {
		this.bidHistory = bidHistory;
	}
	
	
}
