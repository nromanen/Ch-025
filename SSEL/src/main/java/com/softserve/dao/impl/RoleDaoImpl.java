package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.RoleDao;
import com.softserve.entity.Role;

/**
 * The Class RoleDaoImpl. This class implement interface {@link RoleDao}
 *
 * @author Khomyshyn Roman
 */
@Repository
public class RoleDaoImpl implements RoleDao {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory
			.getLogger(RoleDaoImpl.class);

	/** The entity manager. */
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	/**
	 * @see com.softserve.dao.RoleDao#getRoleById(int)
	 */
	@Override
	public Role getRoleById(int id) {
		LOG.debug("Get role with id = {}", id);
		return entityManager.find(Role.class, id);
	}

	/**
	 * @see com.softserve.dao.RoleDao#addRole(com.softserve.entity.Role)
	 */
	@Override
	public Role addRole(Role role) {
		LOG.debug("Add role {}", role);
		entityManager.persist(role);
		return role;
	}

	/**
	 * @see com.softserve.dao.RoleDao#updateRole(com.softserve.entity.Role)
	 */
	@Override
	public Role updateRole(Role role) {
		LOG.debug("Update role = {}", role.getName());
		entityManager.merge(role);
		return role;
	}

	/**
	 * @see com.softserve.dao.RoleDao#deleteRole(com.softserve.entity.Role)
	 */
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

	/**
	 * @see com.softserve.dao.RoleDao#getAllRoles()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		LOG.debug("Get all roles");
		List<Role> roles = new ArrayList<>();
		roles.addAll(entityManager.createQuery("FROM Role").getResultList());
		return roles;
	}

	/**
	 * @see com.softserve.dao.RoleDao#getRoleByName(java.lang.String)
	 */
	@Override
	public Role getRoleByName(String name) {
		LOG.debug("Get role(name = {})", name);
		Query query = entityManager.createQuery("FROM Role WHERE name= :name")
				.setParameter("name", name);
		try {
			Role role = (Role) query.getSingleResult();
			return role;
		} catch (NoResultException exception) {
			LOG.error("Tried to get role(name = {})", name, exception);
			return null;
		}
	}

}
