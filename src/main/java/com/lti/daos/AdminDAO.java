package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.UserDetails;

@Repository
public class AdminDAO extends GenericDAO {

	public List<UserDetails> getAllPendingUsers() {
		return (List<UserDetails>) 
				entityManager
				.createQuery("select u from UserDetails u where u.status = :status",UserDetails.class)
				.setParameter("status", "PENDING")
				.getResultList();
	}


	public int updateUserStatus(int user_id) {
		return (Integer) 
				entityManager
				.createQuery("Update UserDetails u set u.status = :status where u.user_id = :user_id")
				.setParameter("status", "APPROVED")
				.setParameter("user_id", user_id)
				.executeUpdate();
	}


}
