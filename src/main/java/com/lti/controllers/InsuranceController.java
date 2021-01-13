package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.lti.services.InsuranceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
	
}
