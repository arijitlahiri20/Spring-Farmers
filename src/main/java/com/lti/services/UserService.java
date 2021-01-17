package com.lti.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lti.daos.UserDAO;
import com.lti.dto.UploadDocuments;
import com.lti.entities.ContactUs;
import com.lti.entities.UserDetails;
import com.lti.entities.Users;
import com.lti.exception.UserServiceException;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;

	public int registerFarmer(UserDetails user) {
		
		  if (userDAO.isCustomerPresent(user.getEmail())) {
			  
			  throw new UserServiceException("Farmer already registered");  
		  } 
		  else {
		 
			UserDetails updatedFarmer = (UserDetails) userDAO.save(user);
			return updatedFarmer.getUser_id();
		}
	}

	public int registerBidder(UserDetails user) {
		if (userDAO.isCustomerPresent(user.getEmail())) {

			throw new UserServiceException("Bidder already registered");
		}
		else {
		UserDetails bidderDetails = (UserDetails) userDAO.save(user);
		return bidderDetails.getUser_id();
		}
	}

	public Users login(String email, String password) {

		int id = userDAO.findByEmailAndPassword(email, password);
		Users user = userDAO.fetch(Users.class, id);
		return user;
	}

	@Override
	public int forgotpassword(String email) {

		// Update the Password of user with given email and send back the user ID to
		// controller
		int id = userDAO.findByEmail(email);
		UserDetails user = userDAO.fetch(UserDetails.class, id);

		// Logic for generating new password
		// Pending.....
		String newPassword = "12345";

		user.setPassword(newPassword);
		userDAO.save(user);

		return user.getUser_id();
	}

	@Override
	public UserDetails getUserDetails(int id) {
		UserDetails user = userDAO.fetch(UserDetails.class, id);
		return user;
	}

	@Override
	public Users getUser(int id) {
		Users user = userDAO.fetch(Users.class, id);
		return user;
	}

	public void updateUserPassword(int id, String password) {
		Users user = userDAO.fetch(Users.class, id);
		user.setPassword(password);
		userDAO.save(user);

	}

	public int uploadDocuments(UploadDocuments docs, HttpServletRequest request) {
		int id = docs.getUser_id();
		UserDetails user = getUserDetails(id);
		MultipartFile aadhar = docs.getAadhar();
		MultipartFile pan = docs.getPan();
		MultipartFile certificate = docs.getCertificate();
		MultipartFile trader_licence = docs.getTrader_licence();
		
		String docUploadLocation = "c:/uploads/";
		//String docUploadLocation = "/Users/rumilahiri/Desktop/Arijit/LTI /Project Gladiator/uploads/";
		String targetAadharName = id + "_Aadhar_" + aadhar.getOriginalFilename();
		String targetPanName = id + "_Pan_" + pan.getOriginalFilename();
		String targetCertificateName = id + "_Certificate_" + certificate.getOriginalFilename();
		String targetTraderLicenceName = id + "_TraderLicence_" + trader_licence.getOriginalFilename();
		
		try {
			FileCopyUtils.copy(aadhar.getInputStream(), new FileOutputStream(docUploadLocation+targetAadharName));
			FileCopyUtils.copy(pan.getInputStream(), new FileOutputStream(docUploadLocation+targetPanName));
			if(user.getUser_type().equals("FARMER")) {
				FileCopyUtils.copy(certificate.getInputStream(), new FileOutputStream(docUploadLocation+targetCertificateName));
			}
			else if(user.getUser_type().equals("BIDDER")){
				FileCopyUtils.copy(trader_licence.getInputStream(), new FileOutputStream(docUploadLocation+targetTraderLicenceName));
			}
		} catch(IOException e) {
			e.printStackTrace();
			return 0;
		}
		
		user.setAadhar(targetAadharName);
		user.setPan(targetPanName);
		if(user.getUser_type().equals("FARMER")) {
			user.setCertificate(targetCertificateName);
		}
		else if(user.getUser_type().equals("BIDDER")){
			user.setTrader_license(targetTraderLicenceName);;
		}
		userDAO.save(user);
		return 1;
	}

	@Override
	public UploadDocuments downloadDocuments(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveContactUs(ContactUs contact) {
		contact.setCreated_at(new Timestamp(new Date().getTime()));
		userDAO.save(contact);
	}
	
	

}
