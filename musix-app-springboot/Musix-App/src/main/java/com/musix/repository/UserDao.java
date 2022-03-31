package com.musix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.musix.model.User;
import java.util.Optional;

import javax.transaction.Transactional;


public interface UserDao extends JpaRepository<User, Integer> {
	
	// This is used to specify the custom query for mySQL.be it JQL or HQL
	@Query("SELECT u FROM User u WHERE u.email = (?1)")
	public User getEmail(String email);

	public User findByEmail(String email);
	
}
