package com.softserve.service;

import java.util.List;

import com.softserve.entity.Role;

public interface RoleService {

	Role getRoleById(int id);

	Role addRole(Role role);

	Role updateRole(Role role);

	void deleteRole(Role role);

	List<Role> getAllRoles();

	Role getRoleByName(String name);
}
