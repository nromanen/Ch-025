package com.softserve.controller;

import java.util.Set;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.service.RoleService;
import com.softserve.service.UserService;
import com.softserve.entity.Category;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherController {

	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(
			@RequestParam(value = "courseId", required = false) Integer courseId, Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		List<Topic> topics = topicService.getTopicsBySubjectId(courseId);
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		model.addAttribute("topicList", topics);
		return "teacher";
	}

	@RequestMapping(value = "/editTopic", method = RequestMethod.GET)
	public String editTopic(
			@RequestParam(value = "topicId", required = false) Integer topicId, Model model) {
		Topic topic = topicService.getTopicById(topicId);
		model.addAttribute("topic", topic);
		return "editTopic";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "login";
	}

}
