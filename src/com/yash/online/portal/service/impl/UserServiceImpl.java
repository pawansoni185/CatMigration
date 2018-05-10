package com.yash.online.portal.service.impl;

import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.online.portal.dao.UserDAO;
import com.yash.online.portal.dao.VerificationTokenRepository;
import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.entity.VerificationToken;
import com.yash.online.portal.service.UserService;
import com.yash.online.portal.utility.EmailUtill;

@Service("userService")

public class UserServiceImpl  implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	/*@Autowired
    private VerificationTokenRepository tokenRepository;*/
	
	
	public void addUser(UserDTO userDto) {
	/*user.setPassword(passwordEncoder.encode(user.getPassword()));*/
		User user=new User();
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setEmailId(userDto.getEmailId());
		user.setPassword(userDto.getPassword());
		System.out.println(user.toString());
		
		/*//token
		
		 	SecureRandom random = new SecureRandom();
		    byte bytes[] = new byte[20];
		    random.nextBytes(bytes);
		    //System.out.println(bytes);
		    System.out.println("token is thi    :::::::: "+bytes);
		    
		    String token= bytes.toString();
		    
		    user.setToken(token);
		
		System.out.println("SimpleEmail Start");
        
        String smtpHostServer = "yashmail001.yash.com";
        //String emailID = "pawan.soni@yash.com";
         
        Properties props = System.getProperties();
 
        props.put("mail.smtp.host", smtpHostServer);
 
        Session session = Session.getInstance(props, null);
       
	    String url="http://localhost:8080/yashonline/login"+bytes+"";
	    
	    System.out.println(url);
	    
	    EmailUtill.sendEmail(session, user.getEmailId(),"SimpleEmail Testing Subject", url);*/
    
		 this.userDAO.addUser(user);
	}

	
	public boolean findByUserName(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Transactional
	public User getUserByUserName(String name) {
		User user=userDAO.getUserByUserName(name);
		return user;
	}

	/*private boolean emailExist(String email) {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }*/
	
	/*@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		
		return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public void saveRegisteredUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
		
	}

	@Override
	public User getUser(String verificationToken) {
		 User user = tokenRepository.findByToken(verificationToken).getUser();
		return null;
	}

	*/
}

