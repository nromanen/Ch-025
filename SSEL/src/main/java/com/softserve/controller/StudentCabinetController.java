<<<<<<< HEAD
package com.softserve.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.Block;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.service.BlockService;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;

@Controller
public class StudentCabinetController {
	@Autowired
	private StudentCabinetSevice studentCabinetService;
	@Autowired
	private CourseSchedulerDao courseService;
	@Autowired 
	private StudentGroupService studentGroupService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private BlockService blockService;
	
	@RequestMapping("/subscribe")
	public String performSubscribe(@RequestParam("subjectId") Integer subjectId, @RequestParam("op") Boolean operation) {
		if (operation) {
			return subscribe(subjectId);
		} else {
			return unsubscribe(subjectId);
		}
	}
	
	@RequestMapping("/student")
	public String printStudentCourses(@RequestParam(value="table", required = false) String table,Model model) {
		studentCabinetService.initSubscribedList(1);
		if (table == null || table.equals("future")) {
			model.addAttribute("table", generateHtmlTable(studentCabinetService.getFutureCourses()));
		} else if (table.equals("active")){
			model.addAttribute("table", generateHtmlTable(studentCabinetService.getActiveCourses(),1));
		} else {
			model.addAttribute("table", generateHtmlTable(studentCabinetService.getFinishedCourses(),1));
		}
		return "student";
	}

	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public String printModules(
			@RequestParam(value = "courseId", required = false) Integer courseId, Model model) {
		try {
		List<Topic> topics = topicService.getTopicsBySubjectId(courseId);
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		
		model.addAttribute("topicList", topics);
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		} catch(NullPointerException e) {
			return "redirect:student?table=active";
		}
		return "modules?courseId="+courseId;
	}

	/**
	 * Perform subscribing student on course
	 * @param subjectId subject identifier to subscribe
	 * @return URL page
	 */
	private String subscribe(int subjectId) {
		int userId = 1;
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		StudentGroup row = studentCabinetService.getStudentGroupByUserAndCourseId(userId, cs.getId());
		if (row == null) { //check if student hasn't subscribed
			if (cs.getStart().after(new Date())) { // check if course hasn't started
				int groupNumber;
				if (studentGroupService.getAllStudentGroups().size() == 0) { // if no one group in db
					groupNumber = 100;
				} else {
					groupNumber = studentGroupService.getGroupNumberByCourse(cs.getId()); 
					if (groupNumber == -1) { // if user is first subscriber, create new group
						groupNumber = studentGroupService.getNextGroupNumber();
					}
				}
				row = new StudentGroup();
				row.setCourseScheduler(cs);
				row.setGroupNumber(groupNumber);
				row.setProgress(0.0);
				row.setRating(0.0);
				row.setUser(userService.getUserById(userId));
				studentGroupService.addStudentGroup(row);
				return "redirect:course?subjectId="+subjectId;
			} else {//redirect user to error page
				return "redirect:subscribe_error";
			}
		} else {//redirect user to error page
			return "redirect:subscribe_error";
		}
	}
	/**
	 * Perform unsubscribing student from course
	 * @param subjectId subject identifier to unsubscribe
	 * @return URL page
	 */
	private String unsubscribe(int subjectId) {
		int userId = 1;
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		StudentGroup row = studentCabinetService.getStudentGroupByUserAndCourseId(userId, cs.getId());
		if (row != null) {
			studentGroupService.deleteStudentGroup(row);
			return "redirect:course?subjectId="+subjectId;
		} else {
			return "redirect:subscribe_error";
		}
	}
	
