package com.lti.entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "claims")
@SequenceGenerator(name = "claims_seq", initialValue = 1, allocationSize = 1)
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claims_seq")
	private int claim_id;
	private int insurance_id;
	private int policy_no;

	@Column(name = "insurance_company", length = 30)
	private String insurance_company;

	@Column(name = "full_name", length = 50)
	private String full_name;

	private int sum_insured;

	@Column(name = "loss_cause", length = 100)
	private String loss_cause;

	private Date loss_date;

	@Column(name = "status", length = 20)
	private String status;

	private Timestamp created_at;

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public int getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}

	public int getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}

	public String getInsurance_company() {
		return insurance_company;
	}

	public void setInsurance_company(String insurance_company) {
		this.insurance_company = insurance_company;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getSum_insured() {
		return sum_insured;
	}

	public void setSum_insured(int sum_insured) {
		this.sum_insured = sum_insured;
	}

	public String getLoss_cause() {
		return loss_cause;
	}

	public void setLoss_cause(String loss_cause) {
		this.loss_cause = loss_cause;
	}

	public Date getLoss_date() {
		return loss_date;
	}

	public void setLoss_date(Date loss_date) {
		this.loss_date = loss_date;
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
