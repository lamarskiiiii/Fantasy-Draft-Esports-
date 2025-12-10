/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.domain;

/**
 *
 * @author Administrator
 */

//user info

import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.domain.Role;
import java.util.ArrayList;
import java.util.List;

public class User {
    
     public enum Role {
        ADMIN,
        USER
    }
    private String username;
    private int age;
    private double balance;
    private String password;
    private List<FantasyPlayer> draftedPlayers;
    private Role role;
    private boolean isOnline;
    private String league;
    private String userId;
    

    public User(String username, int age, double balance, String password, Role role) {
        this.username = username;
        this.age = age;
        this.balance = balance;
        this.password = password;
        this.role = role;
        this.draftedPlayers = new ArrayList<>();
        this.isOnline = false;//
        this.league = "N/A";
        this.userId = generateUserId();
    }
    
    private String generateUserId() {
        // Math.random() * 900000 generates a number between 0 (inclusive) and 900000 (exclusive)
        // Adding 100000 shifts the range to 100000 to 999999.
        int randomId = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(randomId);
    }

    // Getters
    public String getUserId() { return userId; } 
    public String getUsername() { return username; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { this.isOnline = online; }
    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }
    

    // Drafted players
    public List<FantasyPlayer> getDraftedPlayers() {
        return draftedPlayers;
    }
    

   public void draftPlayer(FantasyPlayer player) {
        if (player != null) {
            this.draftedPlayers.add(player);
        }
    }
    public void setRole(Role role) {
    if (role != null) {
        this.role = role;
         }
    }
    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
