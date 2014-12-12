package com.softserve.entity;

import java.io.StringReader;
import java.io.StringWriter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Entity implementation class for Entity: Question
 * @author Ivan
 */
@Entity
@Table(name = "questions")
public class Question {

	private static JAXBContext jaxbContext;
	private static Marshaller jaxbMarshaller;
	private static Unmarshaller jaxbUnmarshaller;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_Test", nullable = false)
	private Test test;

	@Column(name = "questionText", columnDefinition = "BLOB")
	private String questionText;

	@Column(name = "mark")
	private double mark;

	@Column(name = "deleted")
	private boolean isDeleted;

	/**
	 *
	 */
	public Question() {
	}

	public int getId() {
		return id;
	}

	public Test getTest() {
		return test;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getQuestionText() {
		//return questionText;
		StringBuilder sb = new StringBuilder();
	    for (int i=0; i<questionText.length(); i++)
	        switch (questionText.charAt(i)){
	            case '\n': sb.append("\\n"); break;
	            case '\t': sb.append("\\t"); break;
	            default: sb.append(questionText.charAt(i));
	        }
	    return sb.toString();
	}

	public QuestionText getQuestion() {
		QuestionText questionText = new QuestionText();
		try {
			jaxbContext = JAXBContext
					.newInstance(QuestionText.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			questionText = (QuestionText) jaxbUnmarshaller
					.unmarshal(new StringReader(this.questionText));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return questionText;

	}

	public void setQuestionText(QuestionText questionText) {
		try {
			StringWriter stringWriter = new StringWriter();
			jaxbContext = JAXBContext
					.newInstance(QuestionText.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(questionText, stringWriter);
			this.questionText = stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

//	public void setQuestionText(String questionText) {
//		this.questionText = questionText;
//	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", test=" + test + ", questionText="
				+ questionText + ", mark=" + mark + ", isDeleted=" + isDeleted
				+ "]";
	}
}
