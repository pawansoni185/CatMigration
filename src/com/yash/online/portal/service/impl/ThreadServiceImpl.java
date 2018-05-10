package com.yash.online.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.service.ThreadService;
import com.yash.online.portal.service.UserService;
import com.yash.online.portal.dao.ThreadDAO;
import com.yash.online.portal.dto.ThreadDTO;
import com.yash.online.portal.entity.Thread;
import com.yash.online.portal.entity.User;

@Service("threadService")
public class ThreadServiceImpl extends GenericServiceImpl<Thread> implements
		ThreadService {

	@Autowired
	private ThreadDAO threadDAO;
	@Autowired
	private UserService userService;
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long create(Thread thread){
		return threadDAO.create(thread);
	}
    @Transactional
	@Override
	public void addThread(Thread thread) {
		/*System.out.println("-------------------> pawanservice: " + thread.toString());
		
		Thread thread =new Thread();
		thread.setDescription(threadDTO.getDescription());
		thread.setSubject(threadDTO.getSubject());
		thread.setTopic(threadDTO.getTopic());
		thread.setStartTime(threadDTO.getStartTime());
		thread.setModifiedTime(threadDTO.getModifiedTime());
		User user=getLoggedInUserInfo();
		thread.setUser(user);*/
		
	this.threadDAO.addThread(thread);
		
		
		
	}
    @Transactional
	@Override
	public List<Thread> getAllThread() {
		
    	List<Thread> list= threadDAO.getAllThread();
	
    	/*for(Thread t:list)
    	{
    		
    		System.out.println("subject :"+t.getSubject()+"\n");
    		System.out.println("description :"+t.getDescription()+"\n");
    		System.out.println("id :"+t.getId()+"\n");
    		try{
    		System.out.println("user name :"+t.getUser().getUsername()+"\n");
    		}
    		catch(Exception e)
    		{
    			System.out.println("user name :"+"no user found"+"\n");
    		}
    		
    	}*/
    	return list;
    }
	@Override
	@Transactional
	public Thread getThreadInfo(String name) {
		Thread thread=threadDAO.getThreadInfo(name);
		return thread;
	}
	@Override
	public Thread getThreadByeId(long id) {
		
	    Thread thread=threadDAO.getThreadByeId(id);
		return thread;
	}
	@Override
	public Thread findThreadById(Long thredID) {
		System.out.println("service called");
		Thread th=getThreadByeId(thredID);
		return th;
	}
	
	 private User getLoggedInUserInfo()
	    {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user=userService.getUserByUserName(name);
	      return user;
	    }
	/*@Override
	public void addThread(ThreadDTO threadDto) {
		// TODO Auto-generated method stub
		
	}*/
}
