package com.softserve.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.User;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.LogService;
import com.softserve.service.MailService;
import com.softserve.service.SchedulingService;
import com.softserve.service.StudentGroupService;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	private static final int BEFORE_COURSE_BEGIN = 1;

	private static final Logger LOG = LoggerFactory
			.getLogger(SchedulingServiceImpl.class);

	@Autowired
	private CourseSchedulerService courseSchedulerService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private MailService mailService;

	@Autowired
	private LogService logService;

	@Override
	@Scheduled(cron = "${cron.execute.course_begin}")
	public void courseBegin() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, BEFORE_COURSE_BEGIN);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		List<CourseScheduler> courseSchedulers = courseSchedulerService
				.getCourseSchedulersByStartDate(date);
		for (CourseScheduler courseScheduler : courseSchedulers) {
			String message = "Hello. Course "
					+ courseScheduler.getSubject().getName() + " begin at "
					+ format.format(date);
			for (StudentGroup studentGroup : studentGroupService
					.getStudentGroupsByCourseSheduledId(courseScheduler.getId())) {
				User user = studentGroup.getUser();
				mailService.sendMail(user.getEmail(), "SSEL Course start!",
						message);
			}
		}
	}

	@Override
	@Scheduled(cron = "0 0 4 * * 1")	// every Monday in 04:00 AM
	public void deleteOldLogs() {
		LOG.info("Deleting logs older than a year by SchedulingService");
		GregorianCalendar deleteDate = new GregorianCalendar();
		deleteDate.set(Calendar.YEAR, (deleteDate.get(Calendar.YEAR) - 1));
		logService.deleteLogsDueDate(deleteDate);
	}

}