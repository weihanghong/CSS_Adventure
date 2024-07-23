package com.hong.css_adventure.service;

import com.hong.css_adventure.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    void registerPlayer(Player player);
    Player findPlayerByUsername(String playerName);
    List<Player> findAllPlayers();
}
