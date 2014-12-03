package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.UserDao;
import com.softserve.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public User addUser(User user) {
		LOG.debug("Add user (user email = {})", user.getEmail());
		entityManager.persist(user);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		Query query = entityManager
				.createQuery("DELETE FROM User u WHERE u.id = :id");
		query.setParameter("id", user.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted user (email = {})", user.getEmail());
		} else {
			LOG.warn("Tried to delete user (email = {})", user.getEmail());
		}
	}

	@Override
	public User updateUser(User user) {
		LOG.debug("Update user(email = {})", user.getEmail());
		entityManager.merge(user);
		return user;
	}

	@Override
	public User getUserById(int id) {
		LOG.debug("Get user with id = {}", id);
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByEmail(String email) {
		LOG.debug("Get user(email = {})", email);
		Query query = entityManager
				.createQuery("FROM User WHERE email= :email").setParameter(
						"email", email);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException exception) {
			LOG.error("Tried to get user(email = {})", email, exception);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isExist(String email) {
		List<User> result = entityManager
				.createQuery("FROM User WHERE email = :email")
				.setParameter("email", email).getResultList();
		LOG.debug("User(email = {}) {} exist", email, (!result.isEmpty() ? ""
				: "does not"));
		return !result.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		LOG.debug("Get all users");
		return entityManager.createQuery("FROM User").getResultList();
	}

	@Override
	public User getUserByKey(String key) {
		LOG.debug("Get user(key = {})", key);
		Query query = entityManager
				.createQuery("FROM User u WHERE u.verificationKey = :key");
		query.setParameter("key", key);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException exception) {
			LOG.error("Tried to get user(key = {})", key, exception);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByExpiredDate(Date date) {
		LOG.debug("Get users by expired date");
		List<User> users = new ArrayList<>();
		Query query = entityManager
				.createQuery("FROM User u WHERE u.expired <= :expired AND u.accountNonExpired = :accountNonExpired");
		query.setParameter("expired", date);
		query.setParameter("accountNonExpired", true);
		users.addAll(query.getResultList());
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByFirstNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all Users vs firstName = {}", searchText);
		String textQuery = "FROM User u WHERE u.firstName LIKE '%" + searchText
				+ "%'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength,
				sortBy, sortMethod);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByLastNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all Users vs limit last = {}", searchText);
		String textQuery = "FROM User u WHERE u.lastName LIKE '%" + searchText
				+ "%'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength,
				sortBy, sortMethod);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByRoleVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all Users vs limit searchText = {}", searchText);

		String textQuery = "FROM User u WHERE u.role.role LIKE '%" + searchText
				+ "%'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength,
				sortBy, sortMethod);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersVsLimit(int startPosition, int limitLength,
			String sortBy, String sortMethod) {
		LOG.debug("Get Users from - to = {} {}", startPosition, limitLength);
		String textQuery = "FROM User u";
		Query query = setQueryParameters(textQuery, startPosition, limitLength,
				sortBy, sortMethod);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all Users by searchText = {}", searchText);
		searchText = "%" + searchText + "%";
		String textQuery = "FROM User u WHERE u.id LIKE '" + searchText
				+ "' or u.email LIKE '" + searchText
				+ "' or u.firstName LIKE '" + searchText
				+ "' or u.lastName LIKE '" + searchText
				+ "' or u.role.role LIKE '" + searchText + "'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength,
				sortBy, sortMethod);
		return query.getResultList();
	}

	private Query setQueryParameters(String textQuery, int startPosition,
			int limitLength, String sortBy, String sortMethod) {
		Query query;
		if (sortBy != null && sortMethod != null) {
			switch (sortBy) {
			case "email":
				sortBy = "ORDER BY u.email";
				break;
			case "userName":
				sortBy = "ORDER BY u.firstName";
				break;
			case "userLastName":
				sortBy = "ORDER BY u.lastName";
				break;
			case "expired":
				sortBy = "ORDER BY u.expired";
				break;
			case "role":
				sortBy = "ORDER BY u.role.role";
				break;
			case "blocked":
				sortBy = "ORDER BY u.blocked";
				break;
			case "registration":
				sortBy = "ORDER BY u.registration";
				break;
			}

			if (sortMethod.equals("asc")) {
				sortMethod = "ASC";
			} else {
				sortMethod = "DESC";
			}
			sortBy += " " + sortMethod;
			query = entityManager.createQuery(textQuery + " " + sortBy);
		} else {
			query = entityManager.createQuery(textQuery);
		}

		query.setFirstResult(startPosition);
		query.setMaxResults(limitLength);

		return query;
	}

	@Override
	public long getUsersCount() {
		LOG.debug("Get all Users count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM User u ");
		return (Long) query.getSingleResult();
	}

	@Override
	public long getUsersByFirstNameCount(String searchName) {
		LOG.debug("Get Users by firstName count");
		Query query = entityManager.createQuery("SELECT COUNT (*) FROM User u "
				+ "WHERE u.firstName LIKE :name");
		query.setParameter("name", "%" + searchName + "%");
		return (Long) query.getSingleResult();
	}

	@Override
	public long getUsersByLastNameCount(String searchName) {
		LOG.debug("Get Users by lastName count");
		Query query = entityManager.createQuery("SELECT COUNT (*) FROM User u "
				+ "WHERE u.lastName LIKE :name");
		query.setParameter("name", "%" + searchName + "%");
		return (Long) query.getSingleResult();
	}

	@Override
	public long getUsersByRoleCount(String searchRole) {
		LOG.debug("Get Users by category count");
		Query query = entityManager.createQuery("SELECT COUNT (*) FROM User u "
				+ "WHERE u.role.role LIKE :name");
		query.setParameter("name", "%" + searchRole + "%");
		return (Long) query.getSingleResult();
	}

	@Override
	public long getUsersByTextCount(String searchText) {
		LOG.debug("Get Users count");
		searchText = "%" + searchText + "%";
		Query query = entityManager.createQuery("SELECT COUNT (*) FROM User u "
				+ "WHERE u.id LIKE '" + searchText + "' or u.email LIKE '"
				+ searchText + "' or u.firstName LIKE '" + searchText
				+ "' or u.lastName LIKE '" + searchText
				+ "' or u.role.role LIKE '" + searchText + "'");
		return (Long) query.getSingleResult();
	}

}
