package com.yash.online.portal.service;

import java.util.List;

import com.yash.online.portal.dto.ThreadDTO;
import com.yash.online.portal.entity.Thread;

public interface ThreadService extends GenericService<Thread> {

	void addThread(Thread thread);
	public List<Thread> getAllThread();
	Thread getThreadInfo(String name);
	Thread getThreadByeId(long id);
	Thread findThreadById(Long thredID);
	
	

}