package com.yash.online.portal.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.ResponseDAO;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.Thread;

@Repository("responseDAO")
public class ResponseDAOImpl extends GenericDAOImpl<Response> implements
		ResponseDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("sessionfactory");

		this.sessionFactory = sessionFactory;
		}
	
	public ResponseDAOImpl(){
		setEntity(Response.class);
	}
	
	@SuppressWarnings("unused")
	private Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			
			return sessionFactory.openSession();
		}
	}
	
    @Override
	@Transactional
	public void addResponse(Response response) {
	sessionFactory.getCurrentSession().saveOrUpdate(response);
	}
    
    @SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Response> getAllResponse(String threadId) {
		List<Response> responseList= sessionFactory.getCurrentSession().createQuery("select r from Response r where r.thread ="+threadId).list();
	
		for(Response t:responseList)
    	{
    		Response res=new Response();
    		System.out.println("response :"+t.getResponse()+"\n");
    		System.out.println("time..:"+t.getResponseTimestamp());
    		
    	}
		return responseList;
	}

}
