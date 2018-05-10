package com.yash.online.portal.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.UserDAO;
import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.Authorities;
import com.yash.online.portal.entity.User;

@Repository("userDAO")
public class UserDAOImpl  implements UserDAO, UserDetailsService{
	
	
	@Autowired
	private SessionFactory sessionFactory;

          public void setSessionFactory(SessionFactory sessionFactory) {
			System.out.println("sessionfactory");
			this.sessionFactory = sessionFactory;}
  
	public UserDAOImpl(){
		setEntity(User.class);
	}

	private void setEntity(Class<User> class1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public User loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.createAlias("authorities", "authorities", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("username", userName));
		User userDetails = (User) criteria.uniqueResult();
		System.out.println(userDetails);
		for(Authorities authorities : userDetails.getAuthorities()){
			System.out.println(authorities.getRole());
		}
		return userDetails;
	}
	private Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			
			return sessionFactory.openSession();
		}
	}

	@Override
	@Transactional
	public void addUser(User user) {
		System.out.println("-------------------> pawan: " + user.toString());
		 sessionFactory.getCurrentSession().saveOrUpdate(user);  
		 System.out.println("session----visal----");
	}

	@Override
	@Transactional
	public boolean findByUserName(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByUserName(String name) {
		User user=null;
		try{
			Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("Select u from User u where u.username=:username").setParameter("username",name);
		 user=(User)query.uniqueResult();
		 System.out.println("user email id "+user.getEmailId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
