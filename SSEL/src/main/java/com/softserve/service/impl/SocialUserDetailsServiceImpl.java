package com.softserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException, DataAccessException {
		return (SocialUserDetails) userDetailsService.loadUserByUsername(userId);
	}

}
