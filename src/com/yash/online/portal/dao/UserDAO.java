package com.yash.online.portal.dao;

import com.yash.online.portal.dto.UserDTO;
import com.yash.online.portal.entity.User;

public interface UserDAO /*extends GenericDAO<User>*/{

	void addUser(User user);

	boolean findByUserName(String username);

	User getUserByUserName(String name);

	User findByEmail(String email);
}
