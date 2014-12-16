package com.softserve.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.entity.Test;
import com.softserve.service.GroupService;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;
import com.softserve.service.UserService;

public class QuestionsCreator {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	

	public List<Question> createQuestions(Test test) {
		Question question01 = new Question();
		question01.setTest(test);
		question01.setMark(5);
		question01.setDeleted(false);
	
			QuestionText questionText01 = new QuestionText();
			questionText01.setValue("Which is true?");
			
				Option option01 = new Option();
				option01.setValue("X extends Y - is correct if and only if X is a class and Y is an interface.");
				option01.setIsCorrect(false);
				
				Option option02 = new Option();
				option02.setValue("X extends Y - is correct if and only if X is an interface and Y is a class.");
				option02.setIsCorrect(false);
				
				Option option03 = new Option();
				option03.setValue("X extends Y - is correct if X and Y are either both classes or both interfaces.");
				option03.setIsCorrect(true);
				
				Option option04 = new Option();
				option04.setValue("X extends Y - is correct for all combinations of X and Y being classes and/or interfaces.");
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
		question02.setMark(4);
		question02.setDeleted(false);
	
			QuestionText questionText02 = new QuestionText();
			questionText02.setValue("Which method names follow the JavaBeans standard?");
			
				Option option05 = new Option();
				option05.setValue("addSize");
				option05.setIsCorrect(false);
				
				Option option06 = new Option();
				option06.setValue("getCust");
				option06.setIsCorrect(true);
				
				Option option07 = new Option();
				option07.setValue("deleteRep");
				option07.setIsCorrect(false);
				
				Option option08 = new Option();
				option08.setValue("isColorado");
				option08.setIsCorrect(true);
				
				Option option09 = new Option();
				option09.setValue("putDimensions");
				option09.setIsCorrect(false);
			
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
		question03.setMark(3);
		question03.setDeleted(false);
	
			QuestionText questionText03 = new QuestionText();
			questionText03.setValue("Which statement(s) are true?");
			
				Option option10 = new Option();
				option10.setValue("Cohesion is the OO principle most closely associated with hiding implementation details.");
				option10.setIsCorrect(true);
				
				Option option11 = new Option();
				option11.setValue("Cohesion is the OO principle most closely associated with making sure that classes know about other classes only through their APIs.");
				option11.setIsCorrect(false);
				
				Option option12 = new Option();
				option12.setValue("Cohesion is the OO principle most closely associated with making sure that a class is designed with a single, well-focused purpose.");
				option12.setIsCorrect(false);
				
				Option option13 = new Option();
				option13.setValue("Cohesion is the OO.");
				option13.setIsCorrect(false);
			
				ArrayList<Option> optionList03 = new ArrayList<Option>();
					optionList03.add(option10);
					optionList03.add(option11);
					optionList03.add(option12);
					optionList03.add(option13);
					
			questionText03.setOptions(optionList03);
		question03.setQuestionText(questionText03);
		
		
		

		// NEW QUESTION
		
		Question question04 = new Question();
		question04.setTest(test);
		question04.setMark(4);
		question04.setDeleted(false);
	
			QuestionText questionText04 = new QuestionText();
			questionText04.setValue("Which of the following statements are correct?");
			
				Option option14 = new Option();
				option14.setValue("An ArrayList offers a resizable array, which is easily managed using the methods it provides. You can add and remove elements from an ArrayList.");
				option14.setIsCorrect(true);
				
				Option option15 = new Option();
				option15.setValue("Values stored by an ArrayList can be modified.");
				option15.setIsCorrect(true);
				
				Option option16 = new Option();
				option16.setValue("An ArrayList requires you to specify the total number of elements before you can store any elements in it.");
				option16.setIsCorrect(false);
				
				Option option17 = new Option();
				option17.setValue("An ArrayList can store primitive types.");
				option17.setIsCorrect(false);
			
				ArrayList<Option> optionList04 = new ArrayList<Option>();
					optionList04.add(option14);
					optionList04.add(option15);
					optionList04.add(option16);
					optionList04.add(option17);
					
			questionText04.setOptions(optionList04);
		question04.setQuestionText(questionText04);
		

		// NEW QUESTION
		
		Question question05 = new Question();
		question05.setTest(test);
		question05.setMark(4);
		question05.setDeleted(false);
	
			QuestionText questionText05 = new QuestionText();
			questionText05.setValue("Which of the following is illegal?");
			
				Option option18 = new Option();
				option18.setValue("int i = 32");
				option18.setIsCorrect(false);
				
				Option option19 = new Option();
				option19.setValue("float f = 45.0");
				option19.setIsCorrect(true);
				
				Option option20 = new Option();
				option20.setValue("double d = 45.0");
				option20.setIsCorrect(false);
				
				Option option21 = new Option();
				option21.setValue("boolean b = 1");
				option21.setIsCorrect(true);
			
				ArrayList<Option> optionList05 = new ArrayList<Option>();
					optionList05.add(option18);
					optionList05.add(option19);
					optionList05.add(option20);
					optionList05.add(option21);
					
			questionText05.setOptions(optionList05);
		question05.setQuestionText(questionText05);
		

		
		ArrayList<Question> qList = new ArrayList<Question>();
		qList.add(question01);
		qList.add(question02);
		qList.add(question03);
		qList.add(question04);
		qList.add(question05);
		
		return qList;
		
	}
	
	
	
	
	
}
