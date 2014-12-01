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

import com.softserve.entity.Answer;
import com.softserve.entity.Block;
import com.softserve.entity.Question;
import com.softserve.entity.Test;
import com.softserve.form.QuestionForm;
import com.softserve.service.AnswerService;
import com.softserve.service.BlockService;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;
import com.softserve.validator.TestValidator;

@Controller
public class AddTestController {
	@Autowired
	private TestService testService;

	@Autowired
	private BlockService blockService;
	
	@Autowired
	private TestValidator testValidator;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	
	@RequestMapping(value = "/tests", method = RequestMethod.GET)
	public String printTestList(@RequestParam(value = "subjectId", required=true) Integer subjectId, Model model) {
		List<Test> tests = testService.getTestBySubject(subjectId);
		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		model.addAttribute("testList", tests);
		model.addAttribute("blocks", blocks);
		return "tests";
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
	public void testInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Test.class, "test", new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Test test = testService.getTestById(Integer.parseInt(id));
				setValue(test);
			}
		});
	}
	
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
	
	@RequestMapping(value = "/editTest", method = RequestMethod.GET)
	public String addTestRender(@RequestParam(value = "subjectId", required = true) Integer subjectId,
								@RequestParam(value = "testId", required = false) Integer testId,
								Model model) {
		List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
		Test test = (testId == null) ? new Test() : testService.getTestById(testId);
		model.addAttribute("test", test);
		model.addAttribute("blocks", blocks);
		model.addAttribute("subjectId", subjectId);
		return "editTest";
	}
	
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public String processTestSubmit(@ModelAttribute("test")Test newTest,
			Model model, BindingResult result) {
		testValidator.validate(newTest, result);
		Integer subjectId = newTest.getBlock().getSubject().getId();
		Integer testId = newTest.getId();
		if (result.hasErrors()) {
			System.out.println("Has errors");
			List<Block> blocks = blockService.getBlocksBySubjectId(subjectId);
			model.addAttribute("test", newTest);
			model.addAttribute("blocks", blocks);
			model.addAttribute("error", "has errors");
			return "redirect:editTest?subjectId="+subjectId+"&testId="+testId;
		}
		testService.updateTest(newTest);
		return "redirect:tests?subjectId="+subjectId;
	}
	
	
	@RequestMapping(value="/editQuestion", method = RequestMethod.GET)
	public String addQuestionRender(@RequestParam(value = "testId", required = true) Integer testId,
									@RequestParam(value = "questionId", required = false) Integer questionId,
									Model model) {
		QuestionForm testForm = new QuestionForm();
		Question question;
		List<Answer> answers;
		Test test = testService.getTestById(testId);
		if (questionId == null) {
			question = new Question();
			question.setQuestion("Input question here");
			question.setTest(test);
			question.setAnswersCount(1);
			answers = new ArrayList<>();
			for(int i=0; i < 4; i++) {
				Answer ans = new Answer();
				ans.setQuestion(question);
				ans.setAnswer("Bla bla");
				ans.setIsDeleted(false);
				ans.setIsRight(false);
				answers.add(ans);
			}
			testForm.setQuestion(question);
			testForm.setAnswers(answers);
			testForm.setTestId(testId); 
		} else {
			question = questionService.getQuestionById(questionId);
			answers = answerService.getAnswersByQuestion(questionId);
			testForm.setAnswers(answers);
			testForm.setQuestion(question);
		}
		model.addAttribute("questionForm", testForm);
		model.addAttribute("testName", test.getName());
		return "editQuestion";		
	}
	
	@RequestMapping(value="/saveQuestion", method = RequestMethod.POST)
	public String processSubmitaddQuestion(@ModelAttribute QuestionForm form, BindingResult result,Model model) {
		int testId = form.getQuestion().getTest().getId();
		int questionId = form.getQuestion().getId();
		if (result.hasErrors()) {
			return "editQuestion?testId="+testId+"&questionId="+questionId;
		}
		Test test = testService.getTestById(form.getTestId()); 
		form.getQuestion().setTest(test);
		Question question = questionService.addQuestion(form.getQuestion());
		for(Answer answer : form.getAnswers()) {
			double answerMark = question.getMark()/question.getAnswersCount();
			answer.setQuestion(question);
			answer.setMark(answerMark);
			answerService.addAnswer(answer);
		}
		return "redirect:testInfo?testId="+testId;		
	}

	@RequestMapping(value = "/testInfo", method = RequestMethod.GET)
	public String printTestInfo(@RequestParam(value = "testId", required = true) Integer testId, Model model) {
		Test test = testService.getTestById(testId);
		List<Question> questions = questionService.getAllQuestionsByTest(testId);
		model.addAttribute("test", test);
		model.addAttribute("questions", questions);
		return "testInfo";
	}
}
