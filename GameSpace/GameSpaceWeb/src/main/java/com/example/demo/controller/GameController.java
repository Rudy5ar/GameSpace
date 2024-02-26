package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.GameRepository;
import com.example.demo.repository.StudioRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import model.Game;
import model.Studio;

@Controller
@RequestMapping("/games")
public class GameController {

	@Autowired
	GameService gs;
	
	@Autowired
	UserService us;
	
	@Autowired
	GameRepository gr;
	
	@Autowired
	StudioRepository sr;
	
	//Metoda koja se poziva nakon uspesne prijave
    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        
    	request.setAttribute("gameList", gs.allGames());
        String u = SecurityContextHolder.getContext().getAuthentication().getName();
        request.getSession().setAttribute("currentUser", us.getUserByName(u));
        
        return "pages/home"; 
    }
    
    @GetMapping("/showFavorites")
    public String showFavorites(@RequestParam int userId, HttpServletRequest request) {
        
    	request.setAttribute("gameList", gs.findByUser(userId));

        return "pages/home"; 
    }
    
    @GetMapping("/getGame")
    public String getGame(@RequestParam int gameId, HttpServletRequest request) {
    	Optional<Game> g = gr.findById(gameId);
    	request.getSession().setAttribute("game", g.get());
    	request.getSession().setAttribute("comments", g.get().getComments());

    	return "pages/details";
    }
   
    
    @PostMapping("/findGame")
    public String findGame(@RequestParam("pretraga") String gameTitle, HttpServletRequest request) {
    	Game g = gr.findByTitle(gameTitle);
    	request.getSession().setAttribute("game", g);
    	request.getSession().setAttribute("searched", true);
    	if(g == null) {
    		return "pages/home";
    	}
    	request.setAttribute("comments", g.getComments());
    	
    	return "pages/details";
    }
    
    @GetMapping("/ukloniOmiljenu")
    public String ukloniOmiljenu(@RequestParam int userId, @RequestParam int gameId, HttpServletRequest request) {
    	gs.ukloniOmiljenu(userId, gameId);
    	request.setAttribute("gameList", gs.allGames());
    	
    	return "pages/home";
    }
    
    @GetMapping("/dodajOmiljenu")
    public String dodajOmiljenu(@RequestParam int userId, @RequestParam int gameId, HttpServletRequest request) {
        gs.dodajOmiljenu(userId, gameId);
        request.setAttribute("gameList", gs.allGames());
        
        return "pages/home";
    }
    
    @PostMapping("/addGame")
    public String addGame(@ModelAttribute("game") Game game, @RequestParam("studio1") String studio1, @RequestParam("studio2") String studio2, Model model) {
        List<Studio> studios = new ArrayList<Studio>();
        if(studio1 != null && !studio1.isEmpty()) {
        	Studio s1 = new Studio(studio1);
            studios.add(s1);
        }
        if(studio2 != null && !studio2.isEmpty()) {
        	Studio s2 = new Studio(studio2);
            studios.add(s2);
        }
    	if(gr.findByTitle(game.getTitle()) == null) {
    		for (Studio studio : studios) {
                sr.save(studio);
            }
        	game.setStudios(studios);
    		gs.addGame(game);
        	
            model.addAttribute("message", "Igra uspesno dodata!");
        } else {
        	model.addAttribute("message", "Igra vec postoji");
        }
        return "pages/addGame";
    }
	
}
