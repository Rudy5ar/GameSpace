package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.UserGameRepository;
import com.example.demo.repository.UserRepository;

import model.Comment;
import model.Game;
import model.User;
import model.UserGame;

@Service
public class GameService {

	@Autowired
	GameRepository gr;
	
	@Autowired
	CommentRepository cr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	UserGameRepository ugr;
	
	public ArrayList<Game> allGames(){
		return (ArrayList<Game>) gr.findAll();
	}
	
	public Game findById(int gameId) {
        return gr.findById(gameId).get();
    }
	
	public List<Game> findByUser(int userId) {
        List<UserGame> list = ugr.findAllByUser_UserId(userId);
        
        List<Game> favorites = new ArrayList<Game>();
        for(UserGame ug : list) {
        	favorites.add(gr.findById(ug.getGame().getGameId()).get());
        }
        return favorites;
	}

    public void addComment(int gameId, String commentText, int userId) {
        Game game = findById(gameId);
        User user = ur.findById(userId).get();
        if (game != null) {
            Comment comment = new Comment();
            comment.setGame(game);
            comment.setCommentText(commentText);
            comment.setUser(user);
            cr.save(comment);
        }
    }

	public void ukloniOmiljenu(int userId, int gameId) {
		UserGame ug = ugr.findByUser_UserIdAndGame_GameId(userId, gameId);
		System.out.println("sestra moja");
		System.out.println(ug);
		ugr.delete(ug);
	}
	
	public void dodajOmiljenu(int userId, int gameId) {
        UserGame existingUserGame = ugr.findByUser_UserIdAndGame_GameId(userId, gameId);
        if (existingUserGame == null) {
            UserGame newUserGame = new UserGame();
            newUserGame.setUser(ur.findById(userId).get());
            newUserGame.setGame(gr.findById(gameId).get());
            ugr.save(newUserGame);
        }
    }

	public void addGame(Game game) {
        gr.save(game);
    }
	
}
