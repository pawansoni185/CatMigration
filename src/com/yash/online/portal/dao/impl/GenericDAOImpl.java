package com.yash.online.portal.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.online.portal.dao.GenericDAO;
import com.yash.online.portal.entity.BaseEntity;
import com.yash.online.portal.entity.Thread;

@Repository("genericDAO")
public class GenericDAOImpl<T extends BaseEntity> implements GenericDAO<T> {

	private Class<T> entity;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public Class<T> getEntity() {
		return entity;
	}

	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}
	
	@Override
	public Long create(T entity) {
		Session session = sessionFactory.getCurrentSession();
		Long id = (Long) session.save(entity);
		session.flush();
		return id;
	}

	@Override
	public T update(T entity){
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
		session.flush();
		return entity;
	}
	
	public void delete(T entity){
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
		session.flush();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(Long id){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entity);
		criteria.add(Restrictions.eq("id", id));
		return (T) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entity);
		return criteria.list();
	}

	@Override
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE " + entity.getName() + " WHERE id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public Thread getThreadInfo(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
