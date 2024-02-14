package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.UserGame;

public interface UserGameRepository extends JpaRepository<UserGame, Integer>{

	UserGame findByUser_UserIdAndGame_GameId(int userId, int gameId);
	
	public List<UserGame> findAllByUser_UserId(int userId);
	
}
