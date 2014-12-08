package com.softserve.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Group Encapsulate course groups
 * information
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_course_scheduler")
	private CourseScheduler course;

	@Column(name = "isActive")
	private boolean isActive;

	@Column(name = "deleted")
	private boolean isDeleted;

	public Group() {

	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CourseScheduler getCourse() {
		return course;
	}

	public void setCourse(CourseScheduler course) {
		this.course = course;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
