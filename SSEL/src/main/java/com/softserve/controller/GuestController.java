package com.softserve.controller;

<<<<<<< HEAD
import java.util.List;
import java.util.Set;

=======
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
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
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
<<<<<<< HEAD
=======
import com.softserve.entity.User;
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.UserService;

@Controller
public class GuestController {
<<<<<<< HEAD
	
=======

>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
	private static final Logger LOG = LoggerFactory
			.getLogger(GuestController.class);

	@Autowired
	private SubjectService subjectService;
<<<<<<< HEAD
	
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
=======

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

	private User user;

	@RequestMapping(value = "/enter")
	public String enter(Model model, Principal principal) {
		LOG.info("User login {}", principal.getName());
		user = userService.getUserByEmail(principal.getName());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOG.debug("Visit index page as guest");
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
<<<<<<< HEAD
		LOG.debug("Visit index page as guest");
		return "index";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer subjectId, Model model) {
=======
		return "index";
	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer subjectId, Model model) {
		LOG.debug("Visit course page as guest");
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		Subject subject = subjectService.getSubjectById(subjectId);
<<<<<<< HEAD
		List<CourseScheduler> schedule = 
				cSchedulerService.getCourseScheduleresBySubjectId(subject.getId());
		StudentGroup row = studCabService.getStudentGroupByUserAndCourseId(1, schedule.get(0).getId());
		boolean isSubscribe = row == null ? true : false; 
=======
		List<CourseScheduler> schedule = cSchedulerService
				.getCourseScheduleresBySubjectId(subject.getId());
		StudentGroup row = studCabService.getStudentGroupByUserAndCourseId(1,
				schedule.get(0).getId());
		boolean isSubscribe = row == null;
>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
		model.addAttribute("isSubscribe", isSubscribe);
		model.addAttribute("schedule", schedule.get(0));
		model.addAttribute("subject", subject);
		return "course";
	}
<<<<<<< HEAD
	
	//extractedmethod
//	Role role = new Role();
//	role.setRole("Student");
//	roleService.addRole(role);
//	User user = new User();
//	user.setFirstName("Vasya");
//	user.setLastName("Vasya");
//	user.setEmail("aaa@gmail.com");
//	user.setPassword("fsdfsd");
//	user.setExpired(new Date(54353455454L));
//	user.setRegistration(new Date(54354353L));
//	user.setRole(roleService.getRoleById(1));
//	userService.addUser(user);
//	StudentGroup studentGroup = new StudentGroup();
//	studentGroup.setCourseScheduler(schedule.get(0));
//	studentGroup.setUser(userService.getUserById(1));
//	studentGroup.setGroupNumber(101);
//	studentGroupService.addStudentGroup(studentGroup);
//	StudentGroup studentGroup = new StudentGroup();
//	studentGroup.setCourseScheduler(schedule.get(0));
//	studentGroup.setUser(userService.getUserById(1));
//	studentGroup.setGroupNumber(101);
//	studentGroupService.addStudentGroup(studentGroup);

	
=======

>>>>>>> fae818a3304fe5f38fbc5b9f614f540a0a7e00df
}
