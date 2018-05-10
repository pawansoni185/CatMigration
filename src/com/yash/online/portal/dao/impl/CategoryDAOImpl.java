package com.yash.online.portal.dao.impl;

import org.springframework.stereotype.Repository;

import com.yash.online.portal.dao.CategoryDAO;
import com.yash.online.portal.entity.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements
		CategoryDAO {

}
