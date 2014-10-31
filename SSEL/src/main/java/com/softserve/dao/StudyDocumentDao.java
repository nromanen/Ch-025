package com.softserve.dao;

import java.util.List;
import com.softserve.entity.StudyDocument;

/**
 *
 * @author hash
 */
public interface StudyDocumentDao {
    
    public List<StudyDocument> list();
    
    public StudyDocument create(StudyDocument sfile);
    
    public StudyDocument get(Long id);
    
    public void delete(StudyDocument sfile);
}
