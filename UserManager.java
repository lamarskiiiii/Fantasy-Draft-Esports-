/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.repository;

/**
 *
 * @author Administrator
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */

import com.mycompany.kdafragbet.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
  
    private final Map<String, User> users;

    public UserManager() {
      
        users = new ConcurrentHashMap<>(); 
    }

    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getUserId())) {
            
            users.put(user.getUserId(), user);
        }
    }

    public List<User> getAllUsers() {
       
        return Collections.unmodifiableList(new ArrayList<>(users.values()));
    }
    public Optional<User> findUserById(String userId) {
       
        return Optional.ofNullable(users.get(userId));
    }

  
    public Optional<User> findUserByUsername(String username) {
      
        return users.values().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username)) 
                .findFirst();
    }
    
    public boolean removeUser(String userId) {
        return users.remove(userId) != null;
    }
    
    
    public User getUser(String username) {
        return findUserByUsername(username).orElse(null);
    }

    public boolean userExists(String username) {
        return findUserByUsername(username).isPresent();
    }
}