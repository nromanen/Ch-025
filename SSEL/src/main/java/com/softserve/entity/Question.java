package com.softserve.entity;

import java.io.StringReader;
import java.io.StringWriter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_Test")
	private int test;

	@Column(name = "questionText", columnDefinition = "BLOB")
	private String questionText;

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

	public int getTest() {
		return test;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public String getQuestionText() {
		return questionText;
	}

	public QuestionText getQuestion() {
		QuestionText questionText = new QuestionText();
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(QuestionText.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
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
			JAXBContext jaxbContext = JAXBContext
					.newInstance(QuestionText.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(questionText, stringWriter);
			this.questionText = stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
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
				+ questionText + "]";
	}

}
