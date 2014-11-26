package com.softserve.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Block;
import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Group;
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
import com.softserve.validator.BlockValidator;
import com.softserve.validator.CategoryValidator;
import com.softserve.validator.StudyDocumentValidator;
import com.softserve.validator.SubjectValidator;
import com.softserve.validator.TopicValidator;
import com.softserve.service.GroupService;

/**
 * Handles requests for the application TEACHER Cabinet.
 */
@Controller
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

	@Autowired
	private TopicValidator topicValidator;

	@Autowired
	private BlockValidator moduleValidator;

	@Autowired
	private SubjectValidator subjectValidator;

	@Autowired
	private CategoryValidator categoryValidator;

	@Autowired
	private StudyDocumentValidator studyDocumentValidator;

	@Autowired
	private GroupService groupService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@InitBinder
	public void userInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(User.class, "user", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				User user = userService.getUserById(Integer.parseInt(id));
				setValue(user);
			}
		});
	}

	@InitBinder
	public void blockInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Block.class, "block", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Block block = blockService.getBlockById(Integer.parseInt(id));
				setValue(block);
			}
		});
	}

	@InitBinder
	public void subjectInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Subject.class, "subject", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Subject subject = subjectService.getSubjectById(Integer.parseInt(id));
				setValue(subject);
			}
		});
	}

	@InitBinder
	public void categoryInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Category category = categoryService.getCategoryById(Integer.parseInt(id));
				setValue(category);
			}
		});
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String categories(Model model) {
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("catList", categoryList);
		return "categories";
	}

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			List<CourseScheduler> schedulerList = courseSchedulerService.getCourseSchedulersBySubjectUserId(user
					.getId());
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("catList", categories);
			model.addAttribute("schedulerList", schedulerList);
			model.addAttribute("user", user);
		}
		return "teacher";
	}

	@RequestMapping(value = "/teacherCourse", method = RequestMethod.GET)
	public String teacherCourse(@RequestParam(value = "subjectId", required = false) Integer subjectId, Model model) {
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Category> categories = categoryService.getAllCategories();
		Map<Integer, Integer> blockSizesArray = new HashMap<Integer, Integer>();

		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		try {
			List<Topic> topics = topicService.getTopicsBySubjectId(subjectId);
			List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
			Subject subject = subjectService.getSubjectById(subjectId);
			
			for (Block block:blocks) {
				blockSizesArray.put(block.getId(), topicService.getTopicsByBlockId(block.getId()).size());
			}

			model.addAttribute("topicList", topics);
			model.addAttribute("blockList", blocks);
			model.addAttribute("subject", subject);
			model.addAttribute("blockSizesArray", blockSizesArray);
		} catch (NullPointerException e) {
			return "teacher";
		}
		return "teacherCourse";
	}

	@RequestMapping(value = "/editTopic", method = RequestMethod.GET)
	public String editTopic(@RequestParam(value = "topicId", required = false) Integer topicId,
			@RequestParam(value = "subjectId", required = false) Integer subjectId, Model model) {
		Topic topic = topicId != null ? topicService.getTopicById(topicId) : new Topic();

		model.addAttribute("topic", topic);
		model.addAttribute("subjectId", subjectId);
		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		model.addAttribute("blockList", blocks);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("catList", categories);

		return "editTopic";
	}

	@RequestMapping(value = "/saveTopic", method = RequestMethod.POST)
	public String saveTopic(@Valid @ModelAttribute("topic") Topic topic, BindingResult result, Model model) {
		// topicValidator.validate(topic, result);
		if (result.hasErrors()) {
			List<Block> blocks = blockService.getAllBlocks();
			model.addAttribute("blockList", blocks);
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("catList", categories);
			return "editTopic";
		}
		topicService.updateTopic(topic);
		return "redirect:/teacher";
	}

	@RequestMapping(value = "/saveTopic", method = RequestMethod.GET)
	public String saveTopic(@ModelAttribute("topic") Topic topic, Model model) {

		List<Block> blocks = blockService.getAllBlocks();
		model.addAttribute("blockList", blocks);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("catList", categories);
		return "editTopic";
	}

	@RequestMapping(value = "/saveBlock", method = RequestMethod.POST)
	public String saveBlock(@Valid @ModelAttribute("block") Block block, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Subject> subjectList = subjectService.getAllSubjects();
			model.addAttribute("subjectList", subjectList);
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("catList", categories);
			return "editBlock";
		}
		blockService.updateBlock(block);
		return "redirect:/teacher";
	}

	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
	public String saveSubject(@RequestParam(value = "user", required = false) String userId,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@Valid @ModelAttribute("subject") Subject subject, BindingResult result, Model model, HttpSession session)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("catList", categories);
		User user = (User) session.getAttribute("user");
		subject.setUser(user);
		if (result.hasErrors()) {
			return "editSubject";
		} else {

			if (subject.getId() < 1) {

				CourseScheduler scheduler = new CourseScheduler();
				scheduler.setStart(format.parse(startDate));
				scheduler.setEnd(format.parse(endDate));
				// subjectService.updateSubject(subject);
				scheduler.setSubject(subject);

				subjectService.addSubject(subject);

				courseSchedulerService.addCourseScheduler(scheduler);
				Group group = new Group();
				group.setCourse(scheduler);
				group.setActive(true);
				groupService.addGroup(group);
			}

			subjectService.updateSubject(subject);
			return "redirect:/teacher";
		}
	}

	@RequestMapping(value = "/saveSubject", method = RequestMethod.GET)
	public String saveSubjectold(@RequestParam(value = "subjectId", required = false) Integer subjectId,
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
			Group group = new Group();
			group.setCourse(scheduler);
			group.setActive(true);
			groupService.addGroup(group);
		}

		return "redirect:/teacher";
	}

	@RequestMapping(value = "/editSubject", method = RequestMethod.GET)
	public String editSubject(@RequestParam(value = "subjectId", required = false) Integer subjectId, Model model) {
		Subject subject = subjectId != null ? subjectService.getSubjectById(subjectId) : new Subject();

		if (subjectId != null) {

			List<CourseScheduler> courseSchedulerList = courseSchedulerService
					.getCourseScheduleresBySubjectId(subjectId);
			model.addAttribute("scheduler", courseSchedulerList.get(0));
		}
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("catList", categoryList);
		model.addAttribute("subject", subject);
		return "editSubject";
	}

	@RequestMapping(value = "/editBlock", method = RequestMethod.GET)
	public String editBlock(@RequestParam(value = "blockId", required = false) Integer blockId, Model model) {
		Block block = blockId != null ? blockService.getBlockById(blockId) : new Block();

		model.addAttribute("block", block);
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

	@RequestMapping(value = "/deleteBlock", method = RequestMethod.GET)
	public String deleteBlock(@RequestParam(value = "blockId", required = true) Integer blockId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId, Model model) {
		List<Topic> topics = topicService.getTopicsByBlockId(blockId);
		for (Topic topic : topics) {
			topicService.deleteTopic(topic);
		}
		Block block = blockService.getBlockById(blockId);
		blockService.deleteBlock(block);
		model.addAttribute("subjectId", subjectId);
		return "redirect:/teacherCourse";

	}

	@RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
	public String deleteSubject(@RequestParam(value = "subjectIds", required = true) String subjectIds, Model model) {

		for (String idInStr : subjectIds.split(",")) {
			
				Integer id = Integer.parseInt(idInStr);
				//try {
				Subject subject = subjectService.getSubjectById(id);
				List<Block> blocks = blockService.getBlocksBySubjectId(subject.getId());
				List<Topic> topics = topicService.getTopicsBySubjectId(subject.getId());
				List<CourseScheduler> cs = courseSchedulerService.getCourseScheduleresBySubjectId(subject.getId());

				for (Topic t : topics)
					topicService.deleteTopic(t);

				for (Block b : blocks)
					blockService.deleteBlock(b);

				for (CourseScheduler c : cs) {
					Group courseGroup = groupService.getGroupByScheduler(c.getId());
					Integer studentGroupNum = courseGroup.getGroupId();
					List<StudentGroup> sg = studentGroupService.getStudentGroupsByGroupNumber(studentGroupNum);
					for (StudentGroup ss : sg)
						studentGroupService.deleteStudentGroup(ss);
					groupService.deleteGroup(courseGroup);
					courseSchedulerService.deleteCourseScheduler(c);
				}

				subjectService.deleteSubject(subject);
		//	} catch (Exception e) {
			//	e.printStackTrace();
			//}
		}
		return "redirect:/teacher";
	}

	@RequestMapping(value = "/deleteCategories", method = RequestMethod.GET)
	public String deleteCategories(@RequestParam(value = "categoriesIds", required = true) String categoriesIds,
			Model model) {

		for (String idInStr : categoriesIds.split(",")) {
			//try {
				Integer id = Integer.parseInt(idInStr);
				Category category = categoryService.getCategoryById(id);
				categoryService.deleteCategory(category);
			//} catch (Exception e) {
			//}
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
