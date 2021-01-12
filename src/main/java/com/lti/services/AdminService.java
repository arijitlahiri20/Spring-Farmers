package com.lti.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.AdminDAO;
import com.lti.entities.UserDetails;

@Service
@Transactional
public class AdminService implements IAdminService{

	@Autowired
	private AdminDAO adminDAO;

	public List<UserDetails> getUserPendingList() {
		List<UserDetails> list = adminDAO.getAllPendingUsers();
		return list;
	}

	public int approveUser(int user_id) {
		int res = adminDAO.updateUserStatus(user_id);
		
		return res;
	}
}
