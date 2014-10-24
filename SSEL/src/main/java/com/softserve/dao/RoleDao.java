package com.softserve.dao;

import java.util.Set;

import com.softserve.entity.Role;

public interface RoleDao {

	public Role getRoleById(int id);

	public void addRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(Role role);

	public Set<Role> getAllRoles();
	
	public Role getRoleByName(String name);
}
