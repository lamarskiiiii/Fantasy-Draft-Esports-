/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service;

/**
 *
 * @author Administrator
 */


import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.domain.Bet;
import com.mycompany.kdafragbet.ui.workflow.AllAccounts;
import java.util.List;

public class Admin {

    UserManager userManager;
    BettingManager bettingManager;
    
    List<User> user;
   
    public Admin(UserManager userManager, BettingManager bettingManager) {
        this.userManager = userManager;
        this.bettingManager = bettingManager;
    }

    
    public void viewUsers() {new AllAccounts(userManager).display(); }

    
    public void viewUserBets(User user) {
        System.out.println("\n=== BETS FOR: " + user.getUsername() + " ===");
        List<Bet> userBets = bettingManager.getActiveBetsForUser(user);

        if (userBets.isEmpty()) {
            System.out.println("No active bets for this user.");
            return;
        }

        for (Bet b : userBets) {
            System.out.println(b);
        }
    }

    public void viewAllBets() {
        System.out.println("\n=== ALL ACTIVE BETS ===");

        List<User> allUsers = (List<User>) userManager.getAllUsers();
        boolean anyBets = false;

        for (User u : allUsers) {
            List<Bet> bets = bettingManager.getActiveBetsForUser(u);
            if (!bets.isEmpty()) {
                anyBets = true;
                System.out.println("\nUser: " + u.getUsername());
                for (Bet b : bets) {
                    System.out.println(b);
                }
            }
        }

        if (!anyBets) {
            System.out.println("No active bets found.");
        }
    }
}