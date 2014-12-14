package com.softserve.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.entity.Category;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.entity.Role;
import com.softserve.entity.Subject;
import com.softserve.entity.TeacherRequest;
import com.softserve.entity.User;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.QuestionService;
import com.softserve.service.RoleService;
import com.softserve.service.SubjectService;
import com.softserve.service.TeacherRequestService;
import com.softserve.service.TestService;
import com.softserve.service.UserService;
import com.softserve.service.impl.QuestionServiceImpl;
import com.softserve.service.impl.TestServiceImpl;

/**
 * The Class AdministratorController.
 *
 * @author Ivan Khotynskyi
 */
@Controller
public class AdministratorController {

	private static final int MIN_ELEMENTS_ON_PAGE = 1;

	private static final int MAX_ELEMENTS_ON_PAGE = 100;

	private static final int MAX_CATEGORY_NAME_LENGTH = 35;

	private static final String ERROR_MESSAGE = "errorMessage";

	private static final String SUCCESS_MESSAGE = "successMessage";

	public static final int DEFAULT_ELEMENTS_ON_PAGE = 10;

	public static final int DEFAULT_LAST_REGISTRED_DAYS = 8;

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
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private TestService testService;

	/**
	 * Administrator it's method that set parameters and redirect to start admin
	 * page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public String administrator(
			@RequestParam(value = SUCCESS_MESSAGE, required = false) String successMessage,
			@RequestParam(value = ERROR_MESSAGE, required = false) String errorMessage,
			Model model) {
		LOG.debug("Visit administrator page");


//		Question question = new Question();
//
//		question.setTest(testService.getTestById(2));
//
//		QuestionText qt = new QuestionText();
//		qt.setValue("How old are you?");
//
//		Option o1 = new Option();
//		o1.setCorrect(true);
//		o1.setValue("Bigger than 0");
//
//		Option o2 = new Option();
//		o2.setCorrect(false);
//		o2.setValue("I don't know");
//
//		List<Option> options = new ArrayList<Option>();
//		options.add(o1);
//		options.add(o2);
//		qt.setOptions(options);
//
//		question.setMark(5);
//
//		question.setQuestionText(qt);
//
//		questionService.addQuestion(question);


//		System.out.println("--Base64->" + questionService.getQuestionById(1).getQuestionText());
//		System.out.println("--Question->" + questionService.getQuestionById(1).getQuestion());
//		System.out.println("--Option->" + questionService.getQuestionById(1).getQuestion().getOptions().get(1).getValue());


		long subjectsCount = subjectService.getCountOfSubjects();
		int categoriesCount = categoryService.getAllCategories().size();
		long usersCount = userService.getCountOfUsers();
		String supportEmail = administratorService.getSupportEmail();
		Map<String, Long> listMap = administratorService.getCountRegistredUsersByLastDays(DEFAULT_LAST_REGISTRED_DAYS);
		List<String> lastRegDates = new ArrayList<String>();
		List<Long> lastRegUsers = new ArrayList<Long>();

		for (String date : listMap.keySet()) {
			lastRegDates.add(date);
		}
		System.out.println(lastRegDates.toString());

		for (Long count : listMap.values()) {
			lastRegUsers.add(count);
		}

		model.addAttribute("lastRegDates", lastRegDates);
		model.addAttribute("lastRegUsers", lastRegUsers);

		setMessage(model, successMessage, errorMessage);
		setAactiveTeacherRequests(model);

		model.addAttribute("subjectsCount", subjectsCount);
		model.addAttribute("categoriesCount", categoriesCount);
		model.addAttribute("usersCount", usersCount);
		model.addAttribute("supportEmail", supportEmail);

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
			@RequestParam(value = SUCCESS_MESSAGE, required = false) String successMessage,
			@RequestParam(value = ERROR_MESSAGE, required = false) String errorMessage,
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit deleteCategory page");
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			if (category != null) {

				redirectAttributes
						.addFlashAttribute(
								SUCCESS_MESSAGE,
								getSpringMessage("label.category")
										+ " <strong>"
										+ category.getName()
										+ "</strong> "
										+ getSpringMessage("message.admin.delete_category"));
				categoryService.deleteCategory(category);
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_categoty") + " "
								+ categoryId);
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
	@RequestMapping(value = "/addCategory")
	public String addCategory(
			@RequestParam(value = "category", required = false) String categoryName,
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit addCategory page");
		if (categoryName != null && categoryName != "") {
			if (categoryName.length() < MAX_CATEGORY_NAME_LENGTH) {
				categoryName = categoryName.trim();
				if (!administratorService.addCategory(categoryName)) {
					redirectAttributes
							.addFlashAttribute(
									SUCCESS_MESSAGE,
									getSpringMessage("label.category")
											+ " <strong>"
											+ categoryName
											+ "</strong> "
											+ getSpringMessage("message.admin.category_added"));
				} else {
					redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
							getSpringMessage("label.category") + " <strong>"
									+ categoryName + "</strong> "
									+ getSpringMessage("message.admin.exist"));
				}
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "<strong>"
						+ categoryName + "</strong> - "
						+ getSpringMessage("message.admin.long_name"));
			}
		}
		return "redirect:/viewAllCategories";
	}

	@RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
	public String changeCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "category", required = false) String category,
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		if (categoryId != null && category != null && category != "") {
			Category myCategory = categoryService.getCategoryById(categoryId);
			if (myCategory != null) {
				String oldName = myCategory.getName();
				myCategory.setName(category);
				categoryService.updateCategory(myCategory);
				redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE,
						getSpringMessage("label.category") + " <b>" + oldName
								+ "</b> "
								+ getSpringMessage("message.admin.was_changed")
								+ " <b>" + myCategory.getName() + "</b>");
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_categoty") + " "
								+ categoryId);
			}
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
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
			@RequestParam(value = SUCCESS_MESSAGE, required = false) String successMessage,
			@RequestParam(value = ERROR_MESSAGE, required = false) String errorMessage,
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
				users = userService.getUsersByTextByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getCountOfUsersByText(searchText);
				break;
			case "userFirstName":
				users = userService.getUsersByFirstNameByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getCountOfUsersByFirstName(searchText);
				break;
			case "userLastName":
				users = userService.getUsersByLastNameByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getCountOfUsersByLastName(searchText);
				break;
			case "role":
				users = userService.getUsersByRoleByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = userService.getCountOfUsersByRole(searchText);
				break;
			}

			searchText = afterSearch(searchText);
			searchOption = afterSearch(searchOption);
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = userService.getCountOfUsers();
			users = userService.getUsersByPage(startPosition, limitLength,
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		if (userId != null && roleId != null && userId != 1) {
			User user = userService.getUserById(userId);
			if (user != null) {
				Role role = roleService.getRoleById(roleId);
				if (role != null) {
					user.setRole(role);
					userService.updateUser(user);
					redirectAttributes
							.addFlashAttribute(
									SUCCESS_MESSAGE,
									getSpringMessage("label.user")
											+ "<b> 	"
											+ user.getEmail()
											+ "</b> "
											+ getSpringMessage("message.admin.change_role")
											+ " <b>" + user.getRole().getName()
											+ "</b>");
				} else {
					redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
							getSpringMessage("message.admin.no_role") + " "
									+ roleId);
				}
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_user") + " "
								+ userId);
			}
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserStatus page");
		if (userId != null && userId != 1) {
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
					redirectAttributes
							.addFlashAttribute(
									SUCCESS_MESSAGE,
									getSpringMessage("label.user")
											+ " <b>"
											+ user.getEmail()
											+ "</b> "
											+ getSpringMessage("message.admin.user_unblock"));
				} else {
					user.setBlocked(true);
					redirectAttributes
							.addFlashAttribute(
									SUCCESS_MESSAGE,
									getSpringMessage("label.user")
											+ " <b>"
											+ user.getEmail()
											+ "</b> "
											+ getSpringMessage("message.admin.user_block"));
				}
				userService.updateUser(user);
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_user") + " "
								+ userId);
			}
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
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
			@RequestParam(value = SUCCESS_MESSAGE, required = false) String successMessage,
			@RequestParam(value = ERROR_MESSAGE, required = false) String errorMessage,
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
				subjects = subjectService.getSubjectsByTextByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getCountOfSubjectsByText(searchText);
				break;
			case "subject":
				subjects = subjectService.getSubjectsByNameByPage(searchText,
						startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getCountOfSubjectsByName(searchText);
				break;
			case "category":
				subjects = subjectService.getSubjectsByCategoryByPage(
						searchText, startPosition, limitLength, this.sortBy,
						this.sortMethod);
				count = subjectService.getCountOfSubjectsByCategory(searchText);
				break;
			}
			searchText = afterSearch(searchText);
			searchOption = afterSearch(searchOption);
			model.addAttribute("searchText", searchText);
			model.addAttribute("searchOption", searchOption);

		} else {
			count = subjectService.getCountOfSubjects();

			subjects = subjectService.getSubjectsByPage(startPosition,
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeSubjectCategory page");
		if (subjectId != null && categoryId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			if (subject != null) {
				Category category = categoryService.getCategoryById(categoryId);
				if (category != null) {
					subject.setCategory(category);
					subjectService.updateSubject(subject);
					redirectAttributes
							.addFlashAttribute(
									SUCCESS_MESSAGE,
									getSpringMessage("message.admin.category_subject")
											+ " "
											+ subject.getName()
											+ "</b>, "
											+ getSpringMessage("message.admin.change_category")
											+ " <b>"
											+ subject.getCategory().getName()
											+ "</b>");
				} else {
					redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
							"No category with id " + categoryId);
				}
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_subject") + " "
								+ subjectId);
			}
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
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
			@RequestParam(value = SUCCESS_MESSAGE, required = false) String successMessage,
			@RequestParam(value = ERROR_MESSAGE, required = false) String errorMessage,
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToTeacher page");
		if (userId != null && userId != 1) {
			User user = userService.getUserById(userId);
			if (user != null) {
				user.setBlocked(false);
				userService.updateUser(user);
				TeacherRequest teacherRequest = teacherRequestService
						.getTeacherRequestByUserId(userId);
				teacherRequest.setActive(false);
				teacherRequestService.updateTeacherRequest(teacherRequest);
				redirectAttributes.addFlashAttribute(
						SUCCESS_MESSAGE,
						getSpringMessage("label.user") + " <b>"
								+ user.getEmail() + "</b> "
								+ getSpringMessage("message.admin.to_teacher"));
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_user") + " "
								+ userId);
			}

		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
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
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToAdmin page");
		if (userId != null) {
			TeacherRequest teacherRequest = teacherRequestService
					.getTeacherRequestByUserId(userId);
			if (teacherRequest != null) {
				teacherRequest.setActive(false);
				teacherRequestService.updateTeacherRequest(teacherRequest);
				redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE,
						getSpringMessage("message.admin.delete_request"));
			} else {
				redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
						getSpringMessage("message.admin.no_user_request") + " "
								+ userId);
			}
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
		}
		return "redirect:/viewAllRequests";
	}

	@RequestMapping(value = "/changeSupportEmail")
	public String changeSupportEmail(
			@RequestParam(value = "email", required = false) String email,
			RedirectAttributes redirectAttributes) {
		LOG.debug("Visit changeUserRoleToAdmin page");
		if (email != null & email != "") {
			administratorService.setSupportEmail(email);
			redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE,
					getSpringMessage("label.support_email") + " "
							+ getSpringMessage("message.admin.was_changed")
							+ " " + email);
		} else {
			redirectAttributes.addFlashAttribute(ERROR_MESSAGE,
					getSpringMessage("message.admin.invalid_parameters"));
		}
		return "redirect:/administrator";
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
		count = subjectService.getCountOfSubjectsByCategory(category.getName());
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
				jsonObject.put("roleName", role.getName());
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
			model.addAttribute(SUCCESS_MESSAGE, successMessage);
		}
		if (errorMessage != null) {
			model.addAttribute(ERROR_MESSAGE, errorMessage);
		}
	}

	private void setPaginationProperties(Model model, Integer elementsOnPage,
			Integer currentPage) {
		if (elementsOnPage == null || elementsOnPage < MIN_ELEMENTS_ON_PAGE
				|| elementsOnPage > MAX_ELEMENTS_ON_PAGE) {
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

	// change try catch bla bla foreach...
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

	private String getSpringMessage(String key) {
		return messageSource.getMessage(key, new Object[] {},
				LocaleContextHolder.getLocale());
	}
}
