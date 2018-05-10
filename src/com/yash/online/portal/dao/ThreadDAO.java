package com.yash.online.portal.dao;

import java.util.List;

import com.yash.online.portal.entity.Thread;

public interface ThreadDAO extends GenericDAO<Thread> {
	
	String addThread(Thread thread);
	public List<Thread> getAllThread();
	Thread getThreadInfo(String name);
	Thread getThreadByeId(long id);
	
}
