package org.ike.yota.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userOpenId;
	private String userNickname;
	private String userHead;
	private Integer userAge;
	private String userSex;
	private String userSignature;
	private String userSchool;
	private String userOccupation;
	private String userHometown;
	private String userEducation;
	private String userPosition;
	private String userRole;
	private String userIsLove;
	private String userRealName;
	private Timestamp registerTime;
	private Set activitieses = new HashSet(0);
	private Set footprints = new HashSet(0);
	private Set attends = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userOpenId, String userNickname, String userHead,
			Integer userAge, String userSex, String userSignature,
			String userSchool, String userOccupation, String userHometown,
			String userEducation, String userPosition, String userRole,
			String userIsLove, String userRealName, Timestamp registerTime,
			Set activitieses, Set footprints, Set attends) {
		this.userOpenId = userOpenId;
		this.userNickname = userNickname;
		this.userHead = userHead;
		this.userAge = userAge;
		this.userSex = userSex;
		this.userSignature = userSignature;
		this.userSchool = userSchool;
		this.userOccupation = userOccupation;
		this.userHometown = userHometown;
		this.userEducation = userEducation;
		this.userPosition = userPosition;
		this.userRole = userRole;
		this.userIsLove = userIsLove;
		this.userRealName = userRealName;
		this.registerTime = registerTime;
		this.activitieses = activitieses;
		this.footprints = footprints;
		this.attends = attends;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserOpenId() {
		return this.userOpenId;
	}

	public void setUserOpenId(String userOpenId) {
		this.userOpenId = userOpenId;
	}

	public String getUserNickname() {
		return this.userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserSignature() {
		return this.userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public String getUserSchool() {
		return this.userSchool;
	}

	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}

	public String getUserOccupation() {
		return this.userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

	public String getUserHometown() {
		return this.userHometown;
	}

	public void setUserHometown(String userHometown) {
		this.userHometown = userHometown;
	}

	public String getUserEducation() {
		return this.userEducation;
	}

	public void setUserEducation(String userEducation) {
		this.userEducation = userEducation;
	}

	public String getUserPosition() {
		return this.userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserIsLove() {
		return this.userIsLove;
	}

	public void setUserIsLove(String userIsLove) {
		this.userIsLove = userIsLove;
	}

	public String getUserRealName() {
		return this.userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Set getActivitieses() {
		return this.activitieses;
	}

	public void setActivitieses(Set activitieses) {
		this.activitieses = activitieses;
	}

	public Set getFootprints() {
		return this.footprints;
	}

	public void setFootprints(Set footprints) {
		this.footprints = footprints;
	}

	public Set getAttends() {
		return this.attends;
	}

	public void setAttends(Set attends) {
		this.attends = attends;
	}

}