package com.lti.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.BidderDAO;
import com.lti.entities.BidHistory;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.exception.BidderServiceException;

@Service
@Transactional
public class BidderService implements IBidderService {

	@Autowired
	private BidderDAO bidderDAO;

	public int placeBid(Bids bid) {

		if (bidderDAO.isSellerPresent(bid.getSell_id())) {
			
			Bids addbid = (Bids) bidderDAO.save(bid);
			return addbid.getBid_id();
			
		}
		else {
			throw new BidderServiceException("Seller not present");
		}
	
	}

	public List<BidHistory> getBidHistory(int user_id) {
		List<BidHistory> list = bidderDAO.getBidHistory(user_id);
		return list;
	}

	@Override
	public List<SellRequests> getApprovedSellRequests() {
		
		List<SellRequests> list = bidderDAO.getApprovedSellRequests();
		return list;
	}

}
