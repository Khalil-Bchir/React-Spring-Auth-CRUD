package com.restaurant.restaurant.security.repositories;

import com.restaurant.restaurant.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleRepository extends JpaRepository<AppRole, String> {

}
