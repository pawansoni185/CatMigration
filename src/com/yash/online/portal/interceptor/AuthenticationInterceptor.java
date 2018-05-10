package com.yash.online.portal.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
				   HttpServletResponse response, Object handler) throws IOException{
		String uri = request.getRequestURI();
		if(!uri.equalsIgnoreCase("/yashonline/") && !uri.equalsIgnoreCase("/yashonline") && 
				!uri.endsWith("login") && !uri.endsWith("logout") && !uri.endsWith("j_spring_security_check")){
			Boolean loggedIn = request.getSession().getAttribute("USER_LOGGED_IN") != null ?
					(Boolean) request.getSession().getAttribute("USER_LOGGED_IN") : false;
			if(loggedIn){
				return true;
			}
			else{
				response.sendRedirect("/yashonline/");
				return false;
			}
		}
		return true;
	}

	
	
}
