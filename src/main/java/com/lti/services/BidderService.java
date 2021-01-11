package com.lti.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.BidderDAO;

@Service
@Transactional
public class BidderService implements IBidderService {

	@Autowired
	private BidderDAO bidderDAO;
}
