package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadDocuments {

	private int user_id;
	private MultipartFile aadhar;
	private MultipartFile pan;
	private MultipartFile certificate;
	private MultipartFile trader_licence;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public MultipartFile getAadhar() {
		return aadhar;
	}

	public void setAadhar(MultipartFile aadhar) {
		this.aadhar = aadhar;
	}

	public MultipartFile getPan() {
		return pan;
	}

	public void setPan(MultipartFile pan) {
		this.pan = pan;
	}

	public MultipartFile getCertificate() {
		return certificate;
	}

	public void setCertificate(MultipartFile certificate) {
		this.certificate = certificate;
	}

	public MultipartFile getTrader_licence() {
		return trader_licence;
	}

	public void setTrader_licence(MultipartFile trader_licence) {
		this.trader_licence = trader_licence;
	}

	@Override
	public String toString() {
		return "UploadDocuments [user_id=" + user_id + ", aadhar=" + aadhar.getOriginalFilename() + ", pan=" + pan.getOriginalFilename() + ", certificate="
				+ certificate.getOriginalFilename() + ", trader_licence=" + trader_licence.getOriginalFilename() + "]";
	}

}
