package com.project.hm.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.customexceptions.UserNotValidException;
import com.project.hm.entity.LoginRequest;
import com.project.hm.entity.UserRegistration;
import com.project.hm.jwt.JwtTokenString;
import com.project.hm.jwt.JwtUtil;
import com.project.hm.service.UserService;

import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class UserController {

	@Autowired
	JwtUtil jwtUtil;


	@Autowired
	AuthenticationManager authenticationManager;

	
	
	@Autowired
private UserService userService;
	
  
	@PostMapping("user/registration")
  public ResponseEntity<UserRegistration> saveUser(@Valid @RequestBody UserRegistration userRegistration)
  {
	  System.out.println("Save User>>>>>>>>>>>>>>>>>>>>>>");
  return new ResponseEntity(userService.addReg( userRegistration), HttpStatus.CREATED);

  }
  @GetMapping("/getAllUsers")
  public ResponseEntity<List<UserRegistration>> allUsers(){
		List<UserRegistration> allUsers= userService.findAll();
		return new ResponseEntity<List<UserRegistration>>(allUsers, HttpStatus.ACCEPTED);
	
  }
  @PostMapping("/login")
	public JwtTokenString generateToken(@RequestBody LoginRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new UserNotValidException("invalid username/password");
		}
		JwtTokenString jwtTokenString = new JwtTokenString();
		jwtTokenString.setToken(jwtUtil.generateToken(authRequest.getUsername()));
		return jwtTokenString;
	}
  @GetMapping("/hello")
  public String hello()
  {
	  return "Hello";
  }

	
}
