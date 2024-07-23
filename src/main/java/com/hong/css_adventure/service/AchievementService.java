package com.hong.css_adventure.service;

import com.hong.css_adventure.models.Achievement;
import org.springframework.stereotype.Service;

@Service
public interface AchievementService{
    boolean checkAchieved(Achievement achievement, int value);
}
