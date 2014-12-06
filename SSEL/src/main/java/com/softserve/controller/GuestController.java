package com.softserve.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
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
import com.softserve.service.SearchService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;

@Controller
public class GuestController {

	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);
	private static final int START_PAGE = 1;
	private static final int PAGE_SIZE = 10;

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
	private BlockService blockService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private SearchService searchService;
	
	private List<Subject> subjects;
	private List<Category> categories;
	private List<CourseScheduler> schedulers;

	@RequestMapping(value = "/enter")
	public String enter(Model model, Principal principal,
			HttpSession httpSession) {
		LOG.info("User login {}", principal.getName());
		User user = userService.getUserByEmail(principal.getName());
		httpSession.setAttribute("user", user);
		if (user.getImage() != null) {
			String encodedImage = new String(Base64.encode(user.getImage()));
			httpSession.setAttribute("image", encodedImage);
		}
		if (user.getRole().getName().equals(User.Roles.TEACHER.toString())) {
			return "redirect:/teacher";
		} else if (user.getRole().getName().equals(User.Roles.STUDENT.toString())) {
			return "redirect:/student";
		} else if (user.getRole().getName().equals(User.Roles.ADMIN.toString())){
			return "redirect:/administrator";
		} 
		else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.debug("Visit index page as guest");
		subjects = subjectService.getAllSubjectsWithSchedulers();
		categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		return "index";
	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer subjectId, 
			@RequestParam(value = "isSubscribed", required = false) Boolean isSubscribed, 
			Model model, HttpSession httpSession) {
		LOG.debug("Visit course page as guest");
		subjects = subjectService.getAllSubjects();
		categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		model.addAttribute("isSubscribed", isSubscribed);
		Subject subject = subjectService.getSubjectByIdWithScheduler(subjectId);
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			StudentGroup row = studentGroupService.getStudentGroupByUserAndGroupId(user.getId(), 
					groupService.getGroupByScheduler(subject.getSchedulers().get(0).getId()).getGroupId());
			boolean isSubscribe = row == null;
			model.addAttribute("isSubscribe", isSubscribe);
		}
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
			StudentGroup row = studentGroupService.getStudentGroupByUserAndGroupId(user.getId(), 
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
	public String search(@RequestParam String search, Model model, 
			@RequestParam (value = "pageNumber", required = false, defaultValue = START_PAGE + "") Integer pageNumber,
			@RequestParam (value = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize,
			@RequestParam (value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam (value = "isReverse", required = false, defaultValue = "false") Boolean isReverse) {
		LOG.debug("Search by subjects with {0} query", search);
		pageNumber = pageNumber > 0 ? pageNumber : START_PAGE;
		pageSize = pageSize > 0 ? pageSize : PAGE_SIZE;
		Long numberOfPages = 0l;
		List<Category> categories = searchService.getCategoriesByNamePart(search);
		List<Subject> subjects = searchService.getSubjectsByNamePart(search, pageNumber, 
				pageSize, sortBy, isReverse);
		Long count = searchService.getSubjectsQuantityByNamePart(search);
		numberOfPages = (count % pageSize > 0) ? count / pageSize + 1 : count / pageSize;
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("catList", categories);
		model.addAttribute("subjList", subjects);
		model.addAttribute("search", search);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("isReverse", isReverse);
		model.addAttribute("sortBy", sortBy);

		return "search";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@RequestParam Integer categoryId, Model model,
			@RequestParam (value = "pageNumber", required = false, defaultValue = START_PAGE + "") Integer pageNumber,
			@RequestParam (value = "pageSize", required = false, defaultValue = PAGE_SIZE + "") Integer pageSize,
			@RequestParam (value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam (value = "isReverse", required = false, defaultValue = "false") Boolean isReverse) {
		pageNumber = pageNumber > 0 ? pageNumber : START_PAGE;
		pageSize = pageSize > 0 ? pageSize : PAGE_SIZE;
		Long numberOfPages = 0l;
		List<Subject> subjects = 
				searchService.getSubjectsByCategoryIdWithLimit(categoryId, pageNumber, 
						pageSize, sortBy, isReverse);
		Category category = categoryService.getCategoryById(categoryId);
		LOG.debug("View all subjects in {0} category", category.getName());
		Long count = subjectService.getCountOfSubjectsByCategory(category.getName());
		numberOfPages = (count % pageSize > 0) ? count / pageSize + 1 : count / pageSize;
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("subjList", subjects);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("isReverse", isReverse);
		model.addAttribute("sortBy", sortBy);
		return "category";
	}
	
}
