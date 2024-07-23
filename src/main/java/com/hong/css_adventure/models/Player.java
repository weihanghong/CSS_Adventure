package com.hong.css_adventure.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int highScore = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_skins",
            joinColumns ={@JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SKIN_ID", referencedColumnName = "ID")}
    )
    private List<Skin> ownedSkins = new ArrayList<>();

    @ManyToOne
    private Skin equippedSkin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_achievements",
            joinColumns = {@JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACHIEVEMENT_ID", referencedColumnName = "ID")}
    )
    private List<Achievement> achievements = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_roles",
            joinColumns = {@JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles = new ArrayList<>();
}
