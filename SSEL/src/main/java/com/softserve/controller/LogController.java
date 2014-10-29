package com.softserve.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.entity.Log;
import com.softserve.service.LogService;

@Controller
public class LogController {

	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private LogService logService;

	@RequestMapping(value = "/logs")
	public String logs(Model model) {
		LOG.debug("Viewing the log list");
		List<Log> logList = logService.getAllLogs();
		model.addAttribute(logList);
		return "logs";
	}

}
