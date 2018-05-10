package com.yash.online.portal.service;

import java.util.List;

import com.yash.online.portal.entity.BaseEntity;
import com.yash.online.portal.entity.Thread;

public interface GenericService<T extends BaseEntity> {
	
	public Long create(T entity);
	
	public T update(T entity);
	
	public void delete(T entity);
	
	public void delete(Long id);
	
	public T findById(Long id);
	
	public List<T> findAll();

	
}
