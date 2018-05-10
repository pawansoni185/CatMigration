package com.yash.online.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.ThreadAttachmentDAO;
import com.yash.online.portal.entity.ThreadAttachment;
import com.yash.online.portal.service.ThreadAttachmentService;

@Service("threadAttachmentService")
public class ThreadAttachmentServiceImpl extends
		GenericServiceImpl<ThreadAttachment> implements ThreadAttachmentService {
	
	@Autowired
	ThreadAttachmentDAO threadAttachmentDao;

	@Override
	@Transactional
	public void threadSave(ThreadAttachment threadAttachment) {
		this.threadAttachmentDao.threadSave(threadAttachment);
		
	}

}
