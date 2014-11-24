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
 *
 * @author Ivan Khotynskyi
 */
@Controller
public class AdministratorController {

	public static final int DEFAULT_ELEMENTS_ON_PAGE = 10;

	private static final Logger LOG = LoggerFactory
			.getLogger(AdministratorController.class);
	private int activeTeacherRequests;
	private int elementsOnPage;
	private int currentPage;
	private int startPosition;
	private int limitLength;
	private String sortBy;
	private String sortMethod;
	long count;

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
	 * Administrator it's method that set parameters and redirect to start admin
	 * page.
	 *
	 * @param model
	 *            the model
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
	 * View all categories it's method that set parameters and redirect to page
	 * with all categories.
	 *
	 * @param successMessage
	 *            the success message
	 * @param errorMessage
	 *            the error message
	 * @param model
	 *            the model
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
		setMessage(model, successMessage, errorMessage);

		activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
		return "viewAllCategories";
	}

	/**
	 * deleteCategory delete category and redirect to page with categories.
	 *
	 * @param categoryId
	 *            the category id
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			if (category != null) {
				redirectAttributes.addFlashAttribute(
						"successMessage",
						"You are delete category: <strong>"
								+ category.getName() + "</strong>");
				categoryService.deleteCategory(category);
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No category vs id " + categoryId);
			}
		}
		return "redirect:/viewAllCategories";
	}

	/**
	 * Add category it's method create new category and redirect to page with
	 * categories.
	 *
	 * @param categoryName
	 *            the category name
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategory(
			@RequestParam(value = "category", required = false) String categoryName,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit viewAllCategories page");
		if (categoryName != null && categoryName != "") {
			if (categoryName.length() < 35) {
				categoryName = categoryName.trim();
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
	 * View all users it's method set parameters and redirect to page with
	 * users.
	 *
	 * @param successMessage
	 *            the success message
	 * @param errorMessage
	 *            the error message
	 * @param searchText
	 *            the search text
	 * @param searchOption
	 *            the search option
	 * @param elementsOnPage
	 *            the elements on page
	 * @param currentPage
	 *            the current page
	 * @param sortBy
	 *            the sort by
	 * @param sortMethod
	 *            the sort method
	 * @param model
	 *            the model
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

		setMessage(model, successMessage, errorMessage);
		setPaginationProperties(model, elementsOnPage, currentPage);
		setSortParameters(model, sortBy, sortMethod);

		if (searchText != null && !searchText.equals("")
				&& searchOption != null && !searchOption.equals("")) {
			searchText = beforeSearch(searchText);
			searchOption = beforeSearch(searchOption);

			switch (searchOption) {
			case "all":
				users = userService.getUsersByTextVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getUsersByTextCount(searchText);
				break;
			case "userFirstName":
				users = userService.getUsersByFirstNameVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getUsersByFirstNameCount(searchText);
				break;
			case "userLastName":
				users = userService.getUsersByLastNameVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getUsersByLastNameCount(searchText);
				break;
			case "role":
				users = userService.getUsersByRoleVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getUsersByRoleCount(searchText);
				break;
			}

			searchText = afterSearch(searchText);
			searchOption = afterSearch(searchOption);
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = userService.getUsersCount();
			users = userService.getUsersVsLimit(startPosition, limitLength,
					this.sortBy, this.sortMethod);
		}

		setPagesCount(model, count);
		setAactiveTeacherRequests(model);

		model.addAttribute("users", users);

		return "viewAllUsers";
	}

	/**
	 * Change user role it's method change user role and redirect to page with
	 * users.
	 *
	 * @param userId
	 *            the user id
	 * @param roleId
	 *            the role id
	 * @param elementsOnPage
	 *            the elements on page
	 * @param currentPage
	 *            the current page
	 * @param searchText
	 *            the search text
	 * @param searchOption
	 *            the search option
	 * @param sortBy
	 *            the sort by
	 * @param sortMethod
	 *            the sort method
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
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
			if (user != null) {
				Role role = roleService.getRoleById(roleId);
				if (role != null) {
					redirectAttributes.addFlashAttribute("successMessage",
							"You are change <b>" + user.getEmail()
									+ "</b> role from <b>"
									+ user.getRole().getRole() + "</b> to <b>"
									+ role.getRole() + "</b>");
					user.setRole(role);
					userService.updateUser(user);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage",
							"No role with id " + roleId);
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No user with id " + userId);
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
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
	 * Change user status it's method change user status and redirect to page
	 * with users.
	 *
	 * @param userId
	 *            the user id
	 * @param elementsOnPage
	 *            the elements on page
	 * @param currentPage
	 *            the current page
	 * @param searchText
	 *            the search text
	 * @param searchOption
	 *            the search option
	 * @param sortBy
	 *            the sort by
	 * @param sortMethod
	 *            the sort method
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
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
			if (user != null) {
				if (user.isBlocked()) {
					user.setBlocked(false);
					TeacherRequest teacherRequest = teacherRequestService
							.getTeacherRequestByUserId(userId);
					if (teacherRequest != null) {
						teacherRequest.setActive(false);
						teacherRequestService
								.updateTeacherRequest(teacherRequest);
					}
					redirectAttributes.addAttribute("successMessage",
							"You are unblock user: <b>" + user.getEmail()
									+ "</b> ");
				} else {
					user.setBlocked(true);
					redirectAttributes.addFlashAttribute("successMessage",
							"You are block user: <b>" + user.getEmail()
									+ "</b> ");
				}
				userService.updateUser(user);
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No user with id" + userId);
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
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
	 * @param successMessage
	 *            the success message
	 * @param errorMessage
	 *            the error message
	 * @param searchText
	 *            the search text
	 * @param searchOption
	 *            the search option
	 * @param elementsOnPage
	 *            the elements on page
	 * @param currentPage
	 *            the current page
	 * @param sortBy
	 *            the sort by
	 * @param sortMethod
	 *            the sort method
	 * @param model
	 *            the model
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

		setMessage(model, successMessage, errorMessage);
		setPaginationProperties(model, elementsOnPage, currentPage);
		setSortParameters(model, sortBy, sortMethod);

		if (searchText != null && !searchText.equals("")
				&& searchOption != null && !searchOption.equals("")) {
			searchText = beforeSearch(searchText);
			searchOption = beforeSearch(searchOption);
			switch (searchOption) {
			case "all":
				subjects = subjectService.getSubjectsByTextVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getSubjectsByTextCount(searchText);
				break;
			case "subject":
				subjects = subjectService.getSubjectsByNameVsLimit(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getSubjectsByNameCount(searchText);
				break;
			case "category":
				subjects = subjectService.getSubjectsByCategoryVsLimit(
						searchText, startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getSubjectsByCategoryCount(searchText);
				break;
			}
			searchText = afterSearch(searchText);
			searchOption = afterSearch(searchOption);
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = subjectService.getSubjectsCount();

			subjects = subjectService.getSubjectsVsLimit(startPosition,
					limitLength, this.sortBy, this.sortMethod);
		}

		setPagesCount(model, count);
		setAactiveTeacherRequests(model);

		model.addAttribute("subjects", subjects);
		return "viewAllSubjects";
	}

	/**
	 * Change subject category its method change subject category/
	 *
	 * @param subjectId
	 *            the subject id
	 * @param categoryId
	 *            the category id
	 * @param elementsOnPage
	 *            the elements on page
	 * @param currentPage
	 *            the current page
	 * @param searchText
	 *            the search text
	 * @param searchOption
	 *            the search option
	 * @param sortBy
	 *            the sort by
	 * @param sortMethod
	 *            the sort method
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
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
			if (subject != null) {
				Category category = categoryService.getCategoryById(categoryId);
				if (category != null) {
					redirectAttributes.addFlashAttribute("successMessage",
							"You are change <b>" + subject.getName()
									+ "</b> category from <b>"
									+ subject.getCategory().getName()
									+ "</b> to <b>" + category.getName()
									+ "</b>");
					subject.setCategory(category);
					subjectService.updateSubject(subject);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage",
							"No category with id " + categoryId);
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No subject with id " + subjectId);
			}
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
	 * View all requests it's method shows requests and redirect to page with
	 * requests.
	 *
	 * @param successMessage
	 *            the success message
	 * @param errorMessage
	 *            the error message
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/viewAllRequests")
	public String viewAllRequests(
			@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model model) {
		LOG.debug("Visit viewAllRequests page");
		setMessage(model, successMessage, errorMessage);
		setAactiveTeacherRequests(model);
		List<TeacherRequest> teacherRequests = teacherRequestService
				.getAllActiveTeacherRequests();
		model.addAttribute("teacherRequests", teacherRequests);

		return "viewAllRequests";
	}

	/**
	 * Change user role to admin it's method change role to teacher and redirect
	 * to page with requests .
	 *
	 * @param userId
	 *            the user id
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/changeUserRoleToTeacher")
	public String changeUserRoleToTeacher(
			@RequestParam(value = "userId", required = false) Integer userId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToTeacher page");
		if (userId != null) {
			User user = userService.getUserById(userId);
			if (user != null) {
				user.setBlocked(false);
				userService.updateUser(user);
				TeacherRequest teacherRequest = teacherRequestService
						.getTeacherRequestByUserId(userId);
				teacherRequest.setActive(false);
				teacherRequestService.updateTeacherRequest(teacherRequest);
				redirectAttributes.addFlashAttribute("successMessage",
						"You allow the user to: <b>" + user.getEmail()
								+ "</b> become <b>TEACHER</b>");
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No user with id " + userId);
			}

		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
					"Can't unblocked user, input parameters is invalid!");
		}
		return "redirect:/viewAllRequests";
	}

	/**
	 * Delete teacher request it's method hide teacher request.
	 *
	 * @param userId
	 *            the user id
	 * @param model
	 *            the model
	 * @param redirectAttributes
	 *            the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/deleteTeacherRequest")
	public String deleteTeacherRequest(
			@RequestParam(value = "userId", required = false) Integer userId,
			Model model, RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToAdmin page");
		if (userId != null) {
			TeacherRequest teacherRequest = teacherRequestService
					.getTeacherRequestByUserId(userId);
			if (teacherRequest != null) {
				teacherRequest.setActive(false);
				teacherRequestService.updateTeacherRequest(teacherRequest);
				redirectAttributes.addFlashAttribute("successMessage",
						"Request is delete");
			} else {
				redirectAttributes.addFlashAttribute("errorMessage",
						"No request from user with id " + userId);
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage",
					"Can't delete request, input parameters is invalid!");
		}
		return "redirect:/viewAllRequests";
	}

	/**
	 * Check category it's method check if category connected with subject.
	 *
	 * @param categoryId
	 *            the category id
	 * @return the string
	 */
	@RequestMapping(value = "/checkCategory", method = RequestMethod.POST)
	public @ResponseBody String checkCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		int count = (int) subjectService.getSubjectsByCategoryCount(category
				.getName());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		jsonObject.put("categoryId", categoryId);
		jsonObject.put("categoryName", category.getName());
		return jsonObject.toString();
	}

	/**
	 * Get category it's method get all categories without one.
	 *
	 * @param categoryId
	 *            the category id
	 * @return the string
	 */
	@RequestMapping(value = "/getCategory", method = RequestMethod.POST)
	public @ResponseBody String getCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId) {
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

	/**
	 * Get roles it's method get all roles without one.
	 *
	 * @param roleId
	 *            the role id
	 * @return the string
	 */
	@RequestMapping(value = "/getRole", method = RequestMethod.POST)
	public @ResponseBody String getRole(
			@RequestParam(value = "roleId", required = false) Integer roleId) {
		JSONObject jsonObject;
		JSONArray jsonRoles = new JSONArray();
		for (Role role : roleService.getAllRoles()) {
			if (!roleId.equals(role.getId())) {
				jsonObject = new JSONObject();
				jsonObject.put("roleId", role.getId());
				jsonObject.put("roleName", role.getRole());
				jsonRoles.put(jsonObject);
			}
		}
		jsonObject = new JSONObject();
		jsonObject.put("roles", jsonRoles);
		return jsonObject.toString();
	}

	private void setMessage(Model model, String successMessage,
			String errorMessage) {
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}
	}

	private void setPaginationProperties(Model model, Integer elementsOnPage,
			Integer currentPage) {
		if (elementsOnPage == null || elementsOnPage < 1
				|| elementsOnPage > 100) {
			this.elementsOnPage = DEFAULT_ELEMENTS_ON_PAGE;
		} else {
			this.elementsOnPage = elementsOnPage;
		}

		if (currentPage == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
		this.startPosition = (this.currentPage - 1) * this.elementsOnPage;
		this.limitLength = this.elementsOnPage;
		model.addAttribute("elementsOnPage", this.elementsOnPage);
		model.addAttribute("currentPage", this.currentPage);
	}

	private void setSortParameters(Model model, String sortBy, String sortMethod) {
		if (sortBy == null && sortMethod == null) {
			this.sortBy = "registration";
			this.sortMethod = "desc";
		} else {
			this.sortBy = sortBy;
			this.sortMethod = sortMethod;
		}
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortMethod", sortMethod);
	}

	private void setPagesCount(Model model, long count) {
		int pages = (int) count / this.elementsOnPage;
		if ((count % this.elementsOnPage) != 0) {
			pages++;
		}
		model.addAttribute("pagesCount", pages);
	}

	private void setAactiveTeacherRequests(Model model) {
		activeTeacherRequests = (int) teacherRequestService
				.getAllActiveTeacherRequestsCount();
		model.addAttribute("activeTeacherRequests", activeTeacherRequests);
	}

	private String beforeSearch(String str) {
		str = str.trim();
		str = str.replaceAll("\\<.*?>", "");
		str = str.replaceAll("\'", "\'\'");
		return str;
	}

	private String afterSearch(String str) {
		str = str.replaceAll("\"", "\u201D");
		str = str.replaceAll("\'\'", "\'");
		return str;
	}
}
