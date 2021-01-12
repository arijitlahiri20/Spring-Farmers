package com.lti.dto;

import java.util.List;

import com.lti.entities.UserDetails;

public class UserApprovalListStatus extends Status {

	private List<UserDetails> userList;

	public List<UserDetails> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetails> userList) {
		this.userList = userList;
	}
	
	
}
