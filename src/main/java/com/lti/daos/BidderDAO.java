package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.BidHistory;
import com.lti.entities.Bids;
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

	public List<Bids> getTopBids(int sell_id) {
		
		return (List<Bids>) 
				entityManager
				.createQuery("select u from Bids u where u.sell_id = :sell_id "+"and status=:status "
						+ "order by u.bid_amount desc", Bids.class)
				.setParameter("sell_id", sell_id)
				.setParameter("status", "PENDING").
				setMaxResults(5)
				.getResultList();
	}

	public List<Bids> getActiveBids(int user_id, int sell_id) {
		
		return (List<Bids>) 
				entityManager
				.createQuery("select u from Bids u where u.user_id = :user_id "+"and u.sell_id=:sell_id "+"and status=:status "+ "order by u.bid_amount desc", Bids.class)
				.setParameter("user_id", user_id)
				.setParameter("sell_id", sell_id)
				.setParameter("status", "PENDING").
				setMaxResults(1).
				getResultList();
	}
	
	public boolean isBidPresent(int user_id,int sell_id) {
		return (Long)
				entityManager
				.createQuery("select count(*) from Bids b where b.sell_id =:sell_id "+"and b.user_id =:user_id ")
				.setParameter("sell_id", sell_id)
				.setParameter("user_id", user_id)
				.getSingleResult() == 1 ? true : false;
	}

	public int update(int user_id, int sell_id, int bid_amount) {
		
		return (Integer) entityManager
				.createQuery("Update Bids u set u.bid_amount = :bid_amount where u.sell_id = :sell_id" +" and u.user_id=:user_id ")
				.setParameter("sell_id", sell_id)
				.setParameter("user_id", user_id)
				.setParameter("bid_amount", bid_amount)
				.executeUpdate();
	}
	

}
