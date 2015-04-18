package org.ike.yota.entity;

/**
 * Attend entity. @author MyEclipse Persistence Tools
 */

public class Attend implements java.io.Serializable {

	// Fields

	private Integer attendId;
	private User user;
	private Activities activities;

	// Constructors

	/** default constructor */
	public Attend() {
	}

	/** full constructor */
	public Attend(User user, Activities activities) {
		this.user = user;
		this.activities = activities;
	}

	// Property accessors

	public Integer getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Integer attendId) {
		this.attendId = attendId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activities getActivities() {
		return this.activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

}