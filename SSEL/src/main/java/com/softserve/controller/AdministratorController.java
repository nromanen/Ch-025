package com.softserve.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Log;
import com.softserve.entity.Subject;
import com.softserve.entity.User;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.LogService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.UserService;

@Controller
public class AdministratorController {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseSchedulerService courceSchedulerService;
	
	@Resource(name = "LogService")
	private LogService logService;

	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator(Model model) {
		LOG.debug("Visit administrator page");
		List<Subject> subjects = subjectService.getAllSubjects();
		List<CourseScheduler> courceScheduler = courceSchedulerService.getAllCourseScheduleres();
		List<User> users = userService.getAllUsers();
		model.addAttribute("subjects", subjects.size());
		model.addAttribute("courceScheduler", courceScheduler.size());
		model.addAttribute("users", users.size());
		return "administrator";
	}
	
	@RequestMapping(value = "/viewAllCategories", method = RequestMethod.GET)
	public String viewAllCategories(Model model) {
		LOG.debug("Visit viewAllCategories page");
		
		return "viewAllCategories";
	}
	
	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public String viewAllUsers(Model model) {
		LOG.debug("Visit viewAllUsers page");
		
		return "viewAllUsers";
	}
	
	@RequestMapping(value = "/viewAllLogs", method = RequestMethod.GET)
	public String viewAllLogs(Model model, HttpServletRequest request) {
		LOG.debug("Visit viewAllLogs page");
		HttpSession session = request.getSession();
		List<Log> logList = logService.getAllLogs();
		session.setAttribute("logs", logList);
		return "viewAllLogs";
	}

}
