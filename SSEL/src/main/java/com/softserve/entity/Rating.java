package com.softserve.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rating
 *	Encapsulate rating information
 */
@Entity
@Table(name = "rating")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;
	
	@Column(name = "mark")
	private double mark;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_block")
	private Block block;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_group")
	private Group group;
	

	public Rating() {
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
   
}
