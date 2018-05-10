package com.yash.online.portal.dao;

import java.util.List;

import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.Thread;

public interface ResponseDAO extends GenericDAO<Response> {

	void addResponse(Response response);
	

	List<Response> getAllResponse(String threadId);
}
