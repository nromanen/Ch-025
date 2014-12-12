package com.softserve.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Block;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Group;
import com.softserve.entity.Rating;
import com.softserve.entity.StudyDocument;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.GroupService;
import com.softserve.service.RatingService;
import com.softserve.service.StudentCabinetService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;
/**
 * Handle student requests
 * @author Anatoliy 
 *
 */
@Controller
public class StudentCabinetController {
	@Autowired
	private CourseSchedulerService courseService;
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
	private MessageSource messageSource; 
	@Autowired
	private StudentCabinetService studentCabinetService;
	
	/**
	 * Handle subscribe requests
	 * @param subjectId subject id to subscribe/unsubscribe
	 * @param operation true - subscribe, false - unsubscribe
	 * @return view URL
	 */
	@RequestMapping("/subscribe")
	public String performSubscribe(@RequestParam(value = "subjectId", required = true) Integer subjectId,
								   @RequestParam(value = "op", required = true)  Boolean operation) {
		User user = userService.getUserByEmail(userService.getCurrentUser()); //(User) sess.getAttribute("user");
		List<CourseScheduler> schedulers = courseService.getCourseScheduleresBySubjectId(subjectId);
		if (schedulers.size() !=0) {
			studentCabinetService.subscribe(schedulers.get(0), user, operation);
			return "redirect:course?subjectId="+subjectId+"&isSubscribed="+operation;
		} 
		return "redirect:course?subjectId="+subjectId;
	}
	/**
	 * Handle student cabinet requests
	 * @param table courses to show ( future, active, finished)
	 * @param model data model for view
	 * @param sess session with user information in it
	 * @return view URL
	 */
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String printStudentCourses(@RequestParam(value="table", required = false) String table, Model model) {
		User user = userService.getUserByEmail(userService.getCurrentUser());//(User) sess.getAttribute("user");
		int userId = user.getId();
		List<CourseScheduler> scheduler;
		List<Double> ratings;
		List<Double> progreses;
		if (table == null || table.equals("future")) { //build data model for future courses
			scheduler = courseService.getFutureSubscribedCoursesByUserId(userId);
			model.addAttribute("courses",scheduler);
			String title = messageSource.getMessage("label.future_courses", new Object[]{},
								LocaleContextHolder.getLocale());
			model.addAttribute("title", title);
			model.addAttribute("table", "future");
			return "student";
		} else if (table.equals("active")) { //build data model for active courses
			scheduler = courseService.getActiveSubscribedCoursesByUserId(userId);
			String title = messageSource.getMessage("label.active_courses", new Object[]{},
					LocaleContextHolder.getLocale());
			model.addAttribute("title", title);
			model.addAttribute("table", "active");
			
		} else { //build data model for finished courses
			scheduler = courseService.getFinishedSubscribedCoursesByUserId(userId);
			String title = messageSource.getMessage("label.finished_courses", new Object[]{},
					LocaleContextHolder.getLocale());
			model.addAttribute("title", title);
			model.addAttribute("table", "finished");
		}
		ratings = new ArrayList<>();
		progreses = new ArrayList<>();
		for (CourseScheduler item: scheduler) {
			int groupId = groupService.getGroupByScheduler(item.getId()).getGroupId();
			ratings.add(ratingService.getAverageRatingByUserAndGroup(userId, groupId));
			progreses.add(ratingService.getProgressByGroupAndUser(groupId, userId));
		}	
		model.addAttribute("courses",scheduler);
		model.addAttribute("ratings", ratings);
		model.addAttribute("progreses", progreses);
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
			@RequestParam(value = "courseId", required = true) Integer courseId, Model model) {
		User user = userService.getUserByEmail(userService.getCurrentUser());
		int userId = user.getId();
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		int courseSchedulerId = courseService.getCourseScheduleresBySubjectId(courseId).get(0).getId();
		int groupId = groupService.getGroupByScheduler(courseSchedulerId).getGroupId();
		Block nearest = blockService.getNearestInactiveBlockBySubject(subject.getId());
		model.addAttribute("rating", ratingService.getAverageRatingByUserAndGroup(userId, groupId));
		model.addAttribute("progress", ratingService.getProgressByGroupAndUser(groupId, userId));
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		model.addAttribute("table", "active");
		model.addAttribute("nearest", nearest);
		model.addAttribute("courseId", courseId);
		return "modules";
	}
	/**
	 * Handle topic request and prepare topic
	 * @param topicId topic to show
	 * @param model data model for view page
	 * @return view URL
	 */
	@RequestMapping(value="/topicView", method = RequestMethod.GET)
	public String printTopic(@RequestParam (value = "topicId", required = true) Integer topicId, Model model,
			HttpServletRequest request) {
		Topic topic = topicService.getTopicById(topicId);
		String dirname = request.getSession().getServletContext().getRealPath("resources");
		boolean isSupported = isSupportedBrowserForPlugin(request.getHeader("User-Agent"));
		List<StudyDocument> documents = studentCabinetService.updateTopicFilesOnServer(dirname, topic.getId());
		model.addAttribute("isSupported", isSupported);
		model.addAttribute("docs", documents);
		model.addAttribute("block_name", topic.getBlock().getName());
		model.addAttribute("topic order", topic.getOrder());
		model.addAttribute("name", topic.getName());
		model.addAttribute("content", topic.getContent());
		model.addAttribute("table", "active");
		model.addAttribute("path", dirname);
		return "topicView";
	}
	
	/**
	 * Handle ratings request and prepare it
	 * @param model data model for view
	 * @param session session object
	 * @return view URL
	 */
	@RequestMapping(value = "/ratings", method = RequestMethod.GET)
	private String printStatistics(@RequestParam (value = "courseId", required = true) Integer courseId,
								   @RequestParam (value = "showType", required = false) String showType,
								   Model model) {
		CourseScheduler cs = courseService.getCourseScheduleresBySubjectId(courseId).get(0);
		Group group = groupService.getGroupByScheduler(cs.getId());
		User user = userService.getUserByEmail(userService.getCurrentUser());//(User) session.getAttribute("user");
		double avgRating = ratingService.getAverageRatingByUserAndGroup(user.getId(), group.getGroupId());
		double progress = ratingService.getProgressByGroupAndUser(group.getGroupId(), user.getId());
		List<Rating> ratings = ratingService.getRatingByGroupAndUser(group.getGroupId(), user.getId());
		model.addAttribute("ratings", ratings);
		model.addAttribute("avgRating", avgRating);
		model.addAttribute("progress", progress);
		model.addAttribute("name", cs.getSubject().getName());
		model.addAttribute("startEnd", "(" + cs.getStart()+"-"+cs.getEnd()+ ")");
		model.addAttribute("showType", "table");
		model.addAttribute("table", "active");
		model.addAttribute("courseId", courseId);
		return "ratings";
	}
	@RequestMapping("/bla")
	void testScheduler() {
		studentCabinetService.rescheduleDeleteInactive();
	}
	/**
	 * Check if browser is supported for plugin
	 * @param userAgent http header with browser info
	 * @return true - for Opera, Chrome, Mozilla, false - IE
	 */
	private boolean isSupportedBrowserForPlugin(String userAgent) {
	    return !userAgent.contains("MSIE");
	}
	
}
