package com.qsp.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qsp.repository.UserDetailsRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDetailsRepository userdetailsrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userdetailsrepo.findByUsername(username)
		.orElseThrow(()->new UsernameNotFoundException("User not present with "+username));
	}

}
