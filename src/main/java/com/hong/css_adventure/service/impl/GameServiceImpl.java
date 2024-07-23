package com.hong.css_adventure.service.impl;

import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.models.Role;
import com.hong.css_adventure.repositories.PlayerRepository;
import com.hong.css_adventure.repositories.RoleRepository;
import com.hong.css_adventure.service.GameService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final PlayerRepository playerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public GameServiceImpl(PlayerRepository playerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerPlayer(Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        String roleName = "ROLE_PLAYER";
        Role role = roleRepository.findRoleByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        player.setRoles(Collections.singletonList(role));
        playerRepository.save(player);
    }

    public Player findPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }


}
