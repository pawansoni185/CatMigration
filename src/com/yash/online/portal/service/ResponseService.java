package com.yash.online.portal.service;

import java.util.List;

import com.yash.online.portal.dto.ResponseDTO;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.Thread;

public interface ResponseService extends GenericService<Response> {
//	void addResponse(Response resp);
	void addResponse(Response responses);

	List<Response> getAllResponse(String threadId);

}
