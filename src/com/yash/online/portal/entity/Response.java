package com.yash.online.portal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "responses")
public class Response extends BaseEntity {

	@JoinColumn(name = "threadId")
	@ManyToOne
	private Thread thread;
	
	
	

	@Column
	private String response;
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	@Column
	private Date responseTimestamp;
	
   public Response() {
		super();
		
	}
	
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getResponseTimestamp() {
		return responseTimestamp;
	}
	public void setResponseTimestamp(Date responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	/*@Override
	public String toString() {
		return "Response [thread=" + thread + ", response=" + response
				+ ", user=" + user + ", responseTimestamp=" + responseTimestamp
				+ "]";
	}*/

	

	

	
}