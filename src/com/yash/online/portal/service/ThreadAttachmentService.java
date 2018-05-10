package com.yash.online.portal.service;

import com.yash.online.portal.entity.ThreadAttachment;

public interface ThreadAttachmentService extends
		GenericService<ThreadAttachment> {
	void threadSave(ThreadAttachment threadAttachment);

}
