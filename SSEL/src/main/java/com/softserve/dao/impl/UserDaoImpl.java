package com.softserve.dao.impl;

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
	public void addUser(User user) {
		entityManager.merge(user);
		LOG.debug("Add user (user email = {})", user.getEmail());
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
	public void updateUser(User user) {
		entityManager.merge(user);
		LOG.debug("Update user(email = {})", user.getEmail());
	}

	@Override
	public User getUserById(int id) {
		LOG.debug("Get user with id = {}", id);
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByEmail(String email) {
		Query query = entityManager
				.createQuery("FROM User WHERE email= :email").setParameter(
						"email", email);
		try {
			User user = (User) query.getSingleResult();
			LOG.debug("Get user(email = {})", email);
			return user;
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
		LOG.debug("User(email = {}) {} exist", email,
				(result.size() > 0 ? "" : "does not"));
		return result.size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		LOG.debug("Get all users");
		return entityManager.createQuery("FROM User").getResultList();
	}

}