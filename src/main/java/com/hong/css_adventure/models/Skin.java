package com.hong.css_adventure.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "skins")
@Data
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
}