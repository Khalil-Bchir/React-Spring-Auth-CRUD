package com.restaurant.restaurant.dao;

import com.restaurant.restaurant.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Custom query methods can be added here if needed
}

