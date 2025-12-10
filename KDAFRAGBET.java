/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet;

import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.repository.ServerManager;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.menu.AuthSystemImpl;


import java.util.Scanner;
import java.util.List; 
import java.util.ArrayList; 

public class KDAFRAGBET {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize managers
        UserManager userManager = new UserManager();
        BettingManager bettingManager = new BettingManager();
        ServerManager serverManager = new ServerManager();
        
        // Initialize available players list for AppContext 
        List<FantasyPlayer> availablePlayers = new ArrayList<>(); 

        // Create the AppContext object which holds all managers 
        AppContext context = new AppContext(
            userManager,
            serverManager,
            bettingManager,
            availablePlayers,
            sc
        ); 

        // Pre-create admin user with password
      userManager.addUser(new User("admin", 30, 1000.0, "admin123", User.Role.ADMIN));
 

        // Start authentication system
        AuthSystemImpl auth = new AuthSystemImpl(context); 
        auth.start();

        sc.close();
    }
}