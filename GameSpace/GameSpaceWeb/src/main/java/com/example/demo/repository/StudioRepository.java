package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {

	
}
