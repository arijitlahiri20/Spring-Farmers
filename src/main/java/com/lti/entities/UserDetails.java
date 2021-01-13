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
@Table(name = "user_details")
@SequenceGenerator(name = "user_details_seq", initialValue = 1, allocationSize = 1)
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq")
	private int user_id;

	@Column(name = "full_name", length = 50)
	private String full_name;

	@Column(name = "contact_no", length = 20)
	private String contact_no;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "addr_1", length = 50)
	private String addr_1;

	@Column(name = "addr_2", length = 150)
	private String addr_2;

	@Column(name = "city", length = 30)
	private String city;

	@Column(name = "state", length = 30)
	private String state;

	private int pincode;
	private int land_area;

	@Column(name = "land_addr", length = 100)
	private String land_addr;

	private int land_pincode;

	@Column(name = "account_no", length = 15)
	private String account_no;

	@Column(name = "ifsc_code", length = 20)
	private String ifsc_code;

	@Column(name = "aadhar", length = 200)
	private String aadhar;

	@Column(name = "pan", length = 200)
	private String pan;

	@Column(name = "trader_license", length = 200)
	private String trader_license;

	@Column(name = "certificate", length = 200)
	private String certificate;

	@Column(name = "password", length = 30)
	private String password;

	@Column(name = "user_type", length = 10)
	private String user_type;

	@Column(name = "status", length = 20)
	private String status;

	private Timestamp created_at;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr_1() {
		return addr_1;
	}

	public void setAddr_1(String addr_1) {
		this.addr_1 = addr_1;
	}

	public String getAddr_2() {
		return addr_2;
	}

	public void setAddr_2(String addr_2) {
		this.addr_2 = addr_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getLand_area() {
		return land_area;
	}

	public void setLand_area(int land_area) {
		this.land_area = land_area;
	}

	public String getLand_addr() {
		return land_addr;
	}

	public void setLand_addr(String land_addr) {
		this.land_addr = land_addr;
	}

	public int getLand_pincode() {
		return land_pincode;
	}

	public void setLand_pincode(int land_pincode) {
		this.land_pincode = land_pincode;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTrader_license() {
		return trader_license;
	}

	public void setTrader_license(String trader_license) {
		this.trader_license = trader_license;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
