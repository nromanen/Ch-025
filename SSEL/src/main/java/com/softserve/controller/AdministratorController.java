package com.softserve.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Log;
import com.softserve.entity.Subject;
import com.softserve.entity.User;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.LogService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.UserService;

@Controller
public class AdministratorController {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentGroupService studentGroupService;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseSchedulerService courceSchedulerService;

	@Autowired
	private CategoryService categoryService;

	@Resource(name = "LogService")
	private LogService logService;

	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator(Model model) {
		LOG.debug("Visit administrator page");
		List<Subject> subjects = subjectService.getAllSubjects();
		List<CourseScheduler> courceScheduler = courceSchedulerService
				.getAllCourseScheduleres();
		List<User> users = userService.getAllUsers();
		model.addAttribute("subjects", subjects.size());
		model.addAttribute("courceScheduler", courceScheduler.size());
		model.addAttribute("users", users.size());
		return "administrator";
	}

	@RequestMapping(value = "/viewAllCategories", method = RequestMethod.GET)
	public String viewAllCategories(
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		LOG.debug("Visit viewAllCategories page");
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		if (message != null) {
			model.addAttribute("message", message);
		}
		return "viewAllCategories";
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Model model) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			model.addAttribute("message", "You are delete category: "
					+ category.getName());
			categoryService.deleteCategory(category);
		}
		// will be delete?
		// Set<Category> categories = categoryService.getAllCategories();
		// List<Category> list = new ArrayList<Category>(categories);
		// Collections.sort(list,Collections.reverseOrder());
		// model.addAttribute("categories", categories);
		return "redirect:/viewAllCategories";
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategory(
			@RequestParam(value = "category", required = false) String category,
			Model model) {
		LOG.debug("Visit viewAllCategories page");
		if (category != "" && category != null) {
			Category newCategory = new Category();
			newCategory.setName(category);
			categoryService.addCategory(newCategory);
			// will be delete?
			// Set<Category> categories = categoryService.getAllCategories();
			// model.addAttribute("categories", categories);
			// model.addAttribute("message", "You are add category: " +
			// category);
		}
		return "redirect:/viewAllCategories";
	}

	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public String viewAllUsers(Model model) {
		LOG.debug("Visit viewAllUsers page");

		return "viewAllUsers";
	}

	@RequestMapping(value = "/viewAllSubjects", method = RequestMethod.GET)
	public String viewAllSubjects(
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		LOG.debug("Visit viewAllLogs page");
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Category> categories = categoryService.getAllCategories();
		if (message != null) {
			model.addAttribute("message", message);
		}
		model.addAttribute("categories", categories);
		model.addAttribute("subjects", subjects);
		return "viewAllSubjects";
	}

	@RequestMapping(value = "/changeSubjectCategory", method = RequestMethod.GET)
	public String changeSubjectCategory(
			@RequestParam(value = "subjectId", required = false) Integer subjectId,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Model model) {
		LOG.debug("Visit changeSubjectCategory page");
		if (subjectId != null && categoryId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			Category category = categoryService.getCategoryById(categoryId);
			model.addAttribute("message",
					"You are change <b>" + subject.getName()
							+ "</b> category ftom <b>"
							+ subject.getCategory().getName() + "</b> to <b>"
							+ category.getName() + "</b>");
			subject.setCategory(category);
			subjectService.updateSubject(subject);
		} else {
			model.addAttribute("message",
					"Can't change category, input parameters is invalid!");
		}

		return "redirect:/viewAllSubjects";
	}

	@RequestMapping(value = "/viewAllLogs", method = RequestMethod.GET)
	public String viewAllLogs(Model model, HttpServletRequest request) {
		LOG.debug("Visit viewAllLogs page");
		HttpSession session = request.getSession();
		List<Log> logList = logService.getAllLogs();
		session.setAttribute("logs", logList);
		return "viewAllLogs";
	}
}
