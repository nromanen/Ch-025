package com.softserve.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.ConfigurationProperty;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Group;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.StudyDocument;
import com.softserve.entity.User;
import com.softserve.service.GroupService;
import com.softserve.service.MailService;
import com.softserve.service.StudentCabinetService;
import com.softserve.service.StudentGroupService;
import com.softserve.util.DeleteInactiveTopicsScheduler;

@Service
public class StudentCabinetServiceImpl implements StudentCabinetService{
	private static final Logger LOG = LoggerFactory.getLogger(StudentCabinetService.class);
	@Autowired
	private GroupService groupService;
	@Autowired
	private StudentGroupService studentGroupService;
	@Autowired
	private MailService mailService;
	@Autowired
	private StudyDocumentDao studyDocumentDao;
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	private ConfigurationPropertiesDao configDao;
	
	public StudentCabinetServiceImpl() {

	}
	
	/**
	 * @see com.softserve.service.StudentCabinetService#updateTopicFilesOnServer(String, int)
	 */
	@Override
	public List<StudyDocument> updateTopicFilesOnServer(String directoryName,int topicId) {
		String dirname =directoryName + "\\tmp\\";
		File tmpDir = new File(dirname);
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
		List<StudyDocument> documents = studyDocumentDao.listByTopicId(topicId);
		for (StudyDocument doc : documents) {
			File file = new File(dirname+doc.getName());
			System.out.println(dirname+""+file.getName());
			if(!file.exists()) {
				try(FileOutputStream fout = new FileOutputStream(dirname+doc.getName());){
					fout.write(doc.getData());
				} catch (IOException e) {
					LOG.error("Cannot write topic temp files");
				}
			}
		}
		return documents;		
	}
	/**
	 * @see com.softserve.service.StudentCabinetService#subscribe(CourseScheduler, User, boolean)
	 */
	@Override
	public void subscribe(CourseScheduler course, User user, boolean operation) {
		if (operation) {
			subscribeOnCourse(course, user);
		} else {
			unsubscribeFromCourse(course, user);
		}
	}
	/**
	 * Perform subscribe student on course
	 * @param course course to subscribe
	 * @param user user which is subscribe
	 */
	private void subscribeOnCourse(CourseScheduler course, User user) {
		Group subscribedGroup = groupService.getGroupByScheduler(course.getId());
		int groupId = subscribedGroup.getGroupId();
		StudentGroup row = studentGroupService.getStudentGroupByUserAndGroupId(user.getId(), groupId);
		if (row == null) { //check if student hasn't subscribed
			if (course.getStart().after(new Date()) && subscribedGroup.isActive()) { // check if course hasn't started
				row = new StudentGroup();
				row.setGroupNumber(subscribedGroup);
				row.setUser(user);
				studentGroupService.addStudentGroup(row);
				mailService.sendMail(user.getEmail(), "ssel subscribe", "You've subscribed on course "+
						course.getSubject().getName()+
						".Course started: "+course.getStart()+"Good luck");
				LOG.debug("User {} subscribe on course {}", user.getEmail(), course.getSubject().getName());
			} 
		}
		LOG.debug("User has already subscribed on this course");
	}
	/**
	 * Perform unsubscribe from course
	 * @param course course to unsubscribe
	 * @param user user which is unsubscribe
	 */
	private void unsubscribeFromCourse(CourseScheduler course, User user) {
		int groupId = groupService.getGroupByScheduler(course.getId()).getGroupId();
		StudentGroup row = studentGroupService.getStudentGroupByUserAndGroupId(user.getId(), groupId);
		if (row != null) {
			studentGroupService.deleteStudentGroup(row);
			mailService.sendMail(user.getEmail(), "ssel unsubscribe", "You've unsubscribed from course "+
			course.getSubject().getName()+
					".You cannot subscribe on this course till it finished and start again.Good luck");
			LOG.debug("User {} has unsubscribed from course {}", user.getEmail(), course.getSubject().getName());
		}
		LOG.debug("User {} try to unsubscribe from course {} which isn't subscribed!", user.getEmail(),
				course.getSubject().getName());
	}
	
	@Override
	public void rescheduleDeleteInactive() {
		
		ConfigurationProperty cronPatern = configDao.getPropertyByKey("schedule.delete");
		cronPatern.setValue("* 10 * * * *");
		configDao.updateProperty(cronPatern);
		CronTrigger newCron = new CronTrigger(cronPatern.getValue());
		taskScheduler.schedule(DeleteInactiveTopicsScheduler.getInstance(studyDocumentDao, configDao), newCron);
	}
}
