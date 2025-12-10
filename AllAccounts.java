/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.UserManager;
import java.util.List;
import java.util.Collection;


public class AllAccounts {
    UserManager userManager;

    public AllAccounts(UserManager userManager) { this.userManager = userManager; }

    public void display() {
        List<User> users = (List<User>) userManager.getAllUsers(); 
        
        System.out.println("\n=== ALL USERS ===");
        
        if (users.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        } 
        
    
        final int NAME_WIDTH = 20; 
        final int ROLE_WIDTH = 6;
        final int BALANCE_WIDTH = 12;
        final int STATUS_WIDTH = 8;
        final int LEAGUE_WIDTH = 10;
        
        String separator = "+-" + "-".repeat(NAME_WIDTH) + "-+-" + "-".repeat(ROLE_WIDTH) + 
                           "-+-" + "-".repeat(BALANCE_WIDTH) + "-+-" + "-".repeat(STATUS_WIDTH) + 
                           "-+-" + "-".repeat(LEAGUE_WIDTH) + "-+";
        

        System.out.println(separator);
        System.out.printf("| %-" + NAME_WIDTH + "s | %-" + ROLE_WIDTH + "s | %-" + BALANCE_WIDTH + "s | %-" + STATUS_WIDTH + "s | %-" + LEAGUE_WIDTH + "s |%n",
                "USERNAME", "ROLE", "BALANCE", "STATUS", "LEAGUE"); 
        System.out.println(separator);

       
        for (User u : users) {
            String status = u.isOnline() ? "ONLINE" : "OFFLINE";
            
            System.out.printf("| %-" + NAME_WIDTH + "s | %-" + ROLE_WIDTH + "s | $%" + (BALANCE_WIDTH - 1) + ".2f | %-" + STATUS_WIDTH + "s | %-" + LEAGUE_WIDTH + "s |%n",
                    u.getUsername(), 
                    u.getRole().toString(),
                    u.getBalance(),
                    status,
                    u.getLeague()); 
        }
        System.out.println(separator);
    }
}