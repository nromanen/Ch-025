package com.softserve.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Answer
 *
 */
@Entity
@Table(name = "answers")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_answer")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_question", nullable = true)
	private Question question;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "right")
	private boolean isRight;
	
	@Column (name = "mark")
	private double mark;
	
	@Column(name = "deleted")
	private int isDeleted;
	
	
	public Answer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}
	
}
