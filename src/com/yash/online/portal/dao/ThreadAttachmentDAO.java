package com.yash.online.portal.dao;

import com.yash.online.portal.entity.ThreadAttachment;

public interface ThreadAttachmentDAO extends GenericDAO<ThreadAttachment> {

	void threadSave(ThreadAttachment threadAttachment);
	
	
}
