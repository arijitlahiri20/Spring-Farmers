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

	public List<Bids> getTopBids(int sell_id) {
		List<Bids> list = bidderDAO.getTopBids(sell_id);
		return list;
	}

	public List<Bids> getActiveBids(int user_id, int sell_id) {
		List<Bids> list = bidderDAO.getActiveBids(user_id , sell_id);
		return list;
	}

	public int updateBids(Bids bid1) {
		System.out.println(bidderDAO.isBidPresent( bid1.getUser_id(),bid1.getSell_id()) );
		
		if(bidderDAO.isBidPresent( bid1.getUser_id(),bid1.getSell_id()) ) {
			System.out.println("inside if");
			int updatebid =  bidderDAO.update(bid1.getUser_id(),bid1.getSell_id(),bid1.getBid_amount());
			return updatebid;
		}
		else{
			throw new BidderServiceException("Bid not present");
		}
	}

}
