package com.softserve.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.User;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.MailService;
import com.softserve.service.SchedulingService;
import com.softserve.service.StudentGroupService;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	private static final int DATE_TOMORROW = 1;

	@Autowired
	private CourseSchedulerService courseSchedulerService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private MailService mailService;

	// @Scheduled(cron = "0 30 16 * * *")

	@Override
	@Scheduled(cron = "*/5 * * * * *")
	public void courseBegin() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, DATE_TOMORROW);
		Date date = calendar.getTime();
		System.out.println("Date " + date.toString());
		List<CourseScheduler> courseSchedulers = courseSchedulerService
				.getCourseSchedulersByStartDate(date);
		for (CourseScheduler courseScheduler : courseSchedulers) {
			for (StudentGroup studentGroup : studentGroupService
					.getStudentGroupsByCourseSheduledId(courseScheduler.getId())) {
				User user = studentGroup.getUser();
				mailService.sendMail(user.getEmail(), "SSEL course start.",
						"Course " + courseScheduler.getSubject().getName()
								+ " begin " + date.toString());
			}
		}
	}

}
