package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;

import jakarta.servlet.http.HttpServletRequest;
import model.Game;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    GameRepository gr;
    
    @PostMapping("/addComment")
    public String addComment(@RequestParam("gameId") int gameId,
                             @RequestParam("commentText") String commentText, @RequestParam("userId") int userId, HttpServletRequest request) {
    	
        gameService.addComment(gameId, commentText, userId);
        
        Optional<Game> g = gr.findById(gameId);
    	request.setAttribute("game", g.get());
    	request.setAttribute("comments", g.get().getComments());
    	
    	
        return "pages/details";
    }
}