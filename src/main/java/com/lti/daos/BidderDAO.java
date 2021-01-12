package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.Bids;

@Repository
public class BidderDAO extends GenericDAO{

	public List<Bids> getBidHistory() {
		
		return (List<Bids>) 
				entityManager
				.createQuery("select b from Bids b where b.status = :status",Bids.class)
				.setParameter("status", "APPROVED")
				.getResultList();
		
	}
	
	
}
