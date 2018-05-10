package com.yash.online.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.online.portal.entity.User;
import com.yash.online.portal.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	 
    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);

	VerificationToken save(VerificationToken myToken);
}