package com.softserve.service;

import java.util.Set;

import com.softserve.entity.Role;

public interface RoleService {

	public Role getRoleById(int id);

	public Role addRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Role role);

	public Set<Role> getAllRoles();
	
	public Role getRoleByName(String name);
}
