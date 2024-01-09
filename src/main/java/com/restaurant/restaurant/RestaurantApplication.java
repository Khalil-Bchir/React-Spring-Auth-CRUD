package com.restaurant.restaurant;

import com.restaurant.restaurant.dao.ItemRepository;
import com.restaurant.restaurant.dao.MenuRepository;
import com.restaurant.restaurant.entities.Menu;
import com.restaurant.restaurant.security.services.IServiceAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestaurantApplication {

    private MenuRepository menuRepository;
    private ItemRepository itemRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    public void run(String... args ) throws Exception {
        menuRepository.save(new Menu(null,"Drinks",null));
        menuRepository.save(new Menu(null,"Plates",null));
        menuRepository.save(new Menu(null,"Sandwiches",null));
        itemRepository.getItemByCat(1L);

    }


    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(IServiceAccount accountService) {
        return args -> {
            accountService.addRole("USER");
            accountService.addRole("ADMIN");
            accountService.addUser("user", "123", "user@gmail.com");
            accountService.addUser("admin","123","admin@gmail.com");
            accountService.addroletoUser("user","USER");
            accountService.addroletoUser("admin","ADMIN");
            accountService.addroletoUser("admin", "USER");

        };
    }

}
