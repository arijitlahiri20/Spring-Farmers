package com.lti.daos;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO{

	public boolean isCustomerPresent(String email) {
		return (Long)
				entityManager
				.createQuery("select count(u.id) from users u where u.email = :email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false;
	}
	
	public int findByEmailAndPassword(String email, String password) {
		return (Integer)
				entityManager
				.createQuery("select u.id from users u where u.email = :email and u.password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
}
