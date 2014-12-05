package com.softserve.social;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.softserve.entity.Role;
import com.softserve.entity.User.Social;

public class SimpleUserDetails extends SocialUser {

	private int id;
	private String firstName;
	private String lastName;
	private Role role;
	private Social social;

	public SimpleUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, true, true,
				authorities);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Role getRole() {
		return role;
	}

	public Social getSocial() {
		return social;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "SimpleUserDetails [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", social="
				+ social + "]";
	}

	public static class Builder {
		private int id;
		private String username;
		private String lastName;
		private String firstName;
		private String password;
		private Role role;
		private Social social;
		private boolean enabled;
		private boolean accountNonExpired;
		private Set<GrantedAuthority> authorities;

		public Builder() {
			this.authorities = new HashSet<>();
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				this.password = "SocialUser";
			} else {
				this.password = password;
			}
			return this;
		}

		public Builder role(Role role) {
			this.role = role;
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
					role.getRole());
			this.authorities.add(authority);

			return this;
		}

		public Builder social(Social social) {
			this.social = social;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder accountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
			return this;
		}

		public SimpleUserDetails build() {
			SimpleUserDetails user = new SimpleUserDetails(username, password,
					enabled, accountNonExpired, authorities);

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.social = social;

			return user;
		}

	}

}
