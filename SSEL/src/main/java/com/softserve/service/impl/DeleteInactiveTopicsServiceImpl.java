package com.softserve.service.impl;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softserve.controller.StudentCabinetController;
import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.StudyDocument;
import com.softserve.service.DeleteInactiveTopicsService;

@Service
public class DeleteInactiveTopicsServiceImpl implements DeleteInactiveTopicsService{
	//private static final Logger LOG = LoggerFactory.getLogger(DeleteInactiveTopicsServiceImpl.class);
	@Autowired
	private StudyDocumentDao studyDocumentDao;

	private String filesDirectory = "C:\\Tomcat7\\webapp\\SSEL-demo\\resources\\tmp\\"; 
	
	public DeleteInactiveTopicsServiceImpl() {
	}
	/**
	 * @see com.softserve.service.DeleteInactiveTopicsService#deleteInactiveTopics()
	 */
	@Scheduled (cron = "* 2 * * * *")
	@Override
	public void deleteInactiveTopics() {
		List<StudyDocument> inactiveAttachments = studyDocumentDao.getDocumentsForInactiveTopics();
		int deletedCount = 0;
		for (StudyDocument doc: inactiveAttachments) {
			File inactive = new File(filesDirectory+doc.getName());
			if(inactive.exists()) {
				inactive.delete();
				deletedCount++;
			}
		}
		//LOG.debug("Delete inactive topics attachments scheduler has finished. Deleted files = {}", deletedCount);
	}
	
}
