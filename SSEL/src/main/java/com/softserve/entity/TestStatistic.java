package com.softserve.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_statistic")
public class TestStatistic {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	 @ManyToOne (fetch = FetchType.LAZY)
	 @JoinColumn (name = "id_test")
	 private Test test;
//	@Column(name = "id_test")
//	private int testId;

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn (name = "id_user")
	 private User user;
//	@Column(name = "id_user")
//	private int userId;

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_group")
	 private Group group;
//	@Column(name = "id_group")
//	private int groupId;

	// @ManyToOne (fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_question")
	// private Question question;  АХТУНГ!!!
	@Column(name = "id_question")
	private int questionId;

	@Column(name = "user_result")
	private int userResult;

	@Column(name = "max_result")
	private int maxResult;

	@Column(name = "user_answers")
	private ArrayList<Integer> userAnswers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUserResult() {
		return userResult;
	}

	public void setUserResult(int userResult) {
		this.userResult = userResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public List<Integer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(ArrayList<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}




}
