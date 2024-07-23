package com.hong.css_adventure.service;

import com.hong.css_adventure.models.Skin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkinService {
    List<Skin> findAllSkins();
}
