package com.softserve.dao;

import java.util.List;
import com.softserve.entity.StudyDocument;

/**
 *
 * @author hash
 */
public interface StudyDocumentDao {
    /**
     * Return all files
     * @return list of StudyDocument
     */
    List<StudyDocument> list();
    /**
     * Return files for some topic
     * @param id unique topic identifier
     * @return list of StudyDocument
     */
    List<StudyDocument> listByTopicId(int id);
    /**
     * Return all files mark as deleted
     * @return list of StudyDocument
     */
    List<StudyDocument> deletedList();
    /**
     * Add file into files list 
     * @param sfile new file
     * @return added file
     */
    StudyDocument create(StudyDocument sfile);
    /**
     * Return file by id
     * @param id unique file identifier
     * @return file representation object
     */
    StudyDocument get(Long id);
    /**
     * Manage delete
     * @param id unique file identifier
     * @param deleted true - mark as deleted, false - restore
     */
    void setDeleted(Long id, boolean deleted);
    /**
     * Delete file from db
     * @param id unique file identifier
     */
    void delete(Long id);
    /**
     * Return attachments to inactive topics
     * @return list of files
     */
    List<StudyDocument> getDocumentsForInactiveTopics();
    
}
