package com.lti.services;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.daos.AdminDAO;
import com.lti.entities.Claim;
import com.lti.entities.ContactUs;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
import com.lti.entities.UserDetails;
import com.lti.entities.Users;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	private AdminDAO adminDAO;

	public List<UserDetails> getUserPendingList() {
		List<UserDetails> list = adminDAO.getAllPendingUsers();
		return list;
	}

	public int approveUser(int user_id) {
		int res = adminDAO.updateUserStatus(user_id);
		if (res == 1) {
			UserDetails userDetails = adminDAO.fetch(UserDetails.class, user_id);
			Users user = new Users();
			user.setUser_id(userDetails.getUser_id());
			user.setFull_name(userDetails.getFull_name());
			user.setEmail(userDetails.getEmail());
			user.setPassword(userDetails.getPassword());
			user.setUser_type(userDetails.getUser_type());
			user.setCreated_at(new Timestamp(new Date().getTime()));
			adminDAO.save(user);
		}
		return res;
	}

	public List<SellRequests> getSellPendingList() {
		List<SellRequests> list = adminDAO.getAllPendingSellRequests();
		return list;
	}

	public int approveSellRequest(int sell_id) {
		int res = adminDAO.updateSellStatus(sell_id, "APPROVED");
		return res;
	}

	public List<ContactUs> getContactUsList() {
		List<ContactUs> list = adminDAO.getAllContactUs();
		return list;
	}

	@Override
	public List<Insurance> getInsurancePendingList() {
		List<Insurance> list = adminDAO.getAllPendingInsurance();
		return list;
	}

	@Override
	public int approveInsurance(int insurance_id) {
		int res = adminDAO.updateInsuranceStatus(insurance_id, "APPROVED");
		return res;
	}

	@Override
	public List<Claim> getClaimPendingList() {
		List<Claim> list = adminDAO.getAllPendingClaim();
		return list;
	}

	@Override
	public int approveClaim(int claim_id) {
		int res = adminDAO.updateClaimStatus(claim_id, "APPROVED");
		return res;
	}

	@Override
	public int closeAuction(int sell_id, int bid_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
