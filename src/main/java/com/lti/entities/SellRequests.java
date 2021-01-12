package com.lti.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "sell_requests")
@SequenceGenerator(name="sell_requests_seq", initialValue=1 ,allocationSize=1)

public class SellRequests {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sell_requests_seq")
	private int sell_id;
	
	private int user_id;
	private String crop_type;
	private String crop_name;
	private String fertilizer_type;
	private int quantity;
	private String ph_certificate;
	private int msp;
	private String status;
	
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
	public String getCrop_type() {
		return crop_type;
	}
	public void setCrop_type(String crop_type) {
		this.crop_type = crop_type;
	}
	public String getCrop_name() {
		return crop_name;
	}
	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}
	public String getFertilizer_type() {
		return fertilizer_type;
	}
	public void setFertilizer_type(String fertilizer_type) {
		this.fertilizer_type = fertilizer_type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPh_certificate() {
		return ph_certificate;
	}
	public void setPh_certificate(String ph_certificate) {
		this.ph_certificate = ph_certificate;
	}
	public int getMsp() {
		return msp;
	}
	public void setMsp(int msp) {
		this.msp = msp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
