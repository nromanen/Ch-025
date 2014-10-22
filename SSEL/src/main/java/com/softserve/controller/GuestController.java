package com.softserve.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Subject;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.SubjectService;

@Controller
public class GuestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CourseSchedulerService cSchedulerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);

		LOG.debug("Visit index page as guest");
		return "index";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer courseId, Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		Subject subject = subjectService.getSubjectById(courseId);
//		List<CourseScheduler> schedule = cSchedulerService.getCourseScheduleresBySubjectId(subject.getId());
//		model.addAttribute("schList", schedule);
		model.addAttribute("subject", subject);
		return "course";
	}
	
}
