package com.softserve.controller;

import java.util.List;
import java.util.Set;

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
import com.softserve.entity.Subject;
import com.softserve.entity.User;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
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

	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator(Model model) {
		LOG.debug("Visit administrator page");
		Set<Subject> subjects = subjectService.getAllSubjects();
		List<CourseScheduler> courceScheduler = courceSchedulerService.getAllCourseScheduleres();
		List<User> users = userService.getAllUsers();
		model.addAttribute("subjects", subjects.size());
		model.addAttribute("courceScheduler", courceScheduler.size());
		model.addAttribute("users", users.size());
		return "administrator";
	}
	
	@RequestMapping(value = "/viewAllCategories", method = RequestMethod.GET)
	public String viewAllCategories(Model model) {
		LOG.debug("Visit viewAllCategories page");
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		return "viewAllCategories";
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryId != null) {
		Category category = categoryService.getCategoryById(categoryId);
		categoryService.deleteCategory(category);
		}
		Set<Category> categories =categoryService.getAllCategories();
//		List<Category> list = new ArrayList<Category>(categories);
//		Collections.sort(list,Collections.reverseOrder());
		model.addAttribute("categories", categories);
		return "redirect:/viewAllCategories";
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String viewAllCategories(
			@RequestParam(value = "category", required = false) String category, Model model) {
		LOG.debug("Visit viewAllCategories page");
		if (category != "" && category != null ) {
		Category newCategory = new Category();
		newCategory.setName(category);
		categoryService.addCategory(newCategory);
		Set<Category> categories =categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		}
		return "redirect:/viewAllCategories";
	}
	
	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public String viewAllUsers(Model model) {
		LOG.debug("Visit viewAllUsers page");
		
		return "viewAllUsers";
	}
	
	@RequestMapping(value = "/viewAllLogs", method = RequestMethod.GET)
	public String viewAllLogs(Model model) {
		LOG.debug("Visit viewAllLogs page");
		
		return "viewAllLogs";
	}
}
