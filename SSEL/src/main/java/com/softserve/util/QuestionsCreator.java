package com.softserve.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.entity.Test;
import com.softserve.entity.TestStatistic;
import com.softserve.service.GroupService;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;
import com.softserve.service.TestStatisticService;
import com.softserve.service.UserService;

public class QuestionsCreator {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private TestStatisticService testStatisticService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	

	public List<Question> createQuestions(Test test) {
		Question question01 = new Question();
		question01.setTest(test);
		question01.setMark(1);
		question01.setDeleted(false);
	
			QuestionText questionText01 = new QuestionText();
			questionText01.setValue("Question 01");
			
				Option option01 = new Option();
				option01.setValue("Incorrect 01");
				option01.setIsCorrect(false);
				
				Option option02 = new Option();
				option02.setValue("Incorrect 02");
				option02.setIsCorrect(false);
				
				Option option03 = new Option();
				option03.setValue("Correct 03");
				option03.setIsCorrect(true);
				
				Option option04 = new Option();
				option04.setValue("Incorrect 04");
				option04.setIsCorrect(false);
			
				ArrayList<Option> optionList01 = new ArrayList<Option>();
					optionList01.add(option01);
					optionList01.add(option02);
					optionList01.add(option03);
					optionList01.add(option04);
					
			questionText01.setOptions(optionList01);
		question01.setQuestionText(questionText01);
		
		
		// NEW QUESTION
		
		Question question02 = new Question();
		question02.setTest(test);
		question02.setMark(2);
		question02.setDeleted(false);
	
			QuestionText questionText02 = new QuestionText();
			questionText02.setValue("Question 02");
			
				Option option05 = new Option();
				option05.setValue("Incorrect 05");
				option05.setIsCorrect(false);
				
				Option option06 = new Option();
				option06.setValue("Incorrect 06");
				option06.setIsCorrect(false);
				
				Option option07 = new Option();
				option07.setValue("Correct 07");
				option07.setIsCorrect(true);
				
				Option option08 = new Option();
				option08.setValue("Incorrect 08");
				option08.setIsCorrect(false);
				
				Option option09 = new Option();
				option09.setValue("Correct 09");
				option09.setIsCorrect(true);
			
				ArrayList<Option> optionList02 = new ArrayList<Option>();
					optionList02.add(option05);
					optionList02.add(option06);
					optionList02.add(option07);
					optionList02.add(option08);
					optionList02.add(option09);
					
			questionText02.setOptions(optionList02);
		question02.setQuestionText(questionText02);

		
		
		// NEW QUESTION
		
		Question question03 = new Question();
		question03.setTest(test);
		question03.setMark(2);
		question03.setDeleted(false);
	
			QuestionText questionText03 = new QuestionText();
			questionText03.setValue("Question 03");
			
				Option option10 = new Option();
				option10.setValue("Correct 10");
				option10.setIsCorrect(true);
				
				Option option11 = new Option();
				option11.setValue("Incorrect 11");
				option11.setIsCorrect(false);
				
				Option option12 = new Option();
				option12.setValue("Correct 12");
				option12.setIsCorrect(true);
				
				Option option13 = new Option();
				option13.setValue("Incorrect 13");
				option13.setIsCorrect(false);
			
				ArrayList<Option> optionList03 = new ArrayList<Option>();
					optionList03.add(option10);
					optionList03.add(option11);
					optionList03.add(option12);
					optionList03.add(option13);
					
			questionText03.setOptions(optionList03);
		question03.setQuestionText(questionText03);
		
		ArrayList<Question> qList = new ArrayList<Question>();
		qList.add(question01);
		qList.add(question02);
		qList.add(question03);
		
		return qList;
		
	}
	
	public void createTestStatistic() {
		
		//User 1 Question 1
		
		TestStatistic testStatistic01 = new TestStatistic();
		testStatistic01.setTest(testService.getTestById(1));
		testStatistic01.setUser(userService.getUserById(1));
		testStatistic01.setGroup(groupService.getGroupById(3));
		testStatistic01.setQuestion(questionService.getQuestionById(1));
		testStatistic01.setUserResult(1);
		testStatistic01.setMaxResult(1);
		
		ArrayList<Integer> userAnswers01 = new ArrayList<Integer>();
			userAnswers01.add(0);
			userAnswers01.add(0);
			userAnswers01.add(1);
			userAnswers01.add(0);
		
		testStatistic01.setUserAnswers(userAnswers01);
		
		//User 1 Question 2
		
		TestStatistic testStatistic02 = new TestStatistic();
		testStatistic02.setTest(testService.getTestById(1));
		testStatistic02.setUser(userService.getUserById(1));
		testStatistic02.setGroup(groupService.getGroupById(3));
		testStatistic02.setQuestion(questionService.getQuestionById(2));
		testStatistic02.setUserResult(1);
		testStatistic02.setMaxResult(2);
		
		ArrayList<Integer> userAnswers02 = new ArrayList<Integer>();
			userAnswers02.add(0);
			userAnswers02.add(0);
			userAnswers02.add(1);
			userAnswers02.add(1);
			userAnswers02.add(0);
		
		testStatistic02.setUserAnswers(userAnswers02);		
		
		//User 1 Question 3
		
		TestStatistic testStatistic03 = new TestStatistic();
		testStatistic03.setTest(testService.getTestById(1));
		testStatistic03.setUser(userService.getUserById(1));
		testStatistic03.setGroup(groupService.getGroupById(3));
		testStatistic03.setQuestion(questionService.getQuestionById(3));
		testStatistic03.setUserResult(0.75f);
		testStatistic03.setMaxResult(2);
		
		ArrayList<Integer> userAnswers03 = new ArrayList<Integer>();
			userAnswers03.add(1);
			userAnswers03.add(1);
			userAnswers03.add(1);
			userAnswers03.add(0);
		
		testStatistic03.setUserAnswers(userAnswers03);		
		
		testStatisticService.addTestStatistic(testStatistic01);
		testStatisticService.addTestStatistic(testStatistic02);
		testStatisticService.addTestStatistic(testStatistic03);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
