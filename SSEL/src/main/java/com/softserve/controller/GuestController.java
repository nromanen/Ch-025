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
import com.softserve.entity.Topic;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;

@Controller
public class GuestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private SubjectService subjectSevice;
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
		Set<Subject> subject = subjectSevice.getAllSubjects();
		
		Topic topic = topicService.getTopicById(4);
		topicService.deleteTopic(topic);
		
		model.addAttribute("subjects", subject);
		LOG.debug("Visit index page as guest");
		return "index";
	}
	
}
