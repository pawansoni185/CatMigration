package com.yash.online.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "response_attachments")
public class ResponseAttachment extends BaseEntity {

	@JoinColumn(name = "responseId")
	@ManyToOne
	private Response response;
	
	@Column
	private String name;
	
	@Column
	private String filePath;
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "ResponseAttachment [response=" + response + ", name=" + name
				+ ", filePath=" + filePath + ", getResponse()=" + getResponse()
				+ ", getName()=" + getName() + ", getFilePath()="
				+ getFilePath() + "]";
	}
	
}
