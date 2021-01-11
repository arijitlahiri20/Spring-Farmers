package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.FarmerDAO;

@Service 
@Transactional
public class FarmerService implements IFarmerService{

	@Autowired
	private FarmerDAO farmerDAO;
}
