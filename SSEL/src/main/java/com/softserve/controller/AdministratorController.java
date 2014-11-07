package com.softserve.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Log;
import com.softserve.entity.Role;
import com.softserve.entity.Subject;
import com.softserve.entity.User;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.LogService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.UserService;

@Controller
public class AdministratorController {

	public static final int DEFAULT_ELEMENTS_ON_PAGE = 10;

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

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private RoleService roleService;

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
			@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model model) {
		LOG.debug("Visit viewAllCategories page");
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}
		return "viewAllCategories";
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			redirectAttributes.addFlashAttribute("successMessage",
					"You are delete category: <strong>" + category.getName()
							+ "</strong>");
			categoryService.deleteCategory(category);
		}
		return "redirect:/viewAllCategories";
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategory(
			@RequestParam(value = "category", required = false) String categoryName,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryName != "" && categoryName != null) {
			if (!administratorService.addCategory(categoryName)) {
				redirectAttributes.addFlashAttribute("successMessage",
						"You are add category: <strong>" + categoryName
								+ "</strong>");
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Category: <strong>" + categoryName
								+ "</strong> allready exist!");
			}
		}
		return "redirect:/viewAllCategories";
	}

	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public String viewAllUsers(
			@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "sortMethod", required = false) String sortMethod,
			Model model) {
		LOG.debug("Visit viewAllUsers page");
		List<User> users = new ArrayList<User>();
		int startPosition;
		int limitLength;
		long count;
		int pages;
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}

		if (elementsOnPage == null) {
			elementsOnPage = DEFAULT_ELEMENTS_ON_PAGE;
		}

		if (currentPage == null) {
			currentPage = 1;
		}

		startPosition = (currentPage - 1) * elementsOnPage;
		limitLength = elementsOnPage;

		count = userService.getUsersCount();
		pages = (int) count / elementsOnPage;

		if (searchText != null && !searchText.equals("")
				&& searchOption != null) {
			if (searchOption.equals("all")) {
				users = userService.getUsersByTextVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = userService.getUsersByTextCount(searchText);
			} else if (searchOption.equals("userFirstName")) {
				users = userService.getUsersByFirstNameVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = userService.getUsersByFirstNameCount(searchText);
			} else if (searchOption.equals("userLastName")) {
				users = userService.getUsersByLastNameVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = userService.getUsersByLastNameCount(searchText);
			} else if (searchOption.equals("role")) {
				users = userService.getUsersByRoleVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = userService.getUsersByRoleCount(searchText);
			}
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = userService.getUsersCount();

			users = userService.getUsersVsLimit(startPosition, limitLength,
					sortBy, sortMethod);
		}

		pages = (int) count / elementsOnPage;
		if ((count % elementsOnPage) != 0) {
			pages++;
		}

		if (sortBy != null && sortMethod != null) {
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("sortMethod", sortMethod);
		}

		List<Role> roles = roleService.getAllRoles();

		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		model.addAttribute("pagesCount", pages);
		model.addAttribute("elementsOnPage", elementsOnPage);
		model.addAttribute("currentPage", currentPage);
		return "viewAllUsers";
	}

	@RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
	public String changeUserRole(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "sortMethod", required = false) String sortMethod,

			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		System.out.println(userId + "  " + roleId);
		if (userId != null && roleId != null) {
			User user = userService.getUserById(userId);
			Role role = roleService.getRoleById(roleId);
			redirectAttributes.addAttribute("successMessage",
					"You are change <b>" + user.getEmail()
							+ "</b> role from <b>" + user.getRole().getRole()
							+ "</b> to <b>" + role.getRole() + "</b>");
			user.setRole(role);
			userService.updateUser(user);
		} else {
			redirectAttributes.addAttribute("errorMessage",
					"Can't change role, input parameters is invalid!");
		}
		redirectAttributes.addAttribute("currentPage", currentPage);
		redirectAttributes.addAttribute("elementsOnPage", elementsOnPage);
		redirectAttributes.addAttribute("searchText", searchText);
		redirectAttributes.addAttribute("searchOption", searchOption);
		redirectAttributes.addAttribute("sortBy", sortBy);
		redirectAttributes.addAttribute("sortMethod", sortMethod);
		return "redirect:/viewAllUsers";
	}

	@RequestMapping(value = "/changeUserStatus", method = RequestMethod.GET)
	public String changeUserStatus(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "sortMethod", required = false) String sortMethod,

			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		if (userId != null) {
			User user = userService.getUserById(userId);
			if (user.isBlocked()) {
				user.setBlocked(false);
				redirectAttributes
						.addAttribute("successMessage",
								"You are unblock user: <b>" + user.getEmail()
										+ "</b> ");
			} else {
				user.setBlocked(true);
				redirectAttributes.addAttribute("successMessage",
						"You are block user: <b>" + user.getEmail() + "</b> ");
			}
			userService.updateUser(user);
		} else {
			redirectAttributes.addAttribute("errorMessage",
					"Can't block user, input parameters is invalid!");
		}
		redirectAttributes.addAttribute("currentPage", currentPage);
		redirectAttributes.addAttribute("elementsOnPage", elementsOnPage);
		redirectAttributes.addAttribute("searchText", searchText);
		redirectAttributes.addAttribute("searchOption", searchOption);
		redirectAttributes.addAttribute("sortBy", sortBy);
		redirectAttributes.addAttribute("sortMethod", sortMethod);
		return "redirect:/viewAllUsers";
	}

	@RequestMapping(value = "/viewAllSubjects", method = RequestMethod.GET)
	public String viewAllSubjects(
			@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "sortMethod", required = false) String sortMethod,
			Model model) {
		LOG.debug("Visit viewAllSubjects page");
		List<Subject> subjects = new ArrayList<Subject>();
		int startPosition;
		int limitLength;
		long count;
		int pages;
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}

		if (elementsOnPage == null) {
			elementsOnPage = DEFAULT_ELEMENTS_ON_PAGE;
		}

		if (currentPage == null) {
			currentPage = 1;
		}

		startPosition = (currentPage - 1) * elementsOnPage;
		limitLength = elementsOnPage;

		count = subjectService.getSubjectsCount();
		pages = (int) count / elementsOnPage;

		if (searchText != null && !searchText.equals("")
				&& searchOption != null) {
			if (searchOption.equals("all")) {
				subjects = subjectService.getSubjectsByTextVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = subjectService.getSubjectsByTextCount(searchText);
			} else if (searchOption.equals("subject")) {
				subjects = subjectService.getSubjectsByNameVsLimit(searchText,
						startPosition, limitLength, sortBy, sortMethod);
				count = subjectService.getSubjectsByNameCount(searchText);
			} else if (searchOption.equals("category")) {
				subjects = subjectService.getSubjectsByCategoryVsLimit(
						searchText, startPosition, limitLength, sortBy,
						sortMethod);
				count = subjectService.getSubjectsByCategoryCount(searchText);
			}
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = subjectService.getSubjectsCount();

			subjects = subjectService.getSubjectsVsLimit(startPosition,
					limitLength, sortBy, sortMethod);
		}

		pages = (int) count / elementsOnPage;
		if ((count % elementsOnPage) != 0) {
			pages++;
		}

		List<Category> categories = categoryService.getAllCategories();

		if (sortBy != null && sortMethod != null) {
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("sortMethod", sortMethod);
		}

		model.addAttribute("pagesCount", pages);
		model.addAttribute("elementsOnPage", elementsOnPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("categories", categories);
		model.addAttribute("subjects", subjects);
		return "viewAllSubjects";
	}

	@RequestMapping(value = "/changeSubjectCategory", method = RequestMethod.GET)
	public String changeSubjectCategory(
			@RequestParam(value = "subjectId", required = false) Integer subjectId,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "sortMethod", required = false) String sortMethod,

			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		if (subjectId != null && categoryId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			Category category = categoryService.getCategoryById(categoryId);
			redirectAttributes.addAttribute("successMessage",
					"You are change <b>" + subject.getName()
							+ "</b> category from <b>"
							+ subject.getCategory().getName() + "</b> to <b>"
							+ category.getName() + "</b>");
			subject.setCategory(category);
			subjectService.updateSubject(subject);
		} else {
			redirectAttributes.addAttribute("errorMessage",
					"Can't change category, input parameters is invalid!");
		}
		redirectAttributes.addAttribute("currentPage", currentPage);
		redirectAttributes.addAttribute("elementsOnPage", elementsOnPage);
		redirectAttributes.addAttribute("searchText", searchText);
		redirectAttributes.addAttribute("searchOption", searchOption);
		redirectAttributes.addAttribute("sortBy", sortBy);
		redirectAttributes.addAttribute("sortMethod", sortMethod);
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
