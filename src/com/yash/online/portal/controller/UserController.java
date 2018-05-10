package com.yash.online.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	
	   @Autowired(required=true)
	    @Qualifier(value="userService")
	    public void setPersonService(UserService us){
	        this.userService = us;
	    }
	
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void create(@RequestBody UserDTO userDTO) throws Exception{
		User user = mapper.map(userDTO, User.class);
		/*userService.create(user);*/
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestBody UserDTO userDTO){
		/*User user = userService.findById(userDTO.getId());*/
		/*mapper.map(userDTO, user);
		userService.update(user);*/
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestParam Long id){
		/*userService.delete(id);*/
	}
	
	/*@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll(){
		return userService.findAll();
	}
	*/
	
	
	 @RequestMapping(value="/signup", method=RequestMethod.GET)
	 
	   public String signup(Model model) {
	 
	       User user = new User();       
	 
	       model.addAttribute("user", user);    
	 
	       return "reg";
	 
	   }
	 
	  
	 
	  
	 /*@RequestMapping(value="/register", method=RequestMethod.POST)

	     public String register(@Validated @ModelAttribute("user") User user, BindingResult result, Model model) {       
	 
	       if(result.hasErrors()) {
	 
	           return "reg";
	 
	       } else if(userService.findByUserName(user.getUsername())) {
	 
	         model.addAttribute("message", "User Name exists. Try another user name");
	 
	           return "reg";
	 
	       } else {
	 
	         userService.addUser(user);
	 
	           model.addAttribute("message", "Saved user details");
	 
	           return "home";
	
	       }
	 */
	   }
	 
	  

	
	 
	
	
	
	
	

