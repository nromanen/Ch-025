package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.StudyDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public List<StudyDocument> list() {
        log.debug("List of files");
        List<StudyDocument> studyDocumet = new ArrayList<StudyDocument>();
        studyDocumet.addAll(entityManager.createQuery("FROM StudyDocument").getResultList());
        return studyDocumet;
    }

    @Override
    public StudyDocument create(StudyDocument stadyDocument) {
        log.debug("Creating file");
		entityManager.persist(stadyDocument);
        return stadyDocument;
    }

    @Override
    @Transactional(readOnly = true)
    public StudyDocument get(Long id) {
        log.debug("Getting file {}", id);
        return entityManager.find(StudyDocument.class, id);
    }

    @Override
    public void delete(StudyDocument stadyDocument) {
    	Query query = entityManager.createQuery("DELETE FROM files s WHERE s.id = :id");
		query.setParameter("id", stadyDocument.getId());
		if (query.executeUpdate() != 0) {
			log.debug("Deleted subject(id = {})", stadyDocument.getId());
		} else {
			log.warn("Tried to delete subject(id = {})", stadyDocument.getId());
		}
    }
    
}
