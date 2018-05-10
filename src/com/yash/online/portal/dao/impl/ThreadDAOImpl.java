package com.yash.online.portal.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.ThreadDAO;
import com.yash.online.portal.entity.Thread;
import com.yash.online.portal.entity.User;

@Repository("threadDAO")
public class ThreadDAOImpl extends GenericDAOImpl<Thread> implements ThreadDAO {
	 @Autowired
		private SessionFactory sessionFactory;
	
		public void setSessionFactory(SessionFactory sessionFactory) {
			System.out.println("sessionfactory");

			this.sessionFactory = sessionFactory;
			}
		
		public ThreadDAOImpl(){
			setEntity(Thread.class);
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
	public String addThread(Thread thread) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(thread);
		return "welcome";
		
   }
    @SuppressWarnings("unchecked")
	@Override
	public List<Thread> getAllThread() {
    	
    	List<Thread> list= sessionFactory.getCurrentSession().createQuery("select t from Thread t").list();
    	return list;
    	
    }

	@Override
	@Transactional
	public Thread getThreadByeId(long id) {
		System.out.println("dao called");
		Thread thread = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query=session.createQuery("Select t from Thread t where t.id=:threadId").setParameter("threadId", id);
			thread=(Thread)query.uniqueResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return thread;
		
	}
		
}