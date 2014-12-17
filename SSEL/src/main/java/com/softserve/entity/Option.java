package com.softserve.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ivan
 */
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

	public boolean getIsCorrect() {
		return isCorrect;
	}

	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Option [value=" + value + ", isCorrect=" + isCorrect + "]";
	}

}
