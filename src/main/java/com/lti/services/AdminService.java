package com.lti.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.lti.daos.AdminDAO;
import com.lti.entities.BidHistory;
import com.lti.entities.Bids;
import com.lti.entities.Claim;
import com.lti.entities.ContactUs;
import com.lti.entities.Insurance;
import com.lti.entities.SellRequests;
import com.lti.entities.SoldHistory;
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

	@Override
	public UserDetails downloadDocuments(int user_id, HttpServletRequest request) {
		UserDetails user = adminDAO.fetch(UserDetails.class, user_id);

		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		// creating this downloads folder in case if it doesn't exist
		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();

		// the target location where we will save the files of the customer
		String targetAadharName = tempDownloadPath + user.getAadhar();
		String targetPanName = tempDownloadPath + user.getPan();
		String targetCertificateName = tempDownloadPath + user.getCertificate();
		String targetTraderLicenceName = tempDownloadPath + user.getTrader_license();

		String uploadedImagesPath = "c:/uploads/";
		//String uploadedImagesPath = "/Users/rumilahiri/Desktop/Arijit/LTI /Project Gladiator/uploads/";

		String sourceAadharName = uploadedImagesPath + user.getAadhar();
		String sourcePanName = uploadedImagesPath + user.getPan();
		String sourceCertificateName = uploadedImagesPath + user.getCertificate();
		String sourceTraderLicenceName = uploadedImagesPath + user.getTrader_license();
		try {
			FileCopyUtils.copy(new File(sourceAadharName), new File(targetAadharName));
			FileCopyUtils.copy(new File(sourcePanName), new File(targetPanName));
			if (user.getUser_type().equals("FARMER")) {
				FileCopyUtils.copy(new File(sourceCertificateName), new File(targetCertificateName));
			} else if (user.getUser_type().equals("BIDDER")) {
				FileCopyUtils.copy(new File(sourceTraderLicenceName), new File(targetTraderLicenceName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
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
	
	public SellRequests downloadPh(int id, HttpServletRequest request) {
		SellRequests sell = adminDAO.fetch(SellRequests.class, id);

		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";

		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();

		String targetPhCertificateName = tempDownloadPath + sell.getPh_certificate();
		

		//String uploadedImagesPath = "c:/uploads/";
		String uploadedImagesPath = "/Users/rumilahiri/Desktop/Arijit/LTI /Project Gladiator/uploads/";

		String sourcePhCertificateName = uploadedImagesPath + sell.getPh_certificate();
		
		try {
			FileCopyUtils.copy(new File(sourcePhCertificateName), new File(targetPhCertificateName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sell;
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
	public List<SellRequests> getApprovedSellList() {
		List<SellRequests> list = adminDAO.getAllApprovedSellRequests();
		return list;
	}

	@Override
	public List<Bids> getBidsList(int sell_id) {
		List<Bids> list = adminDAO.getAllBidsBySellId(sell_id);
		return list;
	}

	@Override
	public int closeAuction(int sell_id, int bid_id) {
		int res = adminDAO.closeBid(bid_id, "CLOSED");
		if (res == 1) {
			int result = adminDAO.closeSell(sell_id, "SOLD");
			if (result == 1) {
				SellRequests sell = adminDAO.fetch(SellRequests.class, sell_id);
				Bids bid = adminDAO.fetch(Bids.class, bid_id);

				SoldHistory sold = new SoldHistory();
				sold.setSell_id(sell.getSell_id());
				sold.setUser_id(sell.getUser_id());
				sold.setCrop_type(sell.getCrop_type());
				sold.setCrop_name(sell.getCrop_name());
				sold.setFertilizer_type(sell.getFertilizer_type());
				sold.setQuantity(sell.getQuantity());
				sold.setPh_certificate(sell.getPh_certificate());

				sold.setSold_price(bid.getBid_amount());
				adminDAO.save(sold);

				BidHistory bidHist = new BidHistory();
				bidHist.setBid_id(bid.getBid_id());
				bidHist.setSell_id(bid.getSell_id());
				bidHist.setUser_id(bid.getUser_id());
				bidHist.setBid_amount(bid.getBid_amount());
				bidHist.setCreated_at(new Timestamp(new Date().getTime()));
				adminDAO.save(bidHist);

				adminDAO.updateRemainingBidStatus(sell_id, bid_id, "REJECTED");
			}
			return result;
		}
		return 0;
	}

}
