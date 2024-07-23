package com.hong.css_adventure.security;

import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.models.Role;
import com.hong.css_adventure.repositories.PlayerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PlayerRepository playerRepository;

    public CustomUserDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username);
        if (player == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    player.getUsername(),
                    player.getPassword(),
                    mapRolesToAuthorities(player.getRoles())
            );
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
