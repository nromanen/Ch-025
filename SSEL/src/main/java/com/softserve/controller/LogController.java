package com.softserve.controller;

import java.util.Calendar;
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

	/**
	 * Operates with viewAllLogs page. Checks for required parameters in session
	 * and used it. And if some of them doesn't exists - takes default values.
	 * 
	 * @param pageNumb
	 *            - number of page for query.
	 */
	@RequestMapping(value = "/viewLogs", method = RequestMethod.GET)
	public String viewLogs(Model model, HttpSession session,
			@RequestParam(value = "pageNumb", required = false) Integer pageNumb) {
		LOG.debug("Visit viewLogs page");
		int logsPerPage = 10;
		GregorianCalendar startDate = null;
		GregorianCalendar endDate;

		// defines type of sorting
		String preOrderBy;
		if (session.getAttribute("preOrderBy") != null) {
			preOrderBy = (String) session.getAttribute("preOrderBy");
		} else {
			preOrderBy = "date-desc"; // default value
		}
		String orderBy = logService.createOrderByPart(preOrderBy);
		// end of defining sorting type

		if (pageNumb == null || pageNumb < 0) {
			pageNumb = 0;
		}
		if (session.getAttribute("logsPerPage") != null) {
			logsPerPage = (int) session.getAttribute("logsPerPage");
		}

		// operating with start and end dates for range query
		if (session.getAttribute("startDate") != null) {
			startDate = (GregorianCalendar) session.getAttribute("startDate");
			endDate = (GregorianCalendar) session.getAttribute("endDate");
		} else { // if date wasn't chosen yet,user will see logs for last 24 hours
			endDate = new GregorianCalendar();
			startDate = new GregorianCalendar();
			startDate.set(Calendar.DATE, (endDate.get(Calendar.DATE) - 1)); // *
			// * making "yesterday" for start date
		}
		// end of operating with dates

		List<Log> logList = logService.getRangeOfLogs(startDate, endDate,
				logsPerPage, pageNumb, orderBy);
		Long logsInQuery = logService.countLogsInQuery(startDate, endDate);
		int numberOfPages = logService.getNumberOfPages(logsInQuery,
				logsPerPage);
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

	/**
	 * Operates with form "Show logs from - to date". Analyzes whether
	 * dateStrings is correct and if they do - adds them into session. If
	 * endDate is incorrect or null makes the endDate - moment of making a
	 * query.
	 */
	@RequestMapping(value = "/getRangeOfDates", method = RequestMethod.GET)
	public String getRangeOfDates(
			Model model,
			HttpSession session,
			@RequestParam(value = "startDate", required = false) String startDateString,
			@RequestParam(value = "endDate", required = false) String endDateString) {
		LOG.debug("Select other range of dates");
		GregorianCalendar startCalendar = logService.parseDate(startDateString);
		if (startCalendar == null) {
			return "redirect:/viewLogs";
		} else {
			GregorianCalendar endCalendar = logService.parseDate(endDateString);
			session.setAttribute("logsPerPage", 10);
			if (endCalendar == null) {
				endCalendar = new GregorianCalendar(); // show logs "by now"
			}
			session.setAttribute("startDate", startCalendar);
			session.setAttribute("endDate", endCalendar);
			return "redirect:/viewLogs";
		}
	}

	/**
	 * Receives parameters and adds them into session.
	 */
	@RequestMapping(value = "/getParameters", method = RequestMethod.GET)
	public String getParameters(
			Model model,
			HttpSession session,
			@RequestParam(value = "orderBy", required = false) String preOrderBy,
			@RequestParam(value = "logsPerPage", required = false) Integer logsPerPage) {
		LOG.debug("Change proper parameter");
		if (preOrderBy != null) {
			session.setAttribute("preOrderBy", preOrderBy);
		}
		if (logsPerPage != null) {
			session.setAttribute("logsPerPage", logsPerPage);
		}
		return "redirect:/viewLogs";
	}

	/**
	 * Receives dateString for deleting old logs. And if it correct - deletes
	 * them.
	 */
	@RequestMapping(value = "/deleteOldLogs", method = RequestMethod.GET)
	public String deleteOldLogs(
			Model model,
			@RequestParam(value = "deleteDate", required = false) String dateString) {
		LOG.debug("Atempt to delete old logs");
		GregorianCalendar deleteDate = logService.parseDate(dateString);
		if (deleteDate == null) {
			return "redirect:/viewLogs";
		}
		logService.deleteLogsDueDate(deleteDate);
		return "redirect:/viewLogs";
	}

	/**
	 * Shows log details for logs, that has information in the exception field.
	 */
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
