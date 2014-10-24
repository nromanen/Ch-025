<<<<<<< HEAD
package com.softserve.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.RoleDao;
import com.softserve.entity.Role;
import com.softserve.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	@Override
	@Transactional
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	@Override
	@Transactional
	public Set<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	@Transactional
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

}
=======
package com.softserve.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.RoleDao;
import com.softserve.entity.Role;
import com.softserve.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	@Override
	@Transactional
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	@Override
	@Transactional
	public Set<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	@Transactional
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

}
>>>>>>> cdb409cfb5a30fe58335a64c2c69471f7d040982
