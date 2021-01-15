package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.entities.SoldHistory;

@Repository
public class FarmerDAO extends GenericDAO{

	public List<SoldHistory> getSoldHistory(int user_id) {
		
		return (List<SoldHistory>)
				entityManager
				.createQuery("select s from SoldHistory s where s.user_id = :user_id",SoldHistory.class)
				.setParameter("user_id", user_id)
				.getResultList();
	}

	public List<Bids> getApprovedBids(int sell_id) {
		
		return (List<Bids>)
				entityManager
				.createQuery("select b from Bids b where b.status = :status and b.sell_id = :sell_id",Bids.class)
				.setParameter("status", "PENDING")
				.setParameter("sell_id", sell_id)
				.getResultList();
	}

	public List<SellRequests> getSellRequestById(int user_id) {
		
		return (List<SellRequests>)
				entityManager
				.createQuery("select b from SellRequests b where b.status = :status and b.user_id = :user_id",SellRequests.class)
				.setParameter("status", "APPROVED")
				.setParameter("user_id", user_id)
				.getResultList();
	}

}
