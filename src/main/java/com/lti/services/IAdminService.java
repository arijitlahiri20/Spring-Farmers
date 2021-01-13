package com.lti.services;

import java.util.List;

import com.lti.entities.Bids;
import com.lti.entities.Claim;
import com.lti.entities.ContactUs;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
import com.lti.entities.UserDetails;

public interface IAdminService {

	public List<UserDetails> getUserPendingList();
	
	public int approveUser(int user_id);
	
	public List<SellRequests> getSellPendingList();
	
	public int approveSellRequest(int sell_id);
	
	public List<ContactUs> getContactUsList();
	
	public List<Insurance> getInsurancePendingList();
	
	public int approveInsurance(int insurance_id);
	
	public List<Claim> getClaimPendingList();
	
	public int approveClaim(int claim_id);
	
	public List<SellRequests> getApprovedSellList();
	
	public List<Bids> getBidsList(int sell_id);
	
	public int closeAuction(int sell_id, int bid_id);
	
}
