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

import com.softserve.entity.Block;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Group;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.GroupService;
import com.softserve.service.MailService;
import com.softserve.service.RatingService;
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
	private GroupService groupService;
	@Autowired 
	private RatingService ratingService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private BlockService blockService;
	@Autowired
	private MailService mailService;
	/**
	 * Handle subscribe requests
	 * @param subjectId subject id to subscribe/unsubscribe
	 * @param operation true - subscribe, false - unsubscribe
	 * @param sess session with user information in it
	 * @return view URL
	 */
	@RequestMapping("/subscribe")
	public String performSubscribe(@RequestParam("subjectId") Integer subjectId, @RequestParam("op") Boolean operation, 
			HttpSession sess) {
		User user = (User) sess.getAttribute("user");
		if (operation) {
			return subscribe(subjectId, user.getId(), user.getEmail());
		} else {
			return unsubscribe(subjectId, user.getId(), user.getEmail());
		}
	}
	/**
	 * Handle student cabinet requests
	 * @param table courses to show ( future, active, finished)
	 * @param model data model for view
	 * @param sess session with user information in it
	 * @return view URL
	 */
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String printStudentCourses(@RequestParam(value="table", required = false) String table, Model model, 
			HttpSession sess) {
		User user = (User) sess.getAttribute("user");
		int userId  = user.getId();
		studentCabinetService.initSubscribedList(userId);
		List<CourseScheduler> scheduler;
		List<Double> ratings;
		List<Double> progreses;
		if (table == null || table.equals("future")) { //build data model for future courses
			scheduler = studentCabinetService.getFutureCourses();
			model.addAttribute("courses",scheduler);
			model.addAttribute("title", "Future courses");
		} else if (table.equals("active")) { //build data model for active courses
			scheduler = studentCabinetService.getActiveCourses(); 
			ratings = new ArrayList<>();
			progreses = new ArrayList<>();
			for (CourseScheduler item: scheduler) {
				int groupId = groupService.getGroupByScheduler(item.getId()).getGroupId();
				ratings.add(ratingService.getAverageRatingByUserAndGroup(userId, groupId));
				progreses.add(ratingService.getProgressByGroupAndUser(groupId, userId));
			}
			model.addAttribute("title", "Active courses");
			model.addAttribute("courses",scheduler);
			model.addAttribute("ratings", ratings);
			model.addAttribute("progreses", progreses);
			
		} else { //build data model for finished courses
			scheduler = studentCabinetService.getFinishedCourses();
			model.addAttribute("courses", scheduler);
			model.addAttribute("title", "Finished courses");
		}
			model.addAttribute("table", (table == null) ? "future" : table);
		return "student";
	}
	/**
	 * Handle modules request
	 * @param courseId course identifier to show information about
	 * @param model data model for view
	 * @param session session with user information in it
	 * @return view URL
	 */
	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public String printModules(
			@RequestParam(value = "courseId", required = true) Integer courseId, Model model, HttpSession session) {
		try {
		User user = (User) session.getAttribute("user");
		int userId;
		userId = user.getId();
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		int courseSchedulerId = courseService.getCourseScheduleresBySubjectId(courseId).get(0).getId();
		int groupId = groupService.getGroupByScheduler(courseSchedulerId).getGroupId();
		model.addAttribute("rating", ratingService.getAverageRatingByUserAndGroup(userId, groupId));
		model.addAttribute("progress", ratingService.getProgressByGroupAndUser(groupId, userId));
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		} catch(NullPointerException e) {
			return "redirect:student?table=active";
		}
		return "modules";
	}
	/**
	 * Handle topic request and prepare topic
	 * @param topicId topic to show
	 * @param model data model for view page
	 * @return view URL
	 */
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
	private synchronized String subscribe(int subjectId, int userId, String email) {
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		Group subscribedGroup = groupService.getGroupByScheduler(cs.getId());
		int groupId = subscribedGroup.getGroupId();
		StudentGroup row = studentCabinetService.getStudentGroupByUserAndGroupId(userId, groupId);
		if (row == null) { //check if student hasn't subscribed
			if (cs.getStart().after(new Date()) && subscribedGroup.isActive()) { // check if course hasn't started
				row = new StudentGroup();
				row.setGroupNumber(subscribedGroup);
				row.setUser(userService.getUserById(userId));
				studentGroupService.addStudentGroup(row);
				mailService.sendMail(email, "ssel subscribe", "You've subscribed on course "+cs.getSubject().getName()+
						".Course started: "+cs.getStart()+"Good luck");
			} 
		}
		return "redirect:course?subjectId="+subjectId;
	}
	/**
	 * Perform unsubscribing student from course
	 * @param subjectId subject identifier to unsubscribe
	 * @return URL page
	 */
	private String unsubscribe(int subjectId, int userId, String email) {
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(subjectId).get(0); //get subject course scheduler
		int groupId = groupService.getGroupByScheduler(cs.getId()).getGroupId();
		StudentGroup row = studentCabinetService.getStudentGroupByUserAndGroupId(userId, groupId);
		if (row != null) {
			studentGroupService.deleteStudentGroup(row);
			mailService.sendMail(email, "ssel unsubscribe", "You've unsubscribed from course "+cs.getSubject().getName()+
					".You cannot subscribe on this course till it finished and start again."+"Good luck");
		} 
		return "redirect:course?subjectId="+subjectId;
	}
	
}
