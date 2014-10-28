package com.softserve.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Block;
import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;

@Controller
public class AdminController {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseSchedulerService courceSchedulerService;

	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.debug("Visit administrator page");
		Set<Subject> subjects = subjectService.getAllSubjects();
		List<CourseScheduler> courceScheduler = courceSchedulerService.getAllCourseScheduleres();
		List<User> users = userService.getAllUsers();
		model.addAttribute("subjects", subjects.size());
		model.addAttribute("courceScheduler", courceScheduler.size());
		model.addAttribute("users", users.size());
		return "administrator";
	}
}
