package com.yash.online.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yash.online.portal.service.UserService;

@Controller
public class LandingPageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String landingPage(){
		System.out.println("hello");
		return "index";
	}
}
