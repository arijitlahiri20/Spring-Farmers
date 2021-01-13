package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

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

}
