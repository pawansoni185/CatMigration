package com.yash.online.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.GenericDAO;
import com.yash.online.portal.entity.BaseEntity;
import com.yash.online.portal.service.GenericService;

public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {

	@Autowired
	private GenericDAO<T> genericDAO;
	
	@Override
	@Transactional
	public Long create(T entity) {
		return genericDAO.create(entity);
	}
	
	@Override
	@Transactional
	public T update(T entity){
		return genericDAO.update(entity);
	}
	
	@Override
	@Transactional
	public void delete(T entity){
		genericDAO.delete(entity);
	}
	
	@Override
	@Transactional
	public T findById(Long id){
		return genericDAO.findById(id);
	}
	
	@Override
	@Transactional
	public List<T> findAll(){
		return genericDAO.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		genericDAO.delete(id);
	}
	
	
}
