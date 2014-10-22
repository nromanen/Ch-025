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

	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

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
	public String teacher(
			@RequestParam(value = "courseId", required = false) Integer courseId, Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		try {
		List<Topic> topics = topicService.getTopicsBySubjectId(courseId);
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		
		model.addAttribute("topicList", topics);
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		} catch(NullPointerException e) {
			return "teacher";
		}
		return "teacher";
	}
	
	@RequestMapping(value = "/teacherCourse", method = RequestMethod.GET)
	public String teacherCourse(
			@RequestParam(value = "courseId", required = false) Integer courseId, Model model) {
		Set<Subject> subjects = subjectService.getAllSubjects();
		Set<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("subList", subjects);
		model.addAttribute("catList", categories);
		try {
		List<Topic> topics = topicService.getTopicsBySubjectId(courseId);
		List<Block> blocks = blockService.getBlocksBySubjectId(courseId);
		Subject subject = subjectService.getSubjectById(courseId);
		
		model.addAttribute("topicList", topics);
		model.addAttribute("blockList", blocks);
		model.addAttribute("subject", subject);
		} catch(NullPointerException e) {
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "login";
	}

}
