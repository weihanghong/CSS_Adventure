package com.hong.css_adventure.service.impl;

import com.hong.css_adventure.models.Skin;
import com.hong.css_adventure.repositories.SkinRepository;
import com.hong.css_adventure.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinServiceImpl implements SkinService {
    private final SkinRepository skinRepository;

    @Autowired
    public SkinServiceImpl(SkinRepository skinRepository) {
        this.skinRepository = skinRepository;
    }

    public List<Skin> findAllSkins() {
        return skinRepository.findAll();
    }
}
