package com.softserve.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.dao.StudyDocumentDao;
import com.softserve.dao.impl.BlockDaoImpl;
import com.softserve.entity.ConfigurationProperty;
import com.softserve.entity.StudyDocument;
import com.softserve.service.DeleteInactiveTopicsService;

@Service
public class DeleteInactiveTopicsServiceImpl implements DeleteInactiveTopicsService{
	private static final Logger LOG = LoggerFactory
			.getLogger(BlockDaoImpl.class);

	@Autowired
	private StudyDocumentDao studyDocumentDao;
	
	@Autowired
	private ConfigurationPropertiesDao configurationDao;
	
	public DeleteInactiveTopicsServiceImpl() {
	}
	/**
	 * @see com.softserve.service.DeleteInactiveTopicsService#deleteInactiveTopics()
	 */
	@Scheduled (cron = "10 * * * * *")
	@Override
	public void deleteInactiveTopics() {
		ConfigurationProperty cp = configurationDao.getPropertyByKey("attachmentsPath");
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
