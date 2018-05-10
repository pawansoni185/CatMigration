package com.yash.online.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.Authorities;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.Thread;
import com.yash.online.portal.entity.ThreadAttachment;
import com.yash.online.portal.entity.Response;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.service.ResponseService;
import com.yash.online.portal.service.ThreadAttachmentService;
import com.yash.online.portal.service.ThreadService;
import com.yash.online.portal.service.UserService;
import com.yash.online.portal.utility.Encryption;
/*import com.yash.online.portal.utility.RegistrationListener;*/

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ThreadService threadService;
	@Autowired
	private ResponseService responseService;
	@Autowired
	private ThreadAttachmentService threadAttachmentService;
	
	private User user;
	/*@Autowired
	private MessageSource messages;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	*/
	@Autowired
	private DozerBeanMapper mapper;
	
	@RequestMapping(value = "/")
	public String firstpage(HttpServletRequest request){
		return "home";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request){
		System.out.println("welcome");
		
		
		 String url = request.getRequestURL().toString();
         System.out.println(url);
         
         
		if(user!=null){
		System.out.println("toke n is this "+user.getToken());
		 String url1 = request.getRequestURL().toString();
         System.out.println(url);
         
         if(url1.contains(user.getToken())){
        	 System.out.println("logic is right");
         }
		}
		return "home";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request,Model model){
		System.out.println("welcome1");
		/*com.yash.online.portal.entity.Thread thread = new com.yash.online.portal.entity.Thread();
		model.addAttribute("thread", thread);
		*/
		return "welcome";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request){
		System.out.println("welcome3");
		
		return "welcome";
	}
	
	
	
	@RequestMapping(value = "/regsubmit")
	public String regsubmit(HttpServletRequest request){
		System.out.println("registration successfull");
		return "home";
  }
	
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public String save(HttpServletRequest request){


		
		System.out.println("welcome4");
		return "welcome";
  }
	
	

	
	     @RequestMapping(value = "/reg")
			public String reg(@ModelAttribute("userform") UserDTO userdto,HttpServletRequest request){
				System.out.println("pls registered");
				return "reg";
			}
	      @RequestMapping(value = "/upload", method = RequestMethod.GET)
	       public String showUploadForm(HttpServletRequest request) {
		   System.out.println("upload file please,,,,,,");
	        return "Upload";
	        }
	  
            @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
		    public String handleFileUpload(HttpServletRequest request,
		            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
		           if (fileUpload != null && fileUpload.length > 0) {
		            for (CommonsMultipartFile aFile : fileUpload){
		            	Object form;
						String filePath = System.getProperty("java.io.tmpdir") + "/" + ((CommonsMultipartFile) aFile.getFileItem()).getOriginalFilename();
		                  
		                System.out.println("Saving file: " + aFile.getOriginalFilename());
		                 
		                ThreadAttachment threadAttachment = new ThreadAttachment();
		                threadAttachment.setName(aFile.getOriginalFilename());
		               
						threadAttachment.setFilePath(filePath);
		                threadAttachment.setData(aFile.getBytes());
		               threadAttachmentService.threadSave(threadAttachment); 
		               System.out.println("-------------------> pawanservice: " + threadAttachment.toString());
		            }
		        }
		  System.out.println("welcome to thread attchment  controller");
		        return "welcome";
		    }  
	
           @RequestMapping(value="/listpage")
		    public String list(HttpServletRequest request){
			  return "welcome";
		  }
           
		  @RequestMapping(value = "/list", method = RequestMethod.GET)
		  @ResponseBody
		    public List<Thread> threadList(ModelMap model)
		    {
	            List<Thread> threadList=threadService.getAllThread();
		      /*  System.out.println("list of thread"+threadList.toString());*/
		        return threadList;
		    }
		  
		  @RequestMapping(value = "/ResponseList/{threadId}", method = RequestMethod.GET)
		  @ResponseBody
		    public List<Response> responseList(@PathVariable String threadId)
		    {
			  	System.out.println("threadId  " + threadId);
	            List<Response> responseList=responseService.getAllResponse(threadId);
	            return responseList;
		    }
		  
		  
		
	   @RequestMapping(value="/forgot")
	    public String forgot(HttpServletRequest request){
		  return "forgot";
	     }
	   @RequestMapping(value = "/register",method = RequestMethod.POST)
		public String register(@ModelAttribute("userform") UserDTO userdto,BindingResult result,@ModelAttribute("username") String username, @ModelAttribute("name") String name ,@ModelAttribute("emailId") String emailId,@ModelAttribute("password") String password,@ModelAttribute("confirmPassword") String ConfirmPassword){
		   boolean error = false;
		   
		   if(userdto.getName().isEmpty()){
		        result.rejectValue("Name", "error.Name");
		        error = true;
		    }
		 if(userdto.getPassword().isEmpty()){
			 result.rejectValue("password", "error.password");
			 error= true;
		 }
		 if(userdto.getUsername().isEmpty()){
			 result.rejectValue("username", "error.userName");
		        error = true;
		 }
		 
		 boolean retVal;
		 String Str = new String(userdto.getEmailId());
		 retVal = Str.endsWith("@yash.com");
		 System.out.println("Returned Value = " + retVal );
		 

		 if(userdto.getEmailId().isEmpty()||retVal==false){
			 result.rejectValue("emailId", "error.email");
		        error = true;
		 }
		 if(userdto.getConfirmPassword().equals(password)){
			 /*return "home";*/
			 System.out.println("password match");
		 }else{
			 
			 result.rejectValue("confirmPassword", "error.confirmPassword");
		        error = true;
		 }
			
			 
			 if(error) {
			        return "reg";
			    }
			 else{
			    	UserDTO userDto = new UserDTO();
					userDto.setUsername(username);
					userDto.setName(name);
					userDto.setEmailId(emailId);
					userDto.setPassword(Encryption.encrypt(password));
					userService.addUser(userDto);
					return "home";
			    }
			
			
		}	
	  
	   /* @RequestMapping(value="/register", method=RequestMethod.POST)
		public String register(HttpServletRequest request){
    	    String name=request.getParameter("username");
    	    String password = request.getParameter("passid");
    	    String email = request.getParameter("emailId");
    	    String userName = request.getParameter("username");
    	  
			System.out.println("register process is running ");
			User u=new User();
			u.setName(name);
			u.setEmailId(email);
			u.setUsername(userName);
			u.setPassword(Encryption.encrypt(password));
			List<Authorities> authorities = null;
			u.setAuthorities(authorities);
			userService.addUser(u);
			
				return "home";
			}*/
			
      
      
      @RequestMapping(value="/logout", method = RequestMethod.GET)  
      public String logout(HttpServletRequest request) {
    	 HttpSession session  = request.getSession();
		session.invalidate();
		System.out.println("logout");
       return "home";  
       }
   

	   /***   this is the code of mail verification at the time of registration  *****/
      
	  /* @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	   public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO accountDto, 
	         BindingResult result, WebRequest request, Errors errors) {
	       if (result.hasErrors()) {
	           return new ModelAndView("registration", "user", accountDto);
	       }
	        
	       User registered = registered = createUserAccount(accountDto);
	       if (registered == null) {
	           result.rejectValue("email", "message.regError");
	       }
	       try {
	           String appUrl = request.getContextPath();
	           eventPublisher.publishEvent(new OnRegistrationCompleteEvent
	             (registered, request.getLocale(), appUrl));
	       } catch (Exception me) {
	           return new ModelAndView("emailError", "user", accountDto);
	       }
	       return new ModelAndView("successRegister", "user", accountDto);
	   }

	private User createUserAccount(UserDTO accountDto) {
		
		return null;
	}
	 */
	/*@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration (WebRequest request, Model model, @RequestParam("token") String token) {
	    Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setEnabled(true); 
	    userService.saveRegisteredUser(user); 
	    return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
	}*/
} 