	/**
	 * Generate html table code for future courses
	 * @param courses future courses list
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses) {
		String table = "";
		table += "<thead><tr><td>Subject name</td><td>Start time</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			table += "<tr><td>"+item.getSubject().getName()+"</td><td>"+item.getStart()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
	/**
	 * Generate html table code for active courses
	 * @param courses list of active courses
	 * @param userId student id
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses,int userId) {
		String table = "";
		table += "<thead><tr><td>Subject name</td><td>Start time</td><td>End time</td><td>Rating</td>"
				+ "<td>Progress</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			StudentGroup group = studentCabinetService.getStudentGroupByUserAndCourseId(userId, item.getId());
			table += "<tr><td><a href=\"modules?courseId="+item.getSubject().getId()+"\">"+item.getSubject().getName()+"</a>"
					+ "</td><td>"+item.getStart()+"</td>"
					+ "<td>"+item.getEnd()+"</td>"+"<td>"+group.getRating()+"</td><td>"+group.getProgress()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
}
=======
package com.softserve.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.UserService;

@Controller
public class StudentCabinetController {
	@Autowired
	private StudentCabinetSevice studCabinetService;
	@Autowired
	private CourseSchedulerDao courseService;
	@Autowired 
	private StudentGroupService studGroupService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/subscribe")
	public String performSubscribe(@RequestParam("subjectId") Integer subjectId, @RequestParam("op") Boolean operation) {
		if (operation) {
			return subscribe(subjectId);
		} else {
			return unsubscribe(subjectId);
		}
	}
	/**
	 * Perform subscribing student on course
	 * @param subjectId subject identifier to subscribe
	 * @return URL page
	 */
	private String subscribe(int subjectId) {
		int userId = 1;
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		StudentGroup row = studCabinetService.getStudentGroupByUserAndCourseId(userId, cs.getId());
		if (row == null) { //check if student hasn't subscribed
			if (cs.getStart().after(new Date())) { // check if course hasn't started
				int groupNumber = studGroupService.getGroupNumberByCourse(cs.getId());
				if (groupNumber == -1) { // if user is first subscriber, create new group
					groupNumber = studGroupService.getNextGroupNumber();
				}
				row = new StudentGroup();
				row.setCourseScheduler(cs);
				row.setGroupNumber(groupNumber);
				row.setProgress(0.0);
				row.setRating(0.0);
				row.setUser(userService.getUserById(userId));
				studGroupService.addStudentGroup(row);
				return "redirect:student?table=future";
			} else {//redirect user to error page
				return "redirect:subscribe_error";
			}
		} else {//redirect user to error page
			return "redirect:subscribe_error";
		}
	}
	/**
	 * Perform unsubscribing student from course
	 * @param subjectId subject identifier to unsubscribe
	 * @return URL page
	 */
	private String unsubscribe(int subjectId) {
		int userId = 1;
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		StudentGroup row = studCabinetService.getStudentGroupByUserAndCourseId(userId, cs.getId());
		if (row != null) {
			studGroupService.deleteStudentGroup(row);
			return "redirect:student?table=active";
		} else {
			return "redirect:subscribe_error";
		}
	}
	
	@RequestMapping("/student")
	public String printStudentCourses(@RequestParam("table") String table,Model model) {
		studCabinetService.initSubscribedList(1);
		if (table == null || table.equals("future")) {
			model.addAttribute("table", generateHtmlTable(studCabinetService.getFutureCourses()));
		} else if (table.equals("active")){
			model.addAttribute("table", generateHtmlTable(studCabinetService.getActiveCourses(),1));
		} else {
			model.addAttribute("table", generateHtmlTable(studCabinetService.getFinishedCourses(),1));
		}
		return "student";
	}
	/**
	 * Generate html table code for future courses
	 * @param courses future courses list
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses) {
		String table = "";
		table += "<thead><tr><td>Subject name</td><td>Start time</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			table += "<tr><td>"+item.getSubject().getName()+"</td><td>"+item.getStart()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
	/**
	 * Generate html table code for active courses
	 * @param courses list of active courses
	 * @param userId student id
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses,int userId) {
		String table = "";
		table += "<thead><tr><td>Subject name</td><td>Start time</td><td>End time</td><td>Rating</td>"
				+ "<td>Progress</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			StudentGroup group = studCabinetService.getStudentGroupByUserAndCourseId(userId, item.getId());
			table += "<tr><td>"+item.getSubject().getName()+"</td><td>"+item.getStart()+"</td>"
					+ "<td>"+item.getEnd()+"</td>"+"<td>"+group.getRating()+"</td><td>"+group.getProgress()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
}
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
