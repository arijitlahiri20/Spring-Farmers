package com.lti.services;

import java.util.List;

import com.lti.entities.ContactUs;
import com.lti.entities.SellRequests;
import com.lti.entities.UserDetails;

public interface IAdminService {

	public List<UserDetails> getUserPendingList();
	
	public int approveUser(int user_id);
	
	public List<SellRequests> getSellPendingList();
	
	public int approveSellRequest(int sell_id);
	
	public List<ContactUs> getContactUsList();
	
}
