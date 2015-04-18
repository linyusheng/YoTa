package org.ike.yota.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Activities entity. @author MyEclipse Persistence Tools
 */

public class Activities implements java.io.Serializable {

	// Fields

	private Integer activitiesId;
	private ActivitiesType activitiesType;
	private User user;
	private String activitiesName;
	private String activitiesCity;
	private String activitiesPlace;
	private String activitiesPoster;
	private String activitiesDescription;
	private Integer activitiesNumber;
	private Integer activitiesLevel;
	private Timestamp activitiesDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer activitiesState;
	private Float aspectRatio;
	private Short isHot;
	private Set attends = new HashSet(0);
	private Set footprints = new HashSet(0);

	// Constructors

	/** default constructor */
	public Activities() {
	}

	/** full constructor */
	public Activities(ActivitiesType activitiesType, User user,
			String activitiesName, String activitiesCity,
			String activitiesPlace, String activitiesPoster,
			String activitiesDescription, Integer activitiesNumber,
			Integer activitiesLevel, Timestamp activitiesDate,
			Timestamp startTime, Timestamp endTime, Integer activitiesState,
			Float aspectRatio, Short isHot, Set attends, Set footprints) {
		this.activitiesType = activitiesType;
		this.user = user;
		this.activitiesName = activitiesName;
		this.activitiesCity = activitiesCity;
		this.activitiesPlace = activitiesPlace;
		this.activitiesPoster = activitiesPoster;
		this.activitiesDescription = activitiesDescription;
		this.activitiesNumber = activitiesNumber;
		this.activitiesLevel = activitiesLevel;
		this.activitiesDate = activitiesDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activitiesState = activitiesState;
		this.aspectRatio = aspectRatio;
		this.isHot = isHot;
		this.attends = attends;
		this.footprints = footprints;
	}

	// Property accessors

	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	public ActivitiesType getActivitiesType() {
		return this.activitiesType;
	}

	public void setActivitiesType(ActivitiesType activitiesType) {
		this.activitiesType = activitiesType;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActivitiesName() {
		return this.activitiesName;
	}

	public void setActivitiesName(String activitiesName) {
		this.activitiesName = activitiesName;
	}

	public String getActivitiesCity() {
		return this.activitiesCity;
	}

	public void setActivitiesCity(String activitiesCity) {
		this.activitiesCity = activitiesCity;
	}

	public String getActivitiesPlace() {
		return this.activitiesPlace;
	}

	public void setActivitiesPlace(String activitiesPlace) {
		this.activitiesPlace = activitiesPlace;
	}

	public String getActivitiesPoster() {
		return this.activitiesPoster;
	}

	public void setActivitiesPoster(String activitiesPoster) {
		this.activitiesPoster = activitiesPoster;
	}

	public String getActivitiesDescription() {
		return this.activitiesDescription;
	}

	public void setActivitiesDescription(String activitiesDescription) {
		this.activitiesDescription = activitiesDescription;
	}

	public Integer getActivitiesNumber() {
		return this.activitiesNumber;
	}

	public void setActivitiesNumber(Integer activitiesNumber) {
		this.activitiesNumber = activitiesNumber;
	}

	public Integer getActivitiesLevel() {
		return this.activitiesLevel;
	}

	public void setActivitiesLevel(Integer activitiesLevel) {
		this.activitiesLevel = activitiesLevel;
	}

	public Timestamp getActivitiesDate() {
		return this.activitiesDate;
	}

	public void setActivitiesDate(Timestamp activitiesDate) {
		this.activitiesDate = activitiesDate;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getActivitiesState() {
		return this.activitiesState;
	}

	public void setActivitiesState(Integer activitiesState) {
		this.activitiesState = activitiesState;
	}

	public Float getAspectRatio() {
		return this.aspectRatio;
	}

	public void setAspectRatio(Float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public Short getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Short isHot) {
		this.isHot = isHot;
	}

	public Set getAttends() {
		return this.attends;
	}

	public void setAttends(Set attends) {
		this.attends = attends;
	}

	public Set getFootprints() {
		return this.footprints;
	}

	public void setFootprints(Set footprints) {
		this.footprints = footprints;
	}

}