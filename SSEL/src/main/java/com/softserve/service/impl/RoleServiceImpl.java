package com.softserve.service.impl;

import java.util.List;

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
	public Role addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	@Transactional
	public Role updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	@Transactional
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

}
