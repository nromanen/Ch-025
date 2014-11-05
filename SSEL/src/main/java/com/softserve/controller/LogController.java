package com.softserve.controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Log;
import com.softserve.service.LogService;

@Controller
public class LogController {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);
	
	@Resource(name = "LogService")
	private LogService logService;

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/viewAllLogs", method = RequestMethod.GET)
	public String viewAllLogs(Model model, HttpSession session,
			@RequestParam(value = "pageNumb", required = false) Integer pageNumb) {
		LOG.debug("Visit viewAllLogs page");
		List<Log> logList;
		Date startPeriod;
		Date endPeriod;
		if (pageNumb == null) {
			pageNumb = 0;
		}
		if (session.getAttribute("startDate") != null) {
			GregorianCalendar startDate = (GregorianCalendar) session
					.getAttribute("startDate");
			GregorianCalendar endDate = (GregorianCalendar) session
					.getAttribute("endDate");
			logList = logService.getRageofLogs(startDate, endDate, pageNumb);
			startPeriod = startDate.getTime();
			endPeriod = endDate.getTime();
		} else {
			GregorianCalendar startDate = new GregorianCalendar();
			startDate.set(startDate.DATE, (startDate.get(startDate.DATE) - 1));
			logList = logService.getLogsSinceDate(startDate, pageNumb);
			startPeriod = null;
			endPeriod = null;
		}
		model.addAttribute("pageNumb", pageNumb);
		model.addAttribute("startPeriod", startPeriod);
		model.addAttribute("endPeriod", endPeriod);
		model.addAttribute("logs", logList);
		return "viewAllLogs";
	}

	@RequestMapping(value = "/viewLogsByDate", method = RequestMethod.GET)
	public String viewLogsByDate(
			Model model,
			HttpSession session,
			@RequestParam(value = "startDate", required = false) String startDateString,
			@RequestParam(value = "endDate", required = false) String endDateString) {
		LOG.debug("Visit viewLogsByDate page");
		GregorianCalendar startCalendar = logService.parseDate(startDateString);
		if (startCalendar == null) {
			return "redirect:/viewAllLogs";
		} else {
			GregorianCalendar endCalendar = logService.parseDate(endDateString);
			if (endCalendar == null) {
				endCalendar = new GregorianCalendar();
			}
			session.setAttribute("startDate", startCalendar);
			session.setAttribute("endDate", endCalendar);
			return "redirect:/viewAllLogs";
		}
	}

	@RequestMapping(value = "/deleteOldLogs", method = RequestMethod.GET)
	public String deleteOldLogs(
			@RequestParam(value = "deleteDate", required = false) String dateString,
			Model model) {
		LOG.debug("Atempt to delete old logs");
		GregorianCalendar deleteDate = logService.parseDate(dateString);
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
