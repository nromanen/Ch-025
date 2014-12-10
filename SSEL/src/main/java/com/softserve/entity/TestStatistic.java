package com.softserve.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_statistic")
public class TestStatistic {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// @ManyToOne (fetch = FetchType.LAZY)
	// @JoinColumn (name = "id_test", nullable = false)
	@Column(name = "id_test")
	private int testId;

	// @ManyToOne (fetch = FetchType.LAZY)
	// @JoinColumn (name = "id_user", nullable = false)
	@Column(name = "id_user")
	private int userId;

	// @ManyToOne (fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_group")
	@Column(name = "id_group")
	private int groupId;

	// @OneToOne (fetch = FetchType.LAZY)
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

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public ArrayList<Integer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(ArrayList<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}

}
