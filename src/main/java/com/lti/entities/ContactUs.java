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
@Table(name = "contact_us")
@SequenceGenerator(name = "contact_us_seq", initialValue = 1, allocationSize = 1)
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_us_seq")
	private int c_id;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "contact_no", length = 20)
	private String contact_no;

	@Column(name = "message", length = 200)
	private String message;

	private Timestamp created_at;

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

}
