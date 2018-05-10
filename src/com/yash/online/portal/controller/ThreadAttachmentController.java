package com.yash.online.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yash.online.portal.entity.Thread;
import com.yash.online.portal.entity.ThreadAttachment;
import com.yash.online.portal.service.ThreadAttachmentService;


@Controller
@RequestMapping(value="/threadAttachment")
public class ThreadAttachmentController {
	
	
	@Autowired
	private ThreadAttachmentService threadAttachmentService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	
	
	 @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request,
	            @RequestParam CommonsMultipartFile[] fileUpload, Thread thread) throws Exception {
	          
	        if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){
	            	
/*				String filePath = System.getProperty("java.io.tmpdir") + "/" + ((CommonsMultipartFile) aFile.getFileItem()).getOriginalFilename();
*/                  String filePath = System.getProperty("java.io.tmpdir");
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                
	                
	                 
	                ThreadAttachment threadAttachment = new ThreadAttachment();
	                threadAttachment.setName(aFile.getOriginalFilename());
	                threadAttachment.setThread(thread);
	               
					/*String filePath = null;*/
					threadAttachment.getFilePath();
					threadAttachment.setFilePath(filePath);
	                threadAttachment.setData(aFile.getBytes());
	               threadAttachmentService.threadSave(threadAttachment); 
	               System.out.println("-------------------> pawanservice: " + threadAttachment.toString());
	            }
	        }
	  System.out.println("welcome to thread attachmetn controller");
	        return "welcome";
	    } 
	
	 
}
