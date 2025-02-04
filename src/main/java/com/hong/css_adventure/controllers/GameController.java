package com.hong.css_adventure.controllers;

import com.hong.css_adventure.models.Achievement;
import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.models.Skin;
import com.hong.css_adventure.repositories.AchievementRepository;
import com.hong.css_adventure.service.GameService;
import com.hong.css_adventure.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {

    private final GameService gameService;
    private final SkinService skinService;
    private final AchievementRepository achievementRepository;

    @Autowired
    public GameController(GameService gameService, SkinService skinService, AchievementRepository achievementRepository) {
        this.gameService = gameService;
        this.skinService = skinService;
        this.achievementRepository = achievementRepository;
    }

    /**
     * Shows the main menu
     * @param model model
     * @param user user that is logged in
     * @return main menu html
     */
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

    /**
     * Shows the lobby
     * @param model model
     * @param user user that is logged in
     * @return lobby html
     */
    @GetMapping("/lobby")
    public String lobby(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "lobby";
    }

    /**
     * Shows the game area
     * @param model model
     * @param user user that is logged in
     * @return play html
     */
    @GetMapping("/play")
    public String playGame(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        return "play";
    }

    /**
     * Updates the high score
     * @param model model
     * @param user user that is logged in
     * @param p player object
     * @return redirect to play/?success
     */
    @PostMapping("/play/save")
    public String updateScore(Model model, @AuthenticationPrincipal User user, @ModelAttribute Player p) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        gameService.updatePlayer(player, p.getHighScore());
        model.addAttribute("player", player);
        return "redirect:/play?success";
    }

    /**
     * Shows all achievements
     * @param model model
     * @param user user that is logged in
     * @return achievements html
     */
    @GetMapping("/achievements")
    public String checkAchievements(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        List<Achievement> achievements = achievementRepository.findAll();
        model.addAttribute("achievements", achievements);
        return "achievements";
    }

    /**
     * Shows your character
     * @param model model
     * @param user user that is logged in
     * @return character html
     */
    @GetMapping("/character")
    public String character(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        List<Skin> skins = skinService.findAllSkins();
        model.addAttribute("player", player);
        model.addAttribute("skins", skins);
        return "character";
    }

    /**
     * Shows the leaderboard
     * @param model model
     * @param user user that is logged in
     * @return leaderboard html
     */
    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model, @AuthenticationPrincipal User user) {
        Player player = gameService.findPlayerByUsername(user.getUsername());
        model.addAttribute("player", player);
        List<Player> players = gameService.findAllPlayersByHighScore();
        model.addAttribute("players", players);
        return "leaderboard";
    }

}
