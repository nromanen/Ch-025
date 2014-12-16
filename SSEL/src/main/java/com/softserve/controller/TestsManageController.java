package com.softserve.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Block;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.entity.Test;
import com.softserve.form.QuestionForm;
import com.softserve.service.BlockService;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;
import com.softserve.validator.QuestionFormValidator;
import com.softserve.validator.TestValidator;

@Controller
public class TestsManageController {
	@Autowired
	private TestService testService;

	@Autowired
	private BlockService blockService;

	@Autowired
	private TestValidator testValidator;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionFormValidator questionFormValidator;

	/**
	 * Handle tests list for subject
	 * @param subjectId unique subject identifier
	 * @param model data model for view
	 * @return logical view name
	 */
	@RequestMapping(value = "/tests", method = RequestMethod.GET)
	public String printTestList(@RequestParam(value = "blockId", required = true) Integer blockId,
								@RequestParam(value = "subjectId", required = true) Integer subjectId,
								Model model) {
		List<Test> tests = testService.getTestsByBlock(blockId);
		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		model.addAttribute("testList", tests);
		model.addAttribute("currentBlock", blockService.getBlockById(blockId));
		model.addAttribute("blocks", blocks);
		return "tests";
	}
	/**
	 * Binder for block
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
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
	/**
	 * Binder for tests
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void testInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Test.class, "test", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Test test = testService.getTestById(Integer.parseInt(id));
				setValue(test);
			}
		});
	}
	/**
	 * Binder for question
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void questionInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Question.class, "question", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Question question = questionService.getQuestionById(Integer.parseInt(id));
				setValue(question);
			}
		});
	}
	/**
	 * Handle edit/add test form
	 * @param subjectId unique subject indetifier
	 * @param testId unique test identifier. if missing - add test, else - edit existing test
	 * @param model data model for view
	 * @return logical name for view
	 */
	@RequestMapping(value = "/editTest", method = RequestMethod.GET)
	public String addTestRender(@RequestParam(value = "subjectId", required = true) Integer subjectId,
								@RequestParam(value = "testId", required = false) Integer testId,
								Model model) {
		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		Test test = (testId == null) ? new Test() : testService.getTestById(testId);
		model.addAttribute("test", test);
		model.addAttribute("blocks", blocks);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("op", (testId == null) ? true : false);
		return "editTest";
	}
	/**
	 * Validate and insert/update test
	 * @param test test form
	 * @param model data for view
	 * @param result result binder
	 * @return logical name of view
	 */
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public String processTestSubmit(@ModelAttribute("test") Test test,
									@RequestParam("op") Boolean op,
									Model model,
									BindingResult result) {
		testValidator.validate(test, result);
		Block block = blockService.getBlockById(test.getBlock().getId());
		Integer subjectId = block.getSubject().getId();
		Integer testId = test.getId();
		if (result.hasErrors()) {
			List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
			model.addAttribute("test", test);
			model.addAttribute("blocks", blocks);
			return "redirect:editTest?subjectId="+subjectId+"&testId="+testId;
		}
		test = (op) ? testService.addTest(test) : testService.updateTest(test);
		return "redirect:tests?blockId="+block.getId()+"&subjectId="+subjectId;
	}

	/**
	 * Edit/add question
	 * @param testId unique test identifier.
	 * @param questionId unique question identifier. If missing add, else - edit
	 * @param model data for view
	 * @return logical name for view
	 */
	@RequestMapping(value="/editQuestion", method = RequestMethod.GET)
	public String addQuestionRender(@RequestParam(value = "testId", required = true) Integer testId,
									@RequestParam(value = "questionId", required = false) Integer questionId,
									Model model) {
		Question question;
		List<Option> answers;
		QuestionForm questionForm = new QuestionForm();
		Test test = testService.getTestById(testId);
		if (questionId == null) {
			question = new Question();
			question.setTest(test);
			question.setMark(0.0);
			answers = new ArrayList<>();
			for(int i=0; i < 4; i++) {
				Option ans = new Option();
				ans.setIsCorrect(false);
				answers.add(ans);
			}
			questionForm.setQuestion(question);
			questionForm.setName("");
			questionForm.setAnswers(answers);
			model.addAttribute("op", true);
		} else {
			question = questionService.getQuestionById(questionId);
			questionForm.setQuestion(question);
			questionForm.setName(question.getQuestion().getValue());
			answers = question.getQuestion().getOptions();
			questionForm.setAnswers(answers);
			model.addAttribute("op", false);
		}
		model.addAttribute("questionForm", questionForm);
		model.addAttribute("testName", test.getName());
		return "editQuestion";
	}
	/**
	 * Validate and insert/update question
	 * @param form form with question and answers
	 * @param result binder for form
	 * @param model data for view
	 * @return logical name for view
	 */
	@RequestMapping(value="/saveQuestion", method = RequestMethod.POST)
	public String processSubmitaddQuestion(@ModelAttribute QuestionForm form,
										   @RequestParam(value = "op", required = true) Boolean op,
											BindingResult result,
											Model model) {
		int testId = form.getQuestion().getTest().getId();
		int questionId = form.getQuestion().getId();
		questionFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addAttribute("questionForm", form);
			model.addAttribute("testName", form.getQuestion().getTest().getName());
			return "editQuestion?testId="+testId+"&questionId="+questionId;
		}
		Test test = testService.getTestById(testId);
		form.getQuestion().setTest(test);
		Question question = form.getQuestion();
		QuestionText questionText = new QuestionText();
		questionText.setOptions(form.getAnswers());
		questionText.setValue(form.getName());
		question.setQuestionText(questionText);
		question.setMark(question.getMark());
		question = (op) ? questionService.addQuestion(question) : questionService.updateQuestion(question);
		return "redirect:testInfo?testId="+testId;
	}
	/**
	 * Render test info page
	 * @param testId unique test identifier
	 * @param model data for view
	 * @return logical name of view
	 */
	@RequestMapping(value = "/testInfo", method = RequestMethod.GET)
	public String printTestInfo(@RequestParam(value = "testId", required = true) Integer testId, Model model) {
		Test test = testService.getTestById(testId);
		List<Question> questions = questionService.getQuestionsByTestId(testId);
		List<QuestionText> texts = new ArrayList<>();
		if (questions != null) {
			for (Question question : questions) {
				texts.add(question.getQuestion());
			}
		}
		model.addAttribute("texts", texts);
		model.addAttribute("test", test);
		model.addAttribute("questions", questions);
		return "testInfo";
	}
	/**
	 * Perform test delete
	 * @param testId unique test identifier
	 * @param subjectId unique subject identifier
	 * @param blockId unique block identifier
	 * @return logical name of view
	 */
	@RequestMapping(value = "/deleteTest", method = RequestMethod.POST)
	public String deleteTest(@RequestParam(value = "testId", required = true) Integer testId,
							 @RequestParam(value = "subjectId", required = true) Integer subjectId,
							 @RequestParam(value = "blockId", required = true) Integer blockId) {
		testService.deleteTest(testId);
		return "redirect:tests?blockId="+blockId+"&subjectId="+subjectId;
	}
	/**
	 * Perform question delete
	 * @param questionId unique question identifier
	 * @param testId unique test identifier
	 * @return logical name of view
	 */
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public String deleteQuestion(@RequestParam(value = "questionId", required = true) Integer questionId,
								@RequestParam(value = "testId", required = true) Integer testId) {
		Question question = new Question();
		question.setId(questionId);
		questionService.deleteQuestion(question);
		return "redirect:testInfo?testId="+testId;
	}
	
}