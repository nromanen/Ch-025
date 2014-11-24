package com.softserve.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SocialUserDetailsServiceImpl.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	@Transactional
	public SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException, DataAccessException {
		LOG.debug("Loading user by user id: {}", userId);

		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		LOG.debug("Found user details: {}", userDetails);
		return (SocialUserDetails) userDetails;
	}

}
