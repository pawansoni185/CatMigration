package com.yash.online.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.ResponseDAO;
import com.yash.online.portal.dto.ResponseDTO;
import com.yash.online.portal.entity.BaseEntity;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.service.ResponseService;
import com.yash.online.portal.service.UserService;

@Service("responseService")
public class ResponseServiceImpl extends GenericServiceImpl<Response> implements
		ResponseService {

	@Autowired
    private ResponseDAO responseDao;
	@Autowired
	private UserService userService;

	
	/*@Override
	@Transactional
	public void addResponse(ResponseDTO responseDto) {
    User user=getLoggedInUserInfo();
    
	Response response= new Response();
	response.setResponse(responseDto.getResponse());
	response.setUser(user);
	
	
	
	this.responseDao.addResponse(response);
	
	  System.out.println("------->soni service "+response.toString());
		
	}*/


	private User getLoggedInUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user=userService.getUserByUserName(name);
	      return user;
		
	}
	
	@Override
	@Transactional
	public void addResponse(Response response) {
		this.responseDao.addResponse(response);
		
		}

    @Override
	public List<Response> getAllResponse(String threadId) {
		List<Response> responseList=responseDao.getAllResponse(threadId);
		/*for(Response t:responseList)
    	{
    		Response res=new Response();
    		System.out.println("response :"+t.getResponse()+"\n");
    		System.out.println("time..:"+t.getResponseTimestamp());
    		
    		}*/
		
		return responseList;
	}


}
