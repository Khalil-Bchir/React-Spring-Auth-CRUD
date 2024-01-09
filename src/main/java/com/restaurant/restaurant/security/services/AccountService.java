package com.restaurant.restaurant.security.services;

import com.restaurant.restaurant.security.entities.AppRole;
import com.restaurant.restaurant.security.entities.AppUser;
import com.restaurant.restaurant.security.repositories.RoleRepository;
import com.restaurant.restaurant.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService  implements IServiceAccount {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addRole(String role) {
        roleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addUser(String username, String password, String mail){
        AppUser user=userRepository.findAppUserByUsername(username);
        if (user != null) throw new RuntimeException("user exist");
        userRepository.save(AppUser
                .builder()
                .id(UUID.randomUUID().toString())
                .mail(mail)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build());
    }

    @Override
    public void addroletoUser(String username, String role) {
        AppUser user = loadUserByUserName(username);
        AppRole rol= roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);
    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return userRepository.findAppUserByUsername(username);
    }


}
