package org.ike.yota.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ActivitiesType entity. @author MyEclipse Persistence Tools
 */

public class ActivitiesType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Set activitieses = new HashSet(0);

	// Constructors

	/** default constructor */
	public ActivitiesType() {
	}

	/** full constructor */
	public ActivitiesType(String typeName, Set activitieses) {
		this.typeName = typeName;
		this.activitieses = activitieses;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getActivitieses() {
		return this.activitieses;
	}

	public void setActivitieses(Set activitieses) {
		this.activitieses = activitieses;
	}

}