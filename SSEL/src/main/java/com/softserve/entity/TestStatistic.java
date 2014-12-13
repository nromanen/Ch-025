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

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn (name = "id_test")
	 private Test test;

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn (name = "id_user")
	 private User user;

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_group")
	 private Group group;

	 @ManyToOne (fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_question")
	 private Question question; 
//	@Column(name = "id_question")
//	private int questionId;

	@Column(name = "user_result")
	private float userResult;

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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public float getUserResult() {
		return userResult;
	}

	public void setUserResult(float userResult) {
		this.userResult = userResult;
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
