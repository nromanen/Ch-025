package com.softserve.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.softserve.service.RoleService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;
import com.softserve.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class TeacherController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private BlockService blockService;

	@Autowired
	private CourseSchedulerService courseSchedulerService;

	@Autowired
	private StudentGroupService studentGroupService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String categories(Model model) {
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("catList", categoryList);
		return "categories";
	}

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(Model model, HttpSession sess) {
		User user = (User) sess.getAttribute("user");
		
		if (user != null) {
		//if (subjectService.getSubjectsByUserId(user.getId()) != null) {
		//List<Subject> subjectList = subjectService.getSubjectsByUserId(user.getId());
		//Set<Subject> subjectList = subjectService.getAllSubjects();
		List<CourseScheduler> schedulerList = courseSchedulerService.getCourseSchedulersBySubjectUserId(user.getId());
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("catList", categories);
		//model.addAttribute("subjectList", subjectList);
		model.addAttribute("schedulerList", schedulerList);
		model.addAttribute("user", user);
		}
		return "teacher";
	}

	@RequestMapping(value = "/teacherCourse", method = RequestMethod.GET)
	public String teacherCourse(@RequestParam(value = "subjectId", required = false) Integer subjectId, Model model) {
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Category> categories = categoryService.getAllCategories();

		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		try {
			List<Topic> topics = topicService.getTopicsBySubjectId(subjectId);
			List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
			Subject subject = subjectService.getSubjectById(subjectId);

			model.addAttribute("topicList", topics);
			model.addAttribute("blockList", blocks);
			model.addAttribute("subject", subject);
		} catch (NullPointerException e) {
			return "teacher";
		}
		return "teacherCourse";
	}

	@RequestMapping(value = "/editTopic", method = RequestMethod.GET)
	public String editTopic(@RequestParam(value = "topicId", required = false) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId, Model model) {
		if (topicId != null) {
			Topic topic = topicService.getTopicById(topicId);
			model.addAttribute("topic", topic);
		}

		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		model.addAttribute("blockList", blocks);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("catList", categories);

		return "editTopic";
	}

	@RequestMapping(value = "/editSubject", method = RequestMethod.GET)
	public String editSubject(@RequestParam(value = "subjectId", required = false) Integer subjectId, Model model) {
		if (subjectId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			model.addAttribute("subject", subject);

			List<CourseScheduler> courseSchedulerList = courseSchedulerService
					.getCourseScheduleresBySubjectId(subjectId);
			model.addAttribute("scheduler", courseSchedulerList.get(0));
		}
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("catList", categoryList);

		return "editSubject";
	}

	@RequestMapping(value = "/editBlock", method = RequestMethod.GET)
	public String editBlock(@RequestParam(value = "blockId", required = false) Integer blockId, Model model) {
		if (blockId != null) {
			Block block = blockService.getBlockById(blockId);
			model.addAttribute("block", block);
		}

		List<Subject> subjectList = subjectService.getAllSubjects();
		model.addAttribute("subjectList", subjectList);

		return "editBlock";
	}

	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public String editCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			model.addAttribute("category", category);
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("catList", categories);

		}

		return "editCategory";
	}

	@RequestMapping(value = "/saveTopic", method = RequestMethod.GET)
	public String saveTopic(@RequestParam(value = "topicId", required = false) Integer topicId,
			@RequestParam(value = "blockId", required = true) Integer blockId,
			@RequestParam(value = "topicAlive", required = true) boolean topicAlive,
			@RequestParam(value = "topicContent", required = true) String topicContent,
			@RequestParam(value = "topicName", required = true) String topicName,
			@RequestParam(value = "topicOrder", required = true) Integer topicOrder, Model model) {
		Topic topic = topicId != null ? topicService.getTopicById(topicId) : new Topic();
		topic.setBlock(blockService.getBlockById(blockId));
		topic.setAlive(topicAlive);
		topic.setContent(topicContent);
		topic.setName(topicName);
		topic.setOrder(topicOrder);

		if (topicId != null) {
			topicService.updateTopic(topic);
		} else {
			topicService.addTopic(topic);
		}

		return "redirect:/teacher";
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.GET)
	public String saveCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "categoryName", required = true) String categoryName, Model model) {
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			category.setName(categoryName);
			categoryService.updateCategory(category);
		} else {
			Category category = new Category();
			category.setName(categoryName);
			categoryService.addCategory(category);
		}

		return "redirect:/categories";
	}

	@RequestMapping(value = "/saveBlock", method = RequestMethod.GET)
	public String saveBlock(@RequestParam(value = "blockId", required = false) Integer blockId,
			@RequestParam(value = "blockName", required = true) String blockName,
			@RequestParam(value = "blockOrder", required = true) Integer blockOrder,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Subject subject = subjectService.getSubjectById(subjectId);
		Block block = blockId != null ? blockService.getBlockById(blockId) : new Block();
		block.setName(blockName);
		block.setOrder(blockOrder);
		block.setSubject(subject);
		block.setStartTime(format.parse(startDate));
		block.setEndTime(format.parse(endDate));

		if (blockId != null) {
			blockService.updateBlock(block);
		} else {
			blockService.updateBlock(block);
		}
		model.addAttribute("subjectId", subjectId);
		return "redirect:/teacherCourse";
	}

	@RequestMapping(value = "/saveSubject", method = RequestMethod.GET)
	public String saveSubject(@RequestParam(value = "subjectId", required = false) Integer subjectId,
			@RequestParam(value = "subjectName", required = true) String subjectName,
			@RequestParam(value = "subjectDescription", required = true) String subjectDescription,
			@RequestParam(value = "subjectCategoryId", required = true) Integer subjectCategoryId,
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Category category = categoryService.getCategoryById(subjectCategoryId);
		Subject subject = subjectId != null ? subjectService.getSubjectById(subjectId) : new Subject();
		subject.setName(subjectName);
		subject.setDescription(subjectDescription);
		subject.setCategory(category);

		if (subjectId != null) {
			subjectService.updateSubject(subject);
		} else {
			CourseScheduler scheduler = new CourseScheduler();
			scheduler.setStart(format.parse(startDate));
			scheduler.setEnd(format.parse(endDate));
			scheduler.setSubject(subject);
			subjectService.addSubject(subject);

			courseSchedulerService.addCourseScheduler(scheduler);

		}

		return "redirect:/teacher";
	}

	@RequestMapping(value = "/enableTopic", method = RequestMethod.GET)
	public String enableTopic(@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "enable", required = true) boolean enable, Model model) {
		Topic topic = topicService.getTopicById(topicId);
		if (enable) {
			topic.setAlive(true);
		} else {
			topic.setAlive(false);
		}
		topicService.updateTopic(topic);
		model.addAttribute("subjectId", subjectId);
		return "redirect:/teacherCourse";
	}

	@RequestMapping(value = "/deleteTopic", method = RequestMethod.GET)
	public String deleteTopic(@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId, Model model) {
		Topic topic = topicService.getTopicById(topicId);
		topicService.deleteTopic(topic);
		model.addAttribute("subjectId", subjectId);
		return "redirect:/teacherCourse";

	}

	@RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
	public String deleteSubject(@RequestParam(value = "subjectIds", required = true) String subjectIds, Model model) {

		for (String idInStr : subjectIds.split(",")) {
			try {
				Integer id = Integer.parseInt(idInStr);
				Subject subject = subjectService.getSubjectById(id);
				List<Block> blocks = blockService.getBlocksBySubjectId(subject.getId());
				List<Topic> topics = topicService.getTopicsBySubjectId(subject.getId());
				List<CourseScheduler> cs = courseSchedulerService.getCourseScheduleresBySubjectId(subject.getId());

				for (Topic t : topics)
					topicService.deleteTopic(t);

				for (Block b : blocks)
					blockService.deleteBlock(b);

				for (CourseScheduler c : cs) {
					Integer studentGroupNum = studentGroupService.getGroupNumberByCourse(c.getId());
					List<StudentGroup> sg = studentGroupService.getStudentGroupsByGroupNumber(studentGroupNum);
					for (StudentGroup ss : sg)
						studentGroupService.deleteStudentGroup(ss);

					courseSchedulerService.deleteCourseScheduler(c);
				}

				subjectService.deleteSubject(subject);
			} catch (Exception e) {
			}
		}
		return "redirect:/teacher";
	}

	@RequestMapping(value = "/deleteCategories", method = RequestMethod.GET)
	public String deleteCategories(@RequestParam(value = "categoriesIds", required = true) String categoriesIds,
			Model model) {

		for (String idInStr : categoriesIds.split(",")) {
			try {
				Integer id = Integer.parseInt(idInStr);
				Category category = categoryService.getCategoryById(id);
				categoryService.deleteCategory(category);
			} catch (Exception e) {
			}
		}
		return "redirect:/categories";
	}

	@RequestMapping(value = "/changeTopicOrder", method = RequestMethod.GET)
	public String changeTopicOrder(@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "updown", required = true) String updown, Model model) {
		Topic topic = topicService.getTopicById(topicId);
		if (updown.equals("up")) {
			topicService.changeOrderUp(topic);
		} else if (updown.equals("down")) {
			topicService.changeOrderDown(topic);
		}
		model.addAttribute("subjectId", subjectId);
		return "redirect:/teacherCourse";
	}

}
