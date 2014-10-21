<<<<<<< HEAD
package com.softserve.controller;

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
import com.softserve.entity.Subject;
<<<<<<< HEAD
import com.softserve.entity.Topic;
=======
import com.softserve.service.CategoryService;
>>>>>>> a05a2959b9ca0f375fc3566ece0ebc9276b2659a
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;

@Controller
public class GuestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
<<<<<<< HEAD
	private SubjectService subjectSevice;
	@Autowired
	private TopicService topicService;
=======
	private CategoryService categoryService;
	
	@Autowired
	private SubjectService subjectService;
>>>>>>> a05a2959b9ca0f375fc3566ece0ebc9276b2659a
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
		Set<Subject> subject = subjectSevice.getAllSubjects();
		
		Topic topic = topicService.getTopicById(4);
		topicService.deleteTopic(topic);
		
<<<<<<< HEAD
		model.addAttribute("subjects", subject);
=======
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
>>>>>>> a05a2959b9ca0f375fc3566ece0ebc9276b2659a
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
		model.addAttribute("subject", subject);
		return "course";
	}
	
}
=======
package com.softserve.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.entity.Subject;
import com.softserve.form.Registration;
import com.softserve.service.SubjectService;

@Controller
public class GuestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);
	
	@Autowired
	private SubjectService subjectSevice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("method get");
		model.addAttribute("registration", new Registration());
		return "registration";
	}
	
}
>>>>>>> 43adb0790b125c7b3893adecc6e6bf88b241421f
