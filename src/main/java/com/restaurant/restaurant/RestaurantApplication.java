package com.restaurant.restaurant;

import com.restaurant.restaurant.dao.MenuRepository;
import com.restaurant.restaurant.entities.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {

    private MenuRepository menuRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    public void run(String... args ) throws Exception {
        menuRepository.save(new Menu(null,"Drinks"));
        menuRepository.save(new Menu(null,"Plates"));
        menuRepository.save(new Menu(null,"Sandwiches"));

    }

}
