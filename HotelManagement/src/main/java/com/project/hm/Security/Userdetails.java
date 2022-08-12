package com.project.hm.Security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.hm.entity.Authorities;
import com.project.hm.entity.UserRegistration;


public class Userdetails implements UserDetails{
	
	private UserRegistration userRegistration;

	public Userdetails(UserRegistration user) {
		super();
		this.userRegistration=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
List<Authorities> list=    userRegistration.getAuthorities();

List<SimpleGrantedAuthority> simple= new ArrayList<SimpleGrantedAuthority>();

for(Authorities au:list)
{
	simple.add(new SimpleGrantedAuthority(au.getRole()));
}
return simple;

	}

	@Override
	public String getPassword() {
		
		return userRegistration.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userRegistration.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	

}
