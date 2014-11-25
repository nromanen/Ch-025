package com.softserve.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.softserve.entity.User;
import com.softserve.social.SimpleUserDetails;

public class SecurityUtil {
	public static void logInUser(User user) {
		SimpleUserDetails userDetails = SimpleUserDetails.getBuilder()
				.firstName(user.getFirstName()).lastName(user.getLastName())
				.id(user.getId()).role(user.getRole())
				.password(user.getPassword()).username(user.getEmail())
				.social(user.getSocial()).enabled(!user.isBlocked()).build();
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
