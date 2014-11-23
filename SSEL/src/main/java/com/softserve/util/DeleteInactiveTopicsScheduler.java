package com.softserve.util;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.dao.StudyDocumentDao;
import com.softserve.dao.impl.BlockDaoImpl;
import com.softserve.entity.ConfigurationProperty;
import com.softserve.entity.StudyDocument;

public class DeleteInactiveTopicsScheduler implements Runnable{
	private static final Logger LOG = LoggerFactory
			.getLogger(BlockDaoImpl.class);

	private static volatile DeleteInactiveTopicsScheduler instance;
	
	private StudyDocumentDao studyDocumentDao;
	
	private ConfigurationPropertiesDao configurationDao;
	
	public DeleteInactiveTopicsScheduler(StudyDocumentDao studyDao, ConfigurationPropertiesDao configDao) {
		studyDocumentDao = studyDao;
		configurationDao = configDao;
	}
	/**
	 * Get singleton instance
	 * @return scheduler
	 */
	public static DeleteInactiveTopicsScheduler getInstance(StudyDocumentDao studyDao, ConfigurationPropertiesDao configDao) {
		if (instance == null) {
			synchronized (DeleteInactiveTopicsScheduler.class) {
				if (instance == null) {
					instance = new DeleteInactiveTopicsScheduler(studyDao, configDao);
				} 
			}
		} 
		return instance;
	}
	/**
	 * Delete inactive files
	 */
	@Override
	public void run() {
		ConfigurationProperty cp = configurationDao.getPropertyByKey("attachmentsPath");
		//cp.setValue("C:\\Tomcat7\\webapps\\SSEL-demo\\resources\\tmp");
		String filesDirectory = (cp != null) ? cp.getValue(): "";
		List<StudyDocument> inactiveAttachments = studyDocumentDao.getDocumentsForInactiveTopics();
		int deletedCount = 0;
		for (StudyDocument doc: inactiveAttachments) {
			File inactive = new File(filesDirectory+doc.getName());
			if(inactive.exists()) {
				inactive.delete();
				deletedCount++;
			}
		}
		LOG.debug("Remove temp files scheduler has finished {}. Files deleted: {}", new Date(), deletedCount);	
	}
	
}
