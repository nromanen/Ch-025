package com.softserve.controller;

import java.util.Set;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.service.RoleService;
import com.softserve.service.UserService;
import com.softserve.entity.Block;
import com.softserve.entity.Category;
import com.softserve.entity.Subject;
import com.softserve.entity.Topic;
import com.softserve.service.BlockService;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;
import com.softserve.service.TopicService;

/**
 * Handles requests for the application home page.
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

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(Model model) {
		Set<Subject> subjectList = subjectService.getAllSubjects();
		model.addAttribute("subjectList", subjectList);
		return "teacher";
	}

	@RequestMapping(value = "/teacherCourse", method = RequestMethod.GET)
	public String teacherCourse(
			@RequestParam(value = "subjectId", required = false) Integer subjectId,
			Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();

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
	public String editTopic(
			@RequestParam(value = "topicId", required = false) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			Model model) {
		if (topicId != null) {
			Topic topic = topicService.getTopicById(topicId);
			model.addAttribute("topic", topic);
		}

		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		model.addAttribute("blockList", blocks);

		return "editTopic";
	}

	@RequestMapping(value = "/editSubject", method = RequestMethod.GET)
	public String editSubject(
			@RequestParam(value = "subjectId", required = false) Integer subjectId,
			Model model) {
		if (subjectId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			model.addAttribute("subject", subject);
		}

		Set<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("categoryList", categoryList);

		return "editSubject";
	}

	@RequestMapping(value = "/editBlock", method = RequestMethod.GET)
	public String editBlock(
			@RequestParam(value = "blockId", required = false) Integer blockId,
			Model model) {
		if (blockId != null) {
			Block block = blockService.getBlockById(blockId);
			model.addAttribute("block", block);
		}

		Set<Subject> subjectList = subjectService.getAllSubjects();
		model.addAttribute("subjectList", subjectList);

		return "editBlock";
	}

	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public String editCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Model model) {
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			model.addAttribute("category", category);
		}

		return "editCategory";
	}

	@RequestMapping(value = "/saveTopic", method = RequestMethod.GET)
	public String saveTopic(
			@RequestParam(value = "topicId", required = false) Integer topicId,
			@RequestParam(value = "blockId", required = true) Integer blockId,
			@RequestParam(value = "topicAlive", required = true) boolean topicAlive,
			@RequestParam(value = "topicContent", required = true) String topicContent,
			@RequestParam(value = "topicName", required = true) String topicName,
			@RequestParam(value = "topicOrder", required = true) Integer topicOrder,
			Model model) {
		if (topicId != null) {
			Topic topic = topicService.getTopicById(topicId);
			topic.setBlock(blockService.getBlockById(blockId));
			topic.setAlive(topicAlive);
			topic.setContent(topicContent);
			topic.setName(topicName);
			topic.setOrder(topicOrder);
			topicService.updateTopic(topic);
		} else {
			Topic topic = new Topic();
			topic.setBlock(blockService.getBlockById(blockId));
			topic.setAlive(topicAlive);
			topic.setContent(topicContent);
			topic.setName(topicName);
			topic.setOrder(topicOrder);
			topicService.addTopic(topic);
		}

		return "teacher";
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.GET)
	public String saveCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "categoryName", required = true) String categoryName,
			Model model) {
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			category.setName(categoryName);
			categoryService.updateCategory(category);
		} else {
			Category category = new Category();
			category.setName(categoryName);
			categoryService.addCategory(category);
		}

		return "teacher";
	}

	@RequestMapping(value = "/saveSubject", method = RequestMethod.GET)
	public String saveSubject(
			@RequestParam(value = "subjectId", required = false) Integer subjectId,
			@RequestParam(value = "subjectName", required = true) String subjectName,
			@RequestParam(value = "subjectDescription", required = true) String subjectDescription,
			@RequestParam(value = "subjectCategoryId", required = true) Integer subjectCategoryId,
			Model model) {
		if (subjectId != null) {
			Subject subject = subjectService.getSubjectById(subjectId);
			Category category = categoryService
					.getCategoryById(subjectCategoryId);
			subject.setName(subjectName);
			subject.setDescription(subjectDescription);
			subject.setCategory(category);
			subjectService.updateSubject(subject);
		} else {
			Subject subject = new Subject();
			Category category = categoryService
					.getCategoryById(subjectCategoryId);
			subject.setName(subjectName);
			subject.setDescription(subjectDescription);
			subject.setCategory(category);
			subjectService.addSubject(subject);
		}

		return "teacher";
	}

	@RequestMapping(value = "/enableTopic", method = RequestMethod.GET)
	public String enableTopic(
			@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "enable", required = true) boolean enable,
			Model model) {
		Topic topic = topicService.getTopicById(topicId);
		if (enable) {
			topic.setAlive(true);
		} else {
			topic.setAlive(false);
		}
		topicService.updateTopic(topic);
		//model.addAttribute("subjectId", subjectId);
		return "teacher";
	}

	@RequestMapping(value = "/deleteTopic", method = RequestMethod.GET)
	public String deleteTopic(
			@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			Model model) {
		Topic topic = topicService.getTopicById(topicId);
		topicService.deleteTopic(topic);
		//model.addAttribute("subjectId", subjectId);
		return "teacher";
		
	}

	@RequestMapping(value = "/changeTopicOrder", method = RequestMethod.GET)
	public String changeTopicOrder(
			@RequestParam(value = "topicId", required = true) Integer topicId,
			@RequestParam(value = "subjectId", required = true) Integer subjectId,
			@RequestParam(value = "updown", required = true) String updown,
			Model model) {
		Topic topic = topicService.getTopicById(topicId);
		if (updown == "up") {
			topicService.changeOrderUp(topic);
		} else if (updown == "down") {
			topicService.changeOrderDown(topic);
		}
		//model.addAttribute("subjectId", subjectId);
		return "teacher";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "login";
	}

}
