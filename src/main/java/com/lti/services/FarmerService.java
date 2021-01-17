package com.lti.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lti.daos.FarmerDAO;
import com.lti.dto.UploadPh;
import com.lti.entities.Bids;
import com.lti.entities.SellRequests;
import com.lti.entities.SoldHistory;

@Service 
@Transactional
public class FarmerService implements IFarmerService{

	@Autowired
	private FarmerDAO farmerDAO;

	public int placeSellRequest(SellRequests sell) {
		SellRequests sr = (SellRequests) farmerDAO.save(sell);
		return sr.getSell_id();
	}
	
	public int uploadPhCertificate(UploadPh docs) {
		int sell_id = docs.getSell_id();
		SellRequests sell = farmerDAO.fetch(SellRequests.class, sell_id);
		
		MultipartFile ph_certificate = docs.getPh_certificate();
		
		//String docUploadLocation = "c:/uploads/";
		String docUploadLocation = "/Users/rumilahiri/Desktop/Arijit/LTI /Project Gladiator/uploads/";
		String targetPhCertificateName = sell_id + "_PhCertificate_" + ph_certificate.getOriginalFilename();
		
		try {
			FileCopyUtils.copy(ph_certificate.getInputStream(), new FileOutputStream(docUploadLocation+targetPhCertificateName));
		} catch(IOException e) {
			e.printStackTrace();
			return 0;
		}
		
		sell.setPh_certificate(targetPhCertificateName);
		farmerDAO.save(sell);
		return 1;
	}

	@Override
	public List<SellRequests> getSellRequestsByUserId(int user_id) {
		List<SellRequests> list = farmerDAO.getSellRequestById(user_id);
		return list;
	}

	public List<SoldHistory> getSoldHistory(int user_id) {
		List<SoldHistory> list = farmerDAO.getSoldHistory(user_id);
		return list;
	}

	public List<Bids> getApprovedBids(int sell_id) {
		List<Bids> list = farmerDAO.getApprovedBids(sell_id);
		return list;
	}

	
	
	
}