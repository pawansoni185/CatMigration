package com.yash.online.portal.dao;

import java.util.List;

import com.yash.online.portal.entity.BaseEntity;
import com.yash.online.portal.entity.Thread;

public interface GenericDAO<T extends BaseEntity> {

	Long create(T entity);
	
	public T update(T entity);
	
	public void delete(T entity);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public void delete(Long id);

	Thread getThreadInfo(String name);
}
