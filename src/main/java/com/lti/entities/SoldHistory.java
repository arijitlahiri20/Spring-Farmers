package com.lti.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sold_history")
public class SoldHistory {

	@Id
	private int sell_id;

	private int user_id;

	@Column(name = "crop_type", length = 30)
	private String crop_type;

	@Column(name = "crop_name", length = 30)
	private String crop_name;

	@Column(name = "fertilizer_type", length = 30)
	private String fertilizer_type;

	private int quantity;

	@Column(name = "ph_certificate", length = 50)
	private String ph_certificate;

	private int sold_price;

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

	public int getSold_price() {
		return sold_price;
	}

	public void setSold_price(int sold_price) {
		this.sold_price = sold_price;
	}

}
