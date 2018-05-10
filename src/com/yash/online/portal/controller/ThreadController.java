package com.yash.online.portal.controller;

import java.awt.Window;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.yash.online.portal.dto.ResponseDTO;
import com.yash.online.portal.dto.ThreadDTO;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.Thread;
import com.yash.online.portal.entity.ThreadAttachment;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.service.ResponseService;
import com.yash.online.portal.service.ThreadAttachmentService;
import com.yash.online.portal.service.ThreadService;
import com.yash.online.portal.service.UserService;

@Controller
@RequestMapping(value = "/thread")
public class ThreadController {
	@Autowired
	private ThreadService threadService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResponseService responseService;
	@Autowired
	private ThreadAttachmentService threadAttachmentService;
	
	/**    this is the code for save thread 
	 * @param responseTimestamp ***/
	
	@RequestMapping(value = "/saveQuestion",method= RequestMethod.POST)
	public @ResponseBody String saveQuestion(@RequestBody String objectdata,HttpServletResponse response) {
		
		System.out.println("objectdata :"+objectdata.toString());
		 Gson json = new Gson();
		 ThreadDTO res = json.fromJson(objectdata, ThreadDTO.class);
		 System.out.println("ThreadDTO "+res.toString());
		 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
		System.out.println("name--"+name);
		/*String description=request.getParameter("description");
		String topic=request.getParameter("topic");
		String subject=request.getParameter("subject");
		if(subject.isEmpty()||description.isEmpty()||topic.isEmpty()){
			return "welcome";
		}else{*/
		Thread thread = new Thread();
	    thread.setDescription(res.getDescription());
		thread.setSubject(res.getSubject());
		thread.setTopic(res.getTopic());
		Date startTime = null;
		thread.setStartTime(startTime);
		thread.getStartTime();
		thread.getDescription();
		User user=getLoggedInUserInfo();
		thread.setUser(user);
		
		this.threadService.addThread(thread);
		System.out.println("ajax------->"+thread.toString());
		
	return "welcome";
                   }
	
	
	
	/*@RequestMapping(value = "/saveQuestion",method= RequestMethod.POST)
	public @ResponseBody Thread saveQuestion(HttpServletRequest request,HttpServletResponse response){
		
		String description=request.getParameter("description");
		String topic=request.getParameter("topic");
		String subject=request.getParameter("subject");
		 
		Thread thread= new Thread();
		Date startTime = null;
		Date modifiedTime = null;
		User user=getLoggedInUserInfo();
		thread.setDescription(description);
		thread.setSubject(subject);
		thread.setTopic(topic);
		thread.setUser(user);
		thread.setStartTime(startTime);
		thread.setModifiedTime(modifiedTime);
		threadService.addThread(thread);
		System.out.println("ajax------->"+thread.toString());
		response.setIntHeader("Refresh", 5);
		return thread;
		
	}
	
	
	*/
	
	
	
	
	
	
	
	
	/*** here is the code of thread attachment   ***/
	
	    @RequestMapping(value = "/DoUpload", method = RequestMethod.POST)
        public String handleFileUpload(HttpServletRequest request,
         @RequestParam CommonsMultipartFile[] fileUpload, Thread thread) throws Exception {
       
             if (fileUpload != null && fileUpload.length > 0) {
             for (CommonsMultipartFile aFile : fileUpload){
         	String filePath = System.getProperty("java.io.tmpdir");
             System.out.println("Saving file: " + aFile.getOriginalFilename());
             ThreadAttachment threadAttachment = new ThreadAttachment();
             threadAttachment.setName(aFile.getOriginalFilename());
             threadAttachment.setThread(thread);
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

		/*** here is the code of save response for particular thread **/
	    
	   @RequestMapping(value = "/saveResponse",method= RequestMethod.POST)
	   public @ResponseBody String saveResponse(@RequestBody String response,HttpServletRequest request) {
		 Gson json = new Gson();
		 ResponseDTO res = json.fromJson(response, ResponseDTO.class);
		 Long thredID=res.getThreadID();
	     Thread th=threadService.findThreadById(thredID);
	     User user=getLoggedInUserInfo();
	     Response responses =new Response();
	     responses.setResponse(res.getResponse());
	     	responses.setResponseTimestamp(res.getResponseTimestamp());
			responses.setThread(th);
			responses.setUser(user);
		
    this.responseService.addResponse(responses);
	/*System.out.println("-->soni-->"+responses.toString());
	System.out.println("-->soni-->"+th.toString());*/
	return "welcome";
	}
	   
      @RequestMapping(value = "/create", method = RequestMethod.GET)
	  public Long create(){
		Thread thread = new Thread();
		return threadService.create(thread);
		}
	
	  @RequestMapping(value = "/upload", method = RequestMethod.GET)
	    public String showUploadForm(HttpServletRequest request) {
	        return "Upload";
	    }
	
	  public User someRequestHandler(Principal principal) {
		   Object activeUser =  ((Authentication) principal).getPrincipal();
		   System.out.println( "name"+activeUser);
		  return new User();
		}
	 
	   private User getLoggedInUserInfo()
	    {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user=userService.getUserByUserName(name);
	      return user;
	    }
	
	 private Thread getThreadByeId(long id )
	    {
	      Thread thread=threadService.getThreadByeId(id);
		return thread;
		
	}
	
	 
	 
}
