package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadPh {

	private int sell_id;
	private int user_id;
	private MultipartFile ph_certificate;

	public int getSell_id() {
		return sell_id;
	}

	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public MultipartFile getPh_certificate() {
		return ph_certificate;
	}

	public void setPh_certificate(MultipartFile ph_certificate) {
		this.ph_certificate = ph_certificate;
	}

}
