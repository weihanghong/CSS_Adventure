package com.hong.css_adventure.repositories;

import com.hong.css_adventure.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
}
