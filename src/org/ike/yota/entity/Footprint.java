package org.ike.yota.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Footprint entity. @author MyEclipse Persistence Tools
 */

public class Footprint implements java.io.Serializable {

	// Fields

	private Integer footprintId;
	private User user;
	private Activities activities;
	private String footprintMood;
	private Timestamp footprintTime;
	private Set photos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Footprint() {
	}

	/** full constructor */
	public Footprint(User user, Activities activities, String footprintMood,
			Timestamp footprintTime, Set photos) {
		this.user = user;
		this.activities = activities;
		this.footprintMood = footprintMood;
		this.footprintTime = footprintTime;
		this.photos = photos;
	}

	// Property accessors

	public Integer getFootprintId() {
		return this.footprintId;
	}

	public void setFootprintId(Integer footprintId) {
		this.footprintId = footprintId;
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

	public String getFootprintMood() {
		return this.footprintMood;
	}

	public void setFootprintMood(String footprintMood) {
		this.footprintMood = footprintMood;
	}

	public Timestamp getFootprintTime() {
		return this.footprintTime;
	}

	public void setFootprintTime(Timestamp footprintTime) {
		this.footprintTime = footprintTime;
	}

	public Set getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set photos) {
		this.photos = photos;
	}

}