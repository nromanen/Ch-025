package com.softserve.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Role;
import com.softserve.entity.Subject;
import com.softserve.entity.TeacherRequest;
import com.softserve.entity.User;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.LogService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TeacherRequestService;
import com.softserve.service.UserService;

/**
 * The Class AdministratorController.
 * @author Ivan Khotynskyi
 */
@Controller
public class AdministratorController {

	public static final int DEFAULT_ELEMENTS_ON_PAGE = 10;

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);
	private int activeTeacherRequests;

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

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Resource(name = "LogService")
	private LogService logService;

	/**
	 * Administrator it's method that set parameters and redirect to start admin page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator(Model model) {
		LOG.debug("Visit administrator page");
		List<Subject> subjects = subjectService.getAllSubjects();
		List<CourseScheduler> courceScheduler = courceSchedulerService
				.getAllCourseScheduleres();
		List<User> users = userService.getAllUsers();
		activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		model.addAttribute("subjects", subjects.size());
		model.addAttribute("courceScheduler", courceScheduler.size());
		model.addAttribute("users", users.size());

		return "administrator";
	}

	/**
	 * View all categories it's method that set parameters and redirect to page with all categories.
	 *
	 * @param successMessage the success message
	 * @param errorMessage the error message
	 * @param model the model
	 * @return the string
	 */
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
		activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		return "viewAllCategories";
	}

	/**
	 * Delete category it's method that delete category and redirect to page with categories.
	 *
	 * @param categoryId the category id
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
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

	/**
	 * Add category it's method create new category and redirect to page with categories.
	 *
	 * @param categoryName the category name
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategory(
			@RequestParam(value = "category", required = false) String categoryName,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryName != null && categoryName != "") {
			if (categoryName.length() < 35) {
			if (!administratorService.addCategory(categoryName)) {
				redirectAttributes.addFlashAttribute("successMessage",
						"You are add category: <strong>" + categoryName
								+ "</strong>");
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Category: <strong>" + categoryName
								+ "</strong> allready exist!");
			}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Category name: <strong>" + categoryName
								+ "</strong> is to long!");
			}
		}
		return "redirect:/viewAllCategories";
	}

	/**
	 * View all users it's method set parameters and redirect to page with users.
	 *
	 * @param successMessage the success message
	 * @param errorMessage the error message
	 * @param searchText the search text
	 * @param searchOption the search option
	 * @param elementsOnPage the elements on page
	 * @param currentPage the current page
	 * @param sortBy the sort by
	 * @param sortMethod the sort method
	 * @param model the model
	 * @return the string
	 */
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
		long count = 0;
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

