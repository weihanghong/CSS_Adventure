package com.hong.css_adventure.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "achievements")
@Data
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AchievementType type;

    @Column(nullable = false)
    private int value;
}
