package com.softserve.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Block;
import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.GroupService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;

@Controller
public class GuestController {

	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CourseSchedulerService cSchedulerService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private StudentCabinetSevice studCabService;
	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private GroupService groupService;
	
	private List<Subject> subjects;
	private List<Category> categories;
	private List<CourseScheduler> schedulers;

	@RequestMapping(value = "/enter")
	public String enter(Model model, Principal principal,
			HttpSession httpSession) {
		LOG.info("User login {}", principal.getName());
		User user = userService.getUserByEmail(principal.getName());
		httpSession.setAttribute("user", user);
		if (user.getRole().getRole().equals(User.Roles.TEACHER.toString())) {
			return "redirect:/teacher";
		} else if (user.getRole().getRole().equals(User.Roles.STUDENT.toString())) {
			return "redirect:/student";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.debug("Visit index page as guest");
		subjects = subjectService.getAllSubjects();
		categories = categoryService.getAllCategories();
		List<CourseScheduler> schedule = cSchedulerService.getAllCourseScheduleres();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		model.addAttribute("schedule", schedule);
		return "index";
	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer subjectId, Model model,
			HttpSession httpSession) {
		LOG.debug("Visit course page as guest");
		subjects = subjectService.getAllSubjects();
		categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		Subject subject = subjectService.getSubjectById(subjectId);
		schedulers = cSchedulerService
				.getCourseScheduleresBySubjectId(subject.getId());
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			StudentGroup row = studCabService.getStudentGroupByUserAndGroupId(user.getId(), 
					groupService.getGroupByScheduler(schedulers.get(0).getId()).getGroupId());
			boolean isSubscribe = row == null;
			model.addAttribute("isSubscribe", isSubscribe);
		}
		model.addAttribute("schedule", schedulers.get(0));
		model.addAttribute("subject", subject);
		return "course";
	}
	
	@RequestMapping(value = "/courseInformation", method = RequestMethod.GET)
	public String courseInformation(@RequestParam Integer subjectId, Model model, HttpSession httpSession) {
		LOG.debug("Visit courseInformation page as guest");
		subjects = subjectService.getAllSubjects();
		categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		Subject subject = subjectService.getSubjectById(subjectId);
		Category category = categoryService.getCategoryById(subject.getCategory().getId());
		List<Block> blocks = blockService.getBlocksBySubjectId(subject.getId());
		List<Topic> topics = topicService.getAllTopics();
		schedulers = 
				cSchedulerService.getCourseScheduleresBySubjectId(subject.getId());
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			StudentGroup row = studCabService.getStudentGroupByUserAndGroupId(user.getId(), 
					groupService.getGroupByScheduler(schedulers.get(0).getId()).getGroupId());
			boolean isSubscribe = row == null;
			model.addAttribute("isSubscribe", isSubscribe);
		}
		model.addAttribute("schedule", schedulers.get(0));
		model.addAttribute("subject", subject);
		model.addAttribute("category", category);
		model.addAttribute("blocks", blocks);
		model.addAttribute("topics", topics);
		return "courseInformation";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam String search, Model model, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		
		
		return "search";
	}

}
