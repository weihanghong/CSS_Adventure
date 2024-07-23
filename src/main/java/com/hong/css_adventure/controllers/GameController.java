package com.hong.css_adventure.controllers;

import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String mainMenu(Model model, @AuthenticationPrincipal User user) {
        if(user != null) {
            Player player = gameService.findPlayerByUsername(user.getUsername());
            model.addAttribute("player", player);
        } else {
            model.addAttribute("player", null);
        }
        return "main_menu";
    }

    @GetMapping("/lobby")
    public String lobby(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "lobby";
    }

    @GetMapping("/play")
    public String playGame(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "play";
    }

    @GetMapping("/achievements")
    public String checkAchievements(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "achievements";
    }

    @GetMapping("/character")
    public String character(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "character";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model, @AuthenticationPrincipal User user) {
        if(user != null) {
            Player player = gameService.findPlayerByUsername(user.getUsername());
            model.addAttribute("player", player);
        } else {
            model.addAttribute("player", null);
        }
        List<Player> players = gameService.findAllPlayers();
        model.addAttribute("players", players);
        return "leaderboard";
    }

}
