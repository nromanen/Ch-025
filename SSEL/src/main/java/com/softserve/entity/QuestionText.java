package com.softserve.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "question")
@XmlType(propOrder = { "value", "options" })
public class QuestionText {
	private String value;
	private List<Option> options;

	/**
	 *
	 */
	public QuestionText() {
	}

	public String getValue() {
		return value;
	}

	public List<Option> getOptions() {
		return options;
	}

	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement
	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "QuestionText [value=" + value + ", options=" + options + "]";
	}

}
