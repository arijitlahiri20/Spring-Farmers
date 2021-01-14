package com.lti.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "insurance")
@SequenceGenerator(name = "insurance_seq", initialValue = 1, allocationSize = 1)
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_seq")
	private int insurance_id;

	private int user_id;

	@Column(name = "season", length = 10)
	private String season;

	private int year;

	@Column(name = "crop", length = 20)
	private String crop;

	private int sum_insured;
	private int area;
	private int premium_amount;

	@Column(name = "insurance_company", length = 30)
	private String insurance_company;

	private int policy_no;

	@Column(name = "status", length = 20)
	private String status;

	private Timestamp created_at;

	public int getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public int getSum_insured() {
		return sum_insured;
	}

	public void setSum_insured(int sum_insured) {
		this.sum_insured = sum_insured;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPremium_amount() {
		return premium_amount;
	}

	public void setPremium_amount(int premium_amount) {
		this.premium_amount = premium_amount;
	}

	public String getInsurance_company() {
		return insurance_company;
	}

	public void setInsurance_company(String insurance_company) {
		this.insurance_company = insurance_company;
	}

	public int getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

}
