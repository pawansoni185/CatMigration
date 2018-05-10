package com.yash.online.portal.dto;

import java.util.Date;

public class ThreadDTO {

	private String subject;
	
	private String topic;
	
	private String description;
	private long userId;
	private Date startTime;
	private Date modifiedTime;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "ThreadDTO [subject=" + subject + ", topic=" + topic
				+ ", description=" + description + ", userId=" + userId
				+ ", startTime=" + startTime + ", modifiedTime=" + modifiedTime
				+ "]";
	}
	
	
	
	
	
}
