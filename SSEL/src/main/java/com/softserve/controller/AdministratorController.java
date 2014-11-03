package com.softserve.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<CourseScheduler> courceScheduler = courceSchedulerService
				.getAllCourseScheduleres();
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

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/viewAllLogs", method = RequestMethod.GET)
	public String viewAllLogs(Model model) {
		LOG.debug("Visit viewAllLogs page");
		Date showDate = new Date();
		showDate.setDate((showDate.getDate() - 1));
		List<Log> logList = logService.getLogsSinceDate(showDate);
		String showPeriod = "last 24 hours";
		model.addAttribute("showPeriod", showPeriod);
		model.addAttribute("logs", logList);
		return "viewAllLogs";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/viewLogsByDate", method = RequestMethod.GET)
	public String viewLogsByDate(
			@RequestParam(value = "showDate", required = false) String dateString,
			Model model) {
		LOG.debug("Visit viewLogsByDate page");
		String showPeriod = "";
		Date showDate = logService.parseDate(dateString);
		if (showDate == null) {
			showDate = new Date();
			showDate.setDate((showDate.getDate() - 1));
			showPeriod = "last 24 hours";
		} else {
			showPeriod = showDate.toLocaleString();
		}
		List<Log> logList = logService.getLogsSinceDate(showDate);
		model.addAttribute("showPeriod", showPeriod);
		model.addAttribute("logs", logList);
		return "viewAllLogs";
	}

	@RequestMapping(value = "/deleteOldLogs", method = RequestMethod.GET)
	public String deleteOldLogs(
			@RequestParam(value = "deleteDate", required = false) String dateString,
			Model model) {
		LOG.debug("Atempt to delete old logs");
		Date deleteDate = logService.parseDate(dateString);
		if (deleteDate == null) {
			return "redirect:/viewAllLogs";
		}
		logService.deleteLogsDueDate(deleteDate);
		return "redirect:/viewAllLogs";
	}

	@RequestMapping(value = "/logDetails", method = RequestMethod.GET)
	public String logDetails(
			@RequestParam(value = "LogId", required = false) Integer logId,
			Model model) {
		LOG.debug("Visit logDetails page");
		Log log = logService.getLogById(logId);
		model.addAttribute("log", log);
		return "logDetails";

	}

}
