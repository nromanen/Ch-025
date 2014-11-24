package com.softserve.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.social.SimpleUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		com.softserve.entity.User user = userDao.getUserByEmail(login);
		if (user == null) {
			LOG.info("User {} not found", login);
			throw new UsernameNotFoundException(login);
		}

		SimpleUserDetails principal = SimpleUserDetails.getBuilder()
				.firstName(user.getFirstName()).lastName(user.getLastName())
				.username(user.getEmail()).id(user.getId())
				.password(user.getPassword()).role(user.getRole())
				.social(user.getSocial()).enabled(!user.isBlocked()).build();

		return principal;
	}
}
