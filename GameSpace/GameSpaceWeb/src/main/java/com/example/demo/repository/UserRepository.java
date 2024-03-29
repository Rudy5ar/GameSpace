package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	User findByUsername(String username);
	
	@Query("select u from User u where u.email=:email")
	public User findByEmail(@Param("email") String email);

}