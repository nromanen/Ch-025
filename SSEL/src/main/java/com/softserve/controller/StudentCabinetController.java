package com.softserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CourseSchedulerService;
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
	private CourseSchedulerService courseService;
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
	public String performSubscribe(@RequestParam("subjectId") Integer subjectId, @RequestParam("op") Boolean operation, 
			HttpSession sess) {
		User user = (User) sess.getAttribute("user");
		if (operation) {
			return subscribe(subjectId, user.getId());
		} else {
			return unsubscribe(subjectId, user.getId());
		}
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String printStudentCourses(@RequestParam(value="table", required = false) String table, Model model, HttpSession sess) {
		User user = (User) sess.getAttribute("user");
		int userId = user.getId();
		studentCabinetService.initSubscribedList(userId);
		List<CourseScheduler> scheduler;
		List<StudentGroup> groups;
		if (table == null || table.equals("future")) {
			scheduler = studentCabinetService.getFutureCourses();
			groups = new ArrayList<>();
			for (CourseScheduler item: scheduler) {
				groups.add(studentCabinetService.getStudentGroupByUserAndCourseId(userId,item.getId()));
			}
			model.addAttribute("courses",scheduler);
			model.addAttribute("groups", groups);
		} else if (table.equals("active")){
			scheduler = studentCabinetService.getActiveCourses(); 
			groups = new ArrayList<>();
			for (CourseScheduler item: scheduler) {
				groups.add(studentCabinetService.getStudentGroupByUserAndCourseId(userId,item.getId()));
			}
			model.addAttribute("courses",scheduler);
			model.addAttribute("groups", groups);
			
		} else {
			scheduler = studentCabinetService.getFinishedCourses();
			model.addAttribute("courses", scheduler);
		}
		model.addAttribute("table", table);
		return "student";
	}

	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public String printModules(
			@RequestParam(value = "courseId", required = true) Integer courseId, Model model, HttpSession session) {
		try {
		User user = (User) session.getAttribute("user");
		int userId;
		if (user == null) {
			userId = 1;
		} else {
			userId = user.getId();
		}
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		int courseSchedulerId = courseService.getCourseScheduleresBySubjectId(courseId).get(0).getId();
		StudentGroup result = studentCabinetService.getStudentGroupByUserAndCourseId(userId, courseSchedulerId);
		model.addAttribute("rating", result.getRating());
		model.addAttribute("progress", result.getProgress());
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		} catch(NullPointerException e) {
			return "redirect:student?table=active";
		}
		return "modules";
	}
	
	@RequestMapping(value="/topicView", method = RequestMethod.GET)
	public String printTopic(@RequestParam (value = "topicId", required = true) Integer topicId, Model model) {
		Topic topic = topicService.getTopicById(topicId);
		model.addAttribute("name", topic.getName());
		model.addAttribute("content", topic.getContent());
		return "topicView";
	}
	

	/**
	 * Perform subscribing student on course
	 * @param subjectId subject identifier to subscribe
	 * @return URL page
	 */
	private String subscribe(int subjectId, int userId) {
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
				//row.setId(1);
				row.setCourseScheduler(cs);
				row.setGroupNumber(groupNumber);
				row.setProgress(0.0);
				row.setRating(0.0);
				row.setUser(userService.getUserById(userId));
				studentGroupService.addStudentGroup(row);
			} 
		}
		return "redirect:course?subjectId="+subjectId;
	}
	/**
	 * Perform unsubscribing student from course
	 * @param subjectId subject identifier to unsubscribe
	 * @return URL page
	 */
	private String unsubscribe(int subjectId, int userId) {
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		StudentGroup row = studentCabinetService.getStudentGroupByUserAndCourseId(userId, cs.getId());
		if (row != null) {
			studentGroupService.deleteStudentGroup(row);
		} 
		return "redirect:course?subjectId="+subjectId;
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
