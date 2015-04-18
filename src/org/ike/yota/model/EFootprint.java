package org.ike.yota.model;

import java.util.List;

public class EFootprint {
	
	private Integer footprintId;		//足迹Id
	private Integer userId;				//用户Id
	private String userNickname;		//用户昵称
	private String userHead;			//用户头像
	private Integer activitiesId;		//活动Id
	private String footprintMood;		//心情
	private String footprintTime;		//足迹时间
	private List<String> photoStream;	//足迹图片
	
	public Integer getFootprintId() {
		return footprintId;
	}
	public void setFootprintId(Integer footprintId) {
		this.footprintId = footprintId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	public Integer getActivitiesId() {
		return activitiesId;
	}
	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}
	public String getFootprintMood() {
		return footprintMood;
	}
	public void setFootprintMood(String footprintMood) {
		this.footprintMood = footprintMood;
	}
	
	public String getFootprintTime() {
		return footprintTime;
	}
	public void setFootprintTime(String footprintTime) {
		this.footprintTime = footprintTime;
	}
	public List<String> getPhotoStream() {
		return photoStream;
	}
	public void setPhotoStream(List<String> photoStream) {
		this.photoStream = photoStream;
	}
	
	
}
