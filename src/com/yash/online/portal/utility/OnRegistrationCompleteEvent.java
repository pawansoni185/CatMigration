package com.yash.online.portal.utility;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.yash.online.portal.entity.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	
	  private String appUrl;
	  private Locale locale;
	  private User user;
	  
	/*  
	  public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public User getUser() {
		return user;
	}
*/
	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);
		this.user=user;
		this.locale=locale;
		this.appUrl=appUrl;
		
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
