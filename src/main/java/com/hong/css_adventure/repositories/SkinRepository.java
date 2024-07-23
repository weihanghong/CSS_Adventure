package com.hong.css_adventure.repositories;

import com.hong.css_adventure.models.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Integer> {
}
