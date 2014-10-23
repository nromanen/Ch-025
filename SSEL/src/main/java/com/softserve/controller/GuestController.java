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
import com.softserve.entity.StudentGroup;
import com.softserve.entity.Subject;
import com.softserve.service.CategoryService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.RoleService;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		LOG.debug("Visit index page as guest");
		return "index";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course(@RequestParam Integer subjectId, Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		Subject subject = subjectService.getSubjectById(subjectId);
		List<CourseScheduler> schedule = 
				cSchedulerService.getCourseScheduleresBySubjectId(subject.getId());
		StudentGroup row = studCabService.getStudentGroupByUserAndCourseId(1, schedule.get(0).getId());
		boolean isSubscribe = row == null ? true : false; 
		model.addAttribute("isSubscribe", isSubscribe);
		model.addAttribute("schedule", schedule.get(0));
		model.addAttribute("subject", subject);
		return "course";
	}
	
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

	
}