//		count = userService.getUsersCount();
//		pages = (int) count / elementsOnPage;

		if (sortBy == null && sortMethod == null) {
			sortBy = "registration";
			sortMethod = "desc";
		}
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


		List<Role> roles = roleService.getAllRoles();
		int activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();

		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortMethod", sortMethod);
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		model.addAttribute("pagesCount", pages);
		model.addAttribute("elementsOnPage", elementsOnPage);
		model.addAttribute("currentPage", currentPage);
		return "viewAllUsers";
	}

	/**
	 * Change user role it's method change user role and redirect to page with users.
	 *
	 * @param userId the user id
	 * @param roleId the role id
	 * @param elementsOnPage the elements on page
	 * @param currentPage the current page
	 * @param searchText the search text
	 * @param searchOption the search option
	 * @param sortBy the sort by
	 * @param sortMethod the sort method
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
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

	/**
	 * Change user status it's method change user status and redirect to page with users.
	 *
	 * @param userId the user id
	 * @param elementsOnPage the elements on page
	 * @param currentPage the current page
	 * @param searchText the search text
	 * @param searchOption the search option
	 * @param sortBy the sort by
	 * @param sortMethod the sort method
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
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
				TeacherRequest teacherRequest = teacherRequestService
						.getTeacherRequestByUserId(userId);
				if (teacherRequest != null) {
					teacherRequest.setActive(false);
					teacherRequestService.updateTeacherRequest(teacherRequest);
				}
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

	/**
	 * View all subjects this method set parameters and shows subjects/
	 *
	 * @param successMessage the success message
	 * @param errorMessage the error message
	 * @param searchText the search text
	 * @param searchOption the search option
	 * @param elementsOnPage the elements on page
	 * @param currentPage the current page
	 * @param sortBy the sort by
	 * @param sortMethod the sort method
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/viewAllSubjects")
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
		long count = 0;
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

//		count = subjectService.getSubjectsCount();
//		pages = (int) count / elementsOnPage;

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

		//List<Category> categories = categoryService.getAllCategories();

		if (sortBy != null && sortMethod != null) {
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("sortMethod", sortMethod);
		}

		activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();

		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		model.addAttribute("pagesCount", pages);
		model.addAttribute("elementsOnPage", elementsOnPage);
		model.addAttribute("currentPage", currentPage);
		//model.addAttribute("categories", categories);
		model.addAttribute("subjects", subjects);
		return "viewAllSubjects";
	}

	/**
	 * Change subject ajax.
	 *
	 * @param elementsOnPage the elements on page
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/changeSubjectAjax")
	@ResponseBody
	public String changeSubjectAjax(
			@RequestParam(value = "elementsOnPage", required = false) Integer elementsOnPage,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("elementsOnPage", elementsOnPage);
		return "redirect:/viewAllSubjects";
	}

	/**
	 * Change subject category its method change subject category/
	 *
	 * @param subjectId the subject id
	 * @param categoryId the category id
	 * @param elementsOnPage the elements on page
	 * @param currentPage the current page
	 * @param searchText the search text
	 * @param searchOption the search option
	 * @param sortBy the sort by
	 * @param sortMethod the sort method
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
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
			redirectAttributes.addFlashAttribute("successMessage",
					"You are change <b>" + subject.getName()
							+ "</b> category from <b>"
							+ subject.getCategory().getName() + "</b> to <b>"
							+ category.getName() + "</b>");
			subject.setCategory(category);
			subjectService.updateSubject(subject);
		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
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

	/**
	 * View all requests it's method shows requests and redirect to page with requests.
	 *
	 * @param successMessage the success message
	 * @param errorMessage the error message
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/viewAllRequests", method = RequestMethod.GET)
	public String viewAllRequests(
			@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model model) {
		LOG.debug("Visit viewAllRequests page");
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}

		List<TeacherRequest> teacherRequests = teacherRequestService
				.getAllActiveTeacherRequests();
		model.addAttribute("teacherRequests", teacherRequests);
		int activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		return "viewAllRequests";
	}

	/**
	 * Change user role to admin it's method change role to teacher and redirect to page with requests .
	 *
	 * @param userId the user id
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/changeUserRoleToTeacher")
	public String changeUserRoleToTeacher(
			@RequestParam(value = "userId", required = false) Integer userId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToTeacher page");
		if (userId != null) {
			User user = userService.getUserById(userId);

			user.setBlocked(false);
			userService.updateUser(user);
			TeacherRequest teacherRequest = teacherRequestService
					.getTeacherRequestByUserId(userId);
			teacherRequest.setActive(false);
			teacherRequestService.updateTeacherRequest(teacherRequest);
			redirectAttributes.addAttribute("successMessage",
					"You allow the user to: <b>" + user.getEmail()
							+ "</b> become <b>TEACHER</b>");

		} else {
			redirectAttributes.addAttribute("errorMessage",
					"Can't unblocked user, input parameters is invalid!");
		}
		return "redirect:/viewAllRequests";
	}

	/**
	 * Delete teacher request it's method hide teacher request.
	 *
	 * @param userId the user id
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/deleteTeacherRequest")
	public String deleteTeacherRequest(
			@RequestParam(value = "userId", required = false) Integer userId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToAdmin page");
		if (userId != null) {
			redirectAttributes.addAttribute("successMessage",
					"Request is delete");
			TeacherRequest teacherRequest = teacherRequestService
					.getTeacherRequestByUserId(userId);
			teacherRequest.setActive(false);
			teacherRequestService.updateTeacherRequest(teacherRequest);

		} else {
			redirectAttributes.addAttribute("errorMessage",
					"Can't delete request, input parameters is invalid!");
		}
		return "redirect:/viewAllRequests";
	}

	/**
	 * Check category it's method check if category connected with subject.
	 *
	 * @param categoryId the category id
	 * @return the string
	 */
	@RequestMapping(value = "/checkCategory", method=RequestMethod.POST)
    public @ResponseBody String checkCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		int count = (int) subjectService.getSubjectsByCategoryCount(category.getName());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		jsonObject.put("categoryId", categoryId);
		jsonObject.put("categoryName", category.getName());
		return jsonObject.toString();
    }

	/**
	 * Get category it's method get all categories without one.
	 *
	 * @param categoryId the category id
	 * @return the string
	 */
	@RequestMapping(value = "/getCategory", method=RequestMethod.POST)
    public @ResponseBody String getCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		JSONObject jsonObject;
		JSONArray jsonCategories = new JSONArray();
		for (Category category : categoryService.getAllCategories()) {
			if (!categoryId.equals(category.getId())) {
			jsonObject = new JSONObject();
			jsonObject.put("categoryId", category.getId());
			jsonObject.put("categoryName", category.getName());
			jsonCategories.put(jsonObject);
			}
		}
		jsonObject = new JSONObject();
		jsonObject.put("categories", jsonCategories);
		return jsonObject.toString();
    }
}
