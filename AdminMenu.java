/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.menu;

/**
 *
 * @author Administrator
 */


import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.service.Admin;
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.ui.workflow.AdminSimulation;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.repository.ServerManager;
import com.mycompany.kdafragbet.ui.workflow.AccountManagement;
import com.mycompany.kdafragbet.ui.workflow.BetManagement;
import java.util.Scanner;


public class AdminMenu {
    private final AppContext context;

    public AdminMenu(AppContext context) {
        this.context = context;
    }

    public void display(User admin) {
        AccountManagement accountMenu = new AccountManagement(context);
        BetManagement betMenu = new BetManagement(context);
        
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View Users");
            System.out.println("2. View All Bets");
            System.out.println("3. Manage Accounts"); 
            System.out.println("4. Manage Bet"); 
            System.out.println("5. Run Admin Simulation");
            System.out.println("6. Logout");
            System.out.print("Choice: ");

            
            int choice = getIntInput();
            switch (choice) {
                case 1 -> new Admin(context.userManager, context.bettingManager).viewUsers();
                case 2 -> new Admin(context.userManager, context.bettingManager).viewAllBets();
                case 3 -> accountMenu.display(); 
                case 4 -> betMenu.display();    
                case 5 -> {
                    AdminSimulation simulation = new AdminSimulation(context);
                    simulation.run();
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return; 
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    
    
    private int getIntInput() {
        while (true) {
            try {
                
                return Integer.parseInt(context.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Enter a valid number: ");
            }
        }
    }
}