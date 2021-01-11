package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.AdminDAO;

@Service
@Transactional
public class AdminService implements IAdminService{

	@Autowired
	private AdminDAO adminDAO;
}
