package com.hong.css_adventure.service.impl;

import com.hong.css_adventure.models.Achievement;
import com.hong.css_adventure.service.AchievementService;
import org.springframework.stereotype.Service;

@Service
public class AchievementServiceImpl implements AchievementService {
    public boolean checkAchieved(Achievement achievement, int value) {
        if(value >= achievement.getValue()) {
            achievement.setCompleted(true);
            return true;
        } else {
            return false;
        }
    }
}
