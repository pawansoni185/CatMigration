package com.yash.online.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thread_attachments")
public class ThreadAttachment extends BaseEntity{

	

	@JoinColumn(name = "threadId")
	@ManyToOne
	private Thread thread;
	
	@Column
	private String name;
	
	
	@Column(name = "file_path")
	private String filePath;
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
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
		return "ThreadAttachment [thread=" + thread + ", name=" + name
				+ ", filePath=" + filePath + "]";
	}

	public void setData(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}
	
}
