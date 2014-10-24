<<<<<<< HEAD
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
=======
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
>>>>>>> cdb409cfb5a30fe58335a64c2c69471f7d040982
