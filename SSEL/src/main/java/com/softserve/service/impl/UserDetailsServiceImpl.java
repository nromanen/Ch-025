package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;

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

		boolean enabled = !user.isBlocked();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().getRole()));
<<<<<<< HEAD
		
		System.out.println("User: " + user.getEmail() + " exists= " + userDao.isExist(login));

=======
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
		return new User(user.getEmail(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				roles);
	}

}
