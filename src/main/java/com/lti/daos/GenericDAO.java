package com.lti.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDAO {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public Object save(Object obj) {
		Object updatedObj = entityManager.merge(obj);
		return updatedObj;
	}

	
	public <E> E fetch(Class<E> clazz, Object pk) {
		E e = entityManager.find(clazz, pk); 
		return e;
	}
	
	
}
