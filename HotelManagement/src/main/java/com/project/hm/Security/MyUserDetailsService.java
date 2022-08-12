package com.project.hm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserRegistration user = userRepository.findByUsername(username);
        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new Userdetails(user);
    }
	

}
