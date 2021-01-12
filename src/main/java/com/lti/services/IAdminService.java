package com.lti.services;

import java.util.List;

import com.lti.entities.UserDetails;

public interface IAdminService {

	public List<UserDetails> getUserPendingList();
}
