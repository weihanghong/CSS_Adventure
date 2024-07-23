package com.hong.css_adventure;

import com.hong.css_adventure.models.Player;
import com.hong.css_adventure.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CssAdventureApplicationTests {

    @Autowired
    private PlayerRepository playerRepository;

    @ParameterizedTest
    @ValueSource(strings = {"player", "bad"})
    void testFindPlayerByUsername(String username) {
        Player player = playerRepository.findByUsername(username);
        assertNotNull(player);
    }

}
