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
		int logsPerPage = 10;
		GregorianCalendar startDate = null;
		GregorianCalendar endDate;
		if (pageNumb == null || pageNumb < 0) {
			pageNumb = 0;
		}
		if (session.getAttribute("logsPerPage") != null) {
			logsPerPage = (int)session.getAttribute("logsPerPage");
		}
		if (session.getAttribute("startDate") != null) {
			startDate = (GregorianCalendar) session.getAttribute("startDate");
			endDate = (GregorianCalendar) session.getAttribute("endDate");
		} else {
			endDate = new GregorianCalendar();
			startDate = new GregorianCalendar();
			startDate.set(endDate.DATE, (endDate.get(endDate.DATE) - 1));
		}
		List<Log> logList = logService.getRageofLogs(startDate, endDate, logsPerPage, pageNumb);
		Long logsInQuery = logService.countLogsInQuery(startDate, endDate);
		int numberOfPages = logService.getNumberOfPages(logsInQuery, logsPerPage);
		Date startPeriod = startDate.getTime();
		Date endPeriod = endDate.getTime();
		
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("logsInQuery", logsInQuery);
		model.addAttribute("pageNumb", pageNumb);
		model.addAttribute("startPeriod", startPeriod);
		model.addAttribute("endPeriod", endPeriod);
		model.addAttribute("logs", logList);
		return "viewAllLogs";
	}

	@RequestMapping(value = "/viewLogsByDate", method = RequestMethod.GET)
	public String viewLogsByDate(Model model, HttpSession session,
			@RequestParam(value = "startDate", required = false) String startDateString,
			@RequestParam(value = "endDate", required = false) String endDateString,
			@RequestParam(value = "logsPerPage", required = false) Integer logsPerPage) {
		LOG.debug("Visit viewLogsByDate page");
		if (logsPerPage != null) {
			session.setAttribute("logsPerPage", logsPerPage);
		}
		GregorianCalendar startCalendar = logService.parseDate(startDateString);
		if (startCalendar == null) {
			return "redirect:/viewAllLogs";
		} else {
			GregorianCalendar endCalendar = logService.parseDate(endDateString);
			int defaultLogsPerPage = 10;
			session.setAttribute("logsPerPage", defaultLogsPerPage);
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
