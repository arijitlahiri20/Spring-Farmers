package com.lti.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.BidderDAO;
import com.lti.entities.Bids;
import com.lti.entities.UserDetails;

@Service
@Transactional
public class BidderService implements IBidderService {

	@Autowired
	private BidderDAO bidderDAO;

	public int placeBid(Bids bid) {
		
		Bids addbid = (Bids) bidderDAO.save(bid);
		return addbid.getBid_id();
		
	}
	
	public List<Bids> getBidHistory() {
		List<Bids> list = bidderDAO.getBidHistory();
		return list;
	}
}
