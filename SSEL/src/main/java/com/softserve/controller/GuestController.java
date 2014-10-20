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
import com.softserve.service.SubjectService;

@Controller
public class GuestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private SubjectService subjectSevice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
//		Subject subject1 = new Subject();
//		subject1.setName("Java");
//		Subject subject2 = new Subject();
//		subject2.setName("C++");
//		subjectSevice.addSubject(subject1);
//		subjectSevice.addSubject(subject2);
		Set<Subject> subjects = subjectSevice.getAllSubjects();
		model.addAttribute("subjects", subjects);
		LOG.debug("Visit index page as guest");
		return "index";
	}
	
}
