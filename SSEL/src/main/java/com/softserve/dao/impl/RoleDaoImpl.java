package com.softserve.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.RoleDao;
import com.softserve.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(RoleDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Role getRoleById(int id) {
		LOG.debug("Get role with id = {}", id);
		return entityManager.find(Role.class, id);
	}

	@Override
	public Role addRole(Role role) {
		LOG.debug("Add role {}", role);
		entityManager.persist(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		LOG.debug("Update role = {}", role.getRole());
		entityManager.merge(role);
		return role;
	}

	@Override
	public void deleteRole(Role role) {
		Query query = entityManager
				.createQuery("DELETE FROM Role r WHERE r.id = :id");
		query.setParameter("id", role.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted role(id = {})", role.getId());
		} else {
			LOG.warn("Tried to delete role(id = {})", role.getId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Role> getAllRoles() {
		LOG.debug("Get all roles");
		Set<Role> roles = new HashSet<>();
		roles.addAll(entityManager.createQuery("FROM Role").getResultList());
		return roles;
	}

	@Override
	public Role getRoleByName(String name) {
		LOG.debug("Get role(name = {})", name);
		Query query = entityManager.createQuery("FROM Role WHERE role= :role")
				.setParameter("role", name);
		try {
			Role role = (Role) query.getSingleResult();
			return role;
		} catch (NoResultException exception) {
			LOG.error("Tried to get role(name = {})", name, exception);
			return null;
		}
	}

}
