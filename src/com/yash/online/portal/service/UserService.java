package com.yash.online.portal.service;

import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.User;
import com.yash.online.portal.entity.VerificationToken;

public interface UserService {

	void addUser(UserDTO userDto);

	boolean findByUserName(String username);

	User getUserByUserName(String name);
	
	/* User getUser(String verificationToken);

	VerificationToken getVerificationToken(String token);

	void saveRegisteredUser(User user);

	void createVerificationToken(User user, String token);*/

}
