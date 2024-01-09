package com.restaurant.restaurant.dao;

import com.restaurant.restaurant.entities.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Correct import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {

    public Page<Items> findByNameContains(String mc, Pageable pageable);

    @Query("select i from Items i where i.menu.id= :x")
    public List<Items> getItemByCat(@Param("x") Long idCat);
}
