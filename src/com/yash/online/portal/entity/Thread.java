package com.yash.online.portal.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "threads")
public class Thread extends BaseEntity{
	
	@Column(unique=true, nullable=false) 
	private String subject;
	
    @JsonManagedReference
    @XmlTransient
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToOne(mappedBy="thread",cascade= CascadeType.ALL,fetch=FetchType.EAGER,optional=true)
	private Response response;
	
	
	@Column
	private String topic;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@Column
	private String description;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column
	private Date startTime;
	
	@Column
	private Date modifiedTime;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "Thread [subject=" + subject + ", user=" + user + ", response="
				+ response + ", topic=" + topic + ", description="
				+ description + ", startTime=" + startTime + ", modifiedTime="
				+ modifiedTime + "]";
	}
	
	
	
	
	

}
