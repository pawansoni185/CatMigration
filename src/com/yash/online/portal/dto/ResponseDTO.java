package com.yash.online.portal.dto;

import java.util.Date;


public class ResponseDTO  {

	private long id;
	private long threadId;
	
	private String response;
	
	private Date responseTimestamp;
	
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getThreadID() {
		return threadId;
	}

	public void setThreadID(long threadID) {
		this.threadId = threadID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(Date responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ResponseDTO [id=" + id + ", threadID=" + threadId
				+ ", response=" + response + ", responseTimestamp="
				+ responseTimestamp + ", userId=" + userId + "]";
	}

	
	
	
}
