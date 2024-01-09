package com.restaurant.restaurant.security.services;

import com.restaurant.restaurant.security.entities.AppUser;

public interface IServiceAccount {
    public void addRole(String role);
    public void addUser(String username, String password, String mail);
    public void addroletoUser(String username, String role);
    public AppUser loadUserByUserName(String username);
}
