package com.project.hm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.project.hm.entity.UserRegistration;
import com.project.hm.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminregservice;
	
	
	@PostMapping("admin/register")
	public ResponseEntity<UserRegistration> addAccount(@RequestBody @Valid UserRegistration registrationdto) {

		//AdminRegistration reg = adminregservice.addReg(registrationdto);

		return new ResponseEntity(adminregservice.addReg( registrationdto), HttpStatus.CREATED);

	}
	
	
}
