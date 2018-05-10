package com.yash.online.portal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.ThreadAttachmentDAO;
import com.yash.online.portal.entity.ThreadAttachment;

@Repository("threadAttachmentDAO")
public class ThreadAttachmentDAOImpl extends GenericDAOImpl<ThreadAttachment>
		implements ThreadAttachmentDAO {
	
	 @Autowired
		private SessionFactory sessionFactory;
	
		public void setSessionFactory(SessionFactory sessionFactory) {
			System.out.println("sessionfactory");

			this.sessionFactory = sessionFactory;
			}
		public ThreadAttachmentDAOImpl() {
			setEntity(ThreadAttachment.class);
			}
		
		
		private Session getSession() {
			try {
				return sessionFactory.getCurrentSession();
			} catch (Exception e) {
				
				return sessionFactory.openSession();
			}
		}
	
	
	

	
	@Transactional
	public void threadSave(ThreadAttachment threadAttachment) {
		sessionFactory.getCurrentSession().saveOrUpdate(threadAttachment);
		System.out.println("-------------------> pawanservice: " + threadAttachment.toString());
		
		
	}


}
