package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.BidHistory;
import com.lti.entities.SellRequests;

@Repository
public class BidderDAO extends GenericDAO {

	public List<BidHistory> getBidHistory(int user_id) {

		return (List<BidHistory>) entityManager.createQuery("select  b from BidHistory b where b.user_id = :user_id", BidHistory.class)
				.setParameter("user_id",user_id)
				.getResultList();

	}

	public List<SellRequests> getApprovedSellRequests(){

		return (List<SellRequests>) entityManager.createQuery("select s from SellRequests s where s.status = :status", SellRequests.class)
				.setParameter("status", "APPROVED").getResultList();

	}
	
	public boolean isSellerPresent(int sell_id) {
		return (Long)
				entityManager
				.createQuery("select count(*) from SellRequests s where s.sell_id = :sell_id")
				.setParameter("sell_id", sell_id)
				.getSingleResult() == 1 ? true : false;
	}
	

}
