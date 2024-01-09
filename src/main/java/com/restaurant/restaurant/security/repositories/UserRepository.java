package com.restaurant.restaurant.security.repositories;

import com.restaurant.restaurant.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<AppUser, String> {

    public AppUser findAppUserByUsername(String userName);
}
