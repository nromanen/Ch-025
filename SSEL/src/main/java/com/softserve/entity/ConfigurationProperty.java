package com.softserve.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Application config entity
 * @author Анатолій
 *
 */
@Entity
@Table (name="config")
public class ConfigurationProperty {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "propertyKey")
	private String key;

	@Column (name = "value" )
	private String value;

	public ConfigurationProperty() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ConfigurationProperty [id=" + id + ", key=" + key + ", value="
				+ value + "]";
	}
}
