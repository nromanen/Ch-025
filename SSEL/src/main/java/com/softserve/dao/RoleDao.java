package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Role;

public interface RoleDao {

	public Role getRoleById(int id);

	public Role addRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Role role);

	public List<Role> getAllRoles();
	
	public Role getRoleByName(String name);
}
