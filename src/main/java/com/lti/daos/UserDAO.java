package com.lti.daos;

import org.springframework.stereotype.Repository;

import com.lti.entities.UserDetails;

@Repository
public class UserDAO extends GenericDAO{

	public boolean isCustomerPresent(String email) {
		return (Long)
				entityManager
				.createQuery("select count(u.user_id) from UserDetails u where u.email = :email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false;
	}
	
	public int findByEmailAndPassword(String email, String password) {
		return (Integer)
				entityManager
				.createQuery("select u.user_id from Users u where u.email = :email and u.password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	public int findByEmail(String email) {
		return (Integer)
				entityManager
				.createQuery("select u.user_id from Users u where u.email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public UserDetails findByEmail(int id) {
		return (UserDetails)
				entityManager
				.createQuery("select * from users u where u.user_id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
	/*
	 * public Object save(Object obj) { return entityManager.merge(obj); }
	 */
	
}
