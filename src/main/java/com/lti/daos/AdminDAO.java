package com.lti.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entities.Bids;
import com.lti.entities.Claim;
import com.lti.entities.ContactUs;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
import com.lti.entities.UserDetails;

@Repository
public class AdminDAO extends GenericDAO {

	public List<UserDetails> getAllPendingUsers() {
		return (List<UserDetails>) 
				entityManager
				.createQuery("select u from UserDetails u where u.status = :status",UserDetails.class)
				.setParameter("status", "PENDING")
				.getResultList();
	}


	public int updateUserStatus(int user_id) {
		return (Integer) 
				entityManager
				.createQuery("Update UserDetails u set u.status = :status where u.user_id = :user_id")
				.setParameter("status", "APPROVED")
				.setParameter("user_id", user_id)
				.executeUpdate();
	}


	public List<SellRequests> getAllPendingSellRequests() {
		return (List<SellRequests>) 
				entityManager
				.createQuery("select u from SellRequests u where u.status = :status",SellRequests.class)
				.setParameter("status", "PENDING")
				.getResultList();
	}


	public int updateSellStatus(int sell_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update SellRequests u set u.status = :status where u.sell_id = :sell_id")
				.setParameter("status", s)
				.setParameter("sell_id", sell_id)
				.executeUpdate();
	}


	public List<ContactUs> getAllContactUs() {
		return (List<ContactUs>) 
				entityManager
				.createQuery("select u from ContactUs u",ContactUs.class)
				.getResultList();
	}


	public List<Insurance> getAllPendingInsurance() {
		return (List<Insurance>) 
				entityManager
				.createQuery("select u from Insurance u where u.status = :status",Insurance.class)
				.setParameter("status", "PENDING")
				.getResultList();
	}


	public int updateInsuranceStatus(int insurance_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update Insurance u set u.status = :status where u.insurance_id = :insurance_id")
				.setParameter("status", s)
				.setParameter("insurance_id", insurance_id)
				.executeUpdate();
	}
	
	public List<Claim> getAllPendingClaim() {
		return (List<Claim>) 
				entityManager
				.createQuery("select u from Claim u where u.status = :status",Claim.class)
				.setParameter("status", "PENDING")
				.getResultList();
	}


	public int updateClaimStatus(int claim_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update Claim u set u.status = :status where u.claim_id = :claim_id")
				.setParameter("status", s)
				.setParameter("claim_id", claim_id)
				.executeUpdate();
	}
	
	public List<Bids> getAllBidsBySellId(int sell_id) {
		return (List<Bids>) 
				entityManager
				.createQuery("select u from Bids u where u.sell_id = :sell_id",Bids.class)
				.setParameter("sell_id", sell_id)
				.getResultList();
	}

	public int closeBid(int bid_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update Bids u set u.status = :status where u.bid_id = :bid_id")
				.setParameter("status", s)
				.setParameter("bid_id", bid_id)
				.executeUpdate();
	}


	public int closeSell(int sell_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update SellRequests u set u.status = :status where u.sell_id = :sell_id")
				.setParameter("status", s)
				.setParameter("sell_id", sell_id)
				.executeUpdate();
	}


	public int updateRemainingBidStatus(int sell_id,int bid_id, String s) {
		return (Integer) 
				entityManager
				.createQuery("Update Bids u set u.status = :status where u.sell_id = :sell_id and u.bid_id != :bid_id")
				.setParameter("status", s)
				.setParameter("sell_id", sell_id)
				.setParameter("bid_id", bid_id)
				.executeUpdate();
		
	}


	public List<SellRequests> getAllApprovedSellRequests() {
		return (List<SellRequests>) 
				entityManager
				.createQuery("select u from SellRequests u where u.status = :status",SellRequests.class)
				.setParameter("status", "APPROVED")
				.getResultList();
	}


}
