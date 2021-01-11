package com.lti.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@GeneratedValue
	private int user_id;
	
	private String full_name;
	private String contact_no;
	private String email;
	private String addr_1;
	private String addr_2;
	private String city;
	private String state ;
	private int pincode;
	private int land_area;
	private String land_addr;
	private int land_pincode;
	private String account_no;
	private String ifsc_code;
	private String aadhar;
	private String pan;
	private String trader_license;
	private String certificate ;
	private String password;
	private UserType user_type;
	private String status;
	private Timestamp created_at;
	
	public static enum StatusType {
		PENDING, APPROVED;
	}
	public static enum UserType {
		FARMER, BIDDER, ADMIN;
	}

	
}
