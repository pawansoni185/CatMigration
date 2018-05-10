package com.yash.online.portal.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@Scope("prototype")
public class User implements UserDetails{

   
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String emailId;
	
	@Column
	private String password;
	
	@Column
	private String username;
	

    @Column
    private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonManagedReference
	@OneToOne(mappedBy="user")
	@JsonIgnore
	private Thread thread;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authorities> authorities;
	
	
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/*public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "enabled")
	private boolean enabled;
	     
	 public User() {
	        super();
	        this.enabled=true;
	    }*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<Authorities> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", emailId=" + emailId + ", password="
				+ password + ", username=" + username + ", authorities="
				+ authorities + "]";
	}
	
	
}
