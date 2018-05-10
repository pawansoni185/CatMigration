package com.yash.online.portal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;
	
	@Column(name = "role")
	private String role;
	
	
	public String getAuthority() {
		return role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
