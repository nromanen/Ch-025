package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Role;

/**
 * Basic Data Access Object interface. Provides CRUD operations for {@link Role}
 * objects and others operations.
 * 
 * @author Roma Khomyshyn
 *
 */

public interface RoleDao {
	/**
	 * Return role record by role id
	 * 
	 * @param id
	 *            is a id of record in database
	 * @return role object if exists or null otherwise
	 */
	Role getRoleById(int id);

	/**
	 * Add new role into database
	 * 
	 * @param role
	 *            new role
	 * @return added role
	 */
	Role addRole(Role role);

	/**
	 * Update role information
	 * 
	 * @param role
	 *            updated into database
	 * @return updated role
	 */
	Role updateRole(Role role);

	/**
	 * Delete role record from database
	 * 
	 * @param role
	 *            record to delete
	 */
	void deleteRole(Role role);

	/**
	 * Return all roles from database
	 * 
	 * @return list of role object if exists or empty list
	 */
	List<Role> getAllRoles();

	/**
	 * Return role record by role name
	 * 
	 * @param name
	 *            of role record in database
	 * @return role object if exists or null otherwise
	 */
	Role getRoleByName(String name);
}
