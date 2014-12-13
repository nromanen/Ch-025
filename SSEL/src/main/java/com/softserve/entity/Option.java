package com.softserve.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Option {
	private String value;
	private boolean isCorrect;
	/**
	 *
	 */
	public Option() {

	}

	public String getValue() {
		return value;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}

	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Option [value=" + value + ", isCorrect=" + isCorrect + "]";
	}


}
