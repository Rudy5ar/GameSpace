package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
	
	public Game findByTitle(String title);


}
