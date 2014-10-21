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
