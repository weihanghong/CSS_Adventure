package com.hong.css_adventure.controllers;

import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final GameService gameService;

    @Autowired
    public LoginController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Player player = new Player();
        model.addAttribute("player", player);
        return "register";
    }

    @PostMapping("/register/save")
    public String savePlayer(Model model, @ModelAttribute("player") Player player) {
        Player existing = gameService.findPlayerByUsername(player.getUsername());
        if (existing != null) {
            model.addAttribute("message", "This username is already taken!");
            return "register";
        } else {
            gameService.registerPlayer(player);
            return "redirect:/register?success";
        }
    }
}
