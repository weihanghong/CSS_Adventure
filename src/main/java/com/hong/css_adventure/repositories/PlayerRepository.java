package com.hong.css_adventure.repositories;

import com.hong.css_adventure.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUsername(String username);
    List<Player> findAllByOrderByHighScoreDesc();
}
