package com.softserve.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.entity.Log;
import com.softserve.service.LogService;
import com.softserve.service.TeacherRequestService;

@Controller
public class LogController {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);

	private static final int DEFAULT_LOGS_PER_PAGE = 10;
	private static final String LOGS_PER_PAGE = "logsPerPage";
	private static final String REDIRECT_TO_VIEWLOGS = "redirect:/viewLogs";
	private static final String PRE_ORDER_BY = "preOrderBy";

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Resource(name = "LogService")
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

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
		int logsPerPage = DEFAULT_LOGS_PER_PAGE;

		if (pageNumb == null || pageNumb < 0) {
			pageNumb = 0;
		}
		if (session.getAttribute(LOGS_PER_PAGE) != null) {
			logsPerPage = (int) session.getAttribute(LOGS_PER_PAGE);
		}
		GregorianCalendar[] dateRange = getDates(session);
		GregorianCalendar startDate = dateRange[0];
		GregorianCalendar endDate = dateRange[1];
		String orderBy = getTypeOfSorting(session);

		List<Log> logList = logService.getRangeOfLogs(startDate, endDate,
				logsPerPage, pageNumb, orderBy);
		Long logsInQuery = logService.countLogsInQuery(startDate, endDate);
		int numberOfPages = logService.getNumberOfPages(logsInQuery,
				logsPerPage);
		Date startPeriod = startDate.getTime();
		Date endPeriod = endDate.getTime();
		int activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		model.addAttribute("numberOfPages", numberOfPages);
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
			HttpSession session,
			@RequestParam(value = "startDate", required = false) String startDateString,
			@RequestParam(value = "endDate", required = false) String endDateString) {
		LOG.debug("Select other range of dates");
		GregorianCalendar startCalendar = logService.parseDate(startDateString);
		if (startCalendar == null) {
			return REDIRECT_TO_VIEWLOGS;
		} else {
			GregorianCalendar endCalendar = logService.parseDate(endDateString);
			session.setAttribute(LOGS_PER_PAGE, DEFAULT_LOGS_PER_PAGE);
			if (endCalendar == null) {
				// show logs "by now"
				endCalendar = new GregorianCalendar();
			}
			session.setAttribute("startDate", startCalendar);
			session.setAttribute("endDate", endCalendar);
			return REDIRECT_TO_VIEWLOGS;
		}
	}

	/**
	 * Receives parameters and adds them into session.
	 */
	@RequestMapping(value = "/getParameters", method = RequestMethod.GET)
	public String getParameters(
			HttpSession session,
			@RequestParam(value = "orderBy", required = false) String preOrderBy,
			@RequestParam(value = LOGS_PER_PAGE, required = false) Integer logsPerPage) {
		LOG.debug("Change proper parameter");
		if (preOrderBy != null) {
			session.setAttribute(PRE_ORDER_BY, preOrderBy);
		}
		if (logsPerPage != null) {
			session.setAttribute(LOGS_PER_PAGE, logsPerPage);
		}
		return REDIRECT_TO_VIEWLOGS;
	}

	/**
	 * Receives dateString for deleting old logs. And if it correct - deletes
	 * them.
	 */
	@RequestMapping(value = "/deleteOldLogs", method = RequestMethod.GET)
	public String deleteOldLogs(
			@RequestParam(value = "deleteDate", required = false) String dateString) {
		LOG.debug("Atempt to delete old logs");
		GregorianCalendar deleteDate = logService.parseDate(dateString);
		if (deleteDate == null) {
			return REDIRECT_TO_VIEWLOGS;
		}
		logService.deleteLogsDueDate(deleteDate);
		return REDIRECT_TO_VIEWLOGS;
	}

	/**
	 * Shows log exception for logs, that has information in the exception field.
	 */
	@RequestMapping(value = "/getException", method = RequestMethod.POST)
	@ResponseBody
	public String getException(
			@RequestParam(value = "logId", required = false) Integer logId) {
		JSONObject jsonObject;
		Log log = logService.getLogById(logId);
		jsonObject = new JSONObject();
		jsonObject.put("exception", log.getException());
		return jsonObject.toString();
	}

	/**
	 * Operates with range of date in which logs should be shown. If this range
	 * is not in session, it takes last 24 hours.
	 */
	private GregorianCalendar[] getDates(HttpSession session) {
		GregorianCalendar startDate;
		GregorianCalendar endDate;
		if (session.getAttribute("startDate") != null) {
			startDate = (GregorianCalendar) session.getAttribute("startDate");
			endDate = (GregorianCalendar) session.getAttribute("endDate");
		} else {
			// if date wasn't chosen yet,user will see logs for last 24 hours
			endDate = new GregorianCalendar();
			startDate = new GregorianCalendar();
			startDate.set(Calendar.DATE, endDate.get(Calendar.DATE) - 1); // *
			// * making "yesterday" for start date
		}
		GregorianCalendar[] dateRange = { startDate, endDate };
		return dateRange;
	}
	
	/**
	 * Operates with type of sorting by parsing proper String from session, or
	 * taking default value.
	 */
	private String getTypeOfSorting(HttpSession session) {
		String preOrderBy;
		if (session.getAttribute(PRE_ORDER_BY) != null) {
			preOrderBy = (String) session.getAttribute(PRE_ORDER_BY);
		} else {
			preOrderBy = "date-desc"; // default value
		}
		return logService.createOrderByPart(preOrderBy);
	}


}
