package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.StudyDocument;

/**
 *
 * @author hash
 */
@Repository
@Transactional
public class StudyDocumentDaoImpl implements StudyDocumentDao {

	private static final Logger log = LoggerFactory.getLogger(StudyDocumentDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	/**
	 * @see com.softserve.dao.StudyDocumentDao#list()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<StudyDocument> list() {
		log.debug("List of files");
		List<StudyDocument> studyDocumet = new ArrayList<StudyDocument>();
		studyDocumet.addAll(entityManager.createQuery("FROM StudyDocument sd WHERE sd.isDeleted = :val")
				.setParameter("val",false)
				.getResultList());
		return studyDocumet;
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#listByTopicId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StudyDocument> listByTopicId(int id) {
		log.debug("List of files");
		List<StudyDocument> studyDocumet = new ArrayList<StudyDocument>();
		Query query = entityManager.createQuery("FROM StudyDocument sd WHERE sd.topic.id = :id and sd.isDeleted = :val"
				+ " ORDER BY sd.id");
		query.setParameter("id", id);
		query.setParameter("val",false);
		studyDocumet.addAll(query.getResultList());
		return studyDocumet;
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#create(StudyDocument)
	 */
	@Override
	public StudyDocument create(StudyDocument stadyDocument) {
		log.debug("Creating file");
		entityManager.persist(stadyDocument);
		return stadyDocument;
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#get(Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public StudyDocument get(Long id) {
		log.debug("Getting file {}", id);
		return entityManager.find(StudyDocument.class, id);
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#setDeleted(Long, boolean)
	 */
	@Override
	public void setDeleted(Long id, boolean deleted) {
		Query query = entityManager.createQuery("UPDATE StudyDocument s SET s.isDeleted = :del WHERE s.id = :id");
		query.setParameter("id", id);
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			log.debug("Deleted subject(id = {})", id);
		} else {
			log.warn("Tried to delete subject(id = {})", id);
		}
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#deletedList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StudyDocument> deletedList() {
		log.debug("Get all deleted documents");
		return entityManager.createQuery("FROM StudyDocument s WHERE s.deleted = :val")
				.setParameter("val", true)
				.getResultList();
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#delete(Long)
	 */
	@Override
	public void delete(Long id) {
		Query query = entityManager.createQuery("DELETE FROM StudyDocument s WHERE s.id = :id");
		query.setParameter("id", id);
		if (query.executeUpdate() != 0) {
			log.debug("Deleted subject(id = {})", id);
		} else {
			log.warn("Tried to delete subject(id = {})", id);
		}
	}
	/**
	 * @see com.softserve.dao.StudyDocumentDao#getDocumentsForInactiveTopics()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StudyDocument> getDocumentsForInactiveTopics() {
		log.debug("Get all inactive files");
		return entityManager.createQuery("FROM StudyDocument sd WHERE sd.topic.alive = :val")
				.setParameter("val", true)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StudyDocument getDocumentByName(String name, long size) {
		log.debug("Get file");
		List<StudyDocument> studyDocumet = new ArrayList<StudyDocument>();
		Query query = entityManager.createQuery("FROM StudyDocument sd WHERE sd.name LIKE :name and sd.size = :size"
				+ " ORDER BY sd.id");
		query.setParameter("name", name);
		query.setParameter("size",size);
		studyDocumet.addAll(query.getResultList());
		return studyDocumet.isEmpty() ? null : studyDocumet.get(0);

	}

}
