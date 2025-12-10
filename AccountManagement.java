/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow; 

import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.domain.User;
import java.util.Optional;
import java.util.Scanner;

public class AccountManagement {
    
    private final UserManager userManager;
    private final Scanner scanner;

    public AccountManagement(AppContext context) {
        this.userManager = context.userManager;
        this.scanner = context.sc;
    }

    public void display() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MANAGE ACCOUNTS ===");
            System.out.println("1. View All Users");
            System.out.println("2. Edit User Balance/Role");
            System.out.println("3. Delete User");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> viewAllUsers();
                case 2 -> editUser();
                case 3 -> deleteUser();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewAllUsers() {
        System.out.println("\n--- Registered Users ---");
        if (userManager.getAllUsers().isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        
        System.out.printf("%-6s | %-15s | %-8s | %-10s%n", "ID", "Username", "Role", "Balance");
        System.out.println("----------------------------------------------");
        
        userManager.getAllUsers().forEach(u -> 
            System.out.printf("%-6s | %-15s | %-8s | %.2f%n", 
                u.getUserId(), u.getUsername(), u.getRole(), u.getBalance()));
    }

    private void editUser() {
        System.out.print("Enter User ID or Username to edit: ");
        String identifier = scanner.nextLine();
        
     
        Optional<User> userOpt = userManager.findUserById(identifier)
            .or(() -> userManager.findUserByUsername(identifier));

        if (userOpt.isEmpty()) {
            System.out.println("User not found.");
            return;
        }

        User user = userOpt.get();
        System.out.printf("\nEditing User: %s (Current Role: %s, Balance: %.2f)%n", 
                          user.getUsername(), user.getRole(), user.getBalance());
        
      
        System.out.print("Change Role to (ADMIN/USER, or N/A to skip): ");
        String roleInput = scanner.nextLine().toUpperCase();
        try {
            if (!roleInput.equals("N/A")) {
                user.setRole(User.Role.valueOf(roleInput));
                System.out.println("Role updated.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role. Skipping role update.");
        }

       
        System.out.print("Enter amount to ADD/SUBTRACT from balance (e.g., 50.00 or -20.00, 0 to skip): ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0) {
                user.deposit(amount);
                System.out.printf("Deposited %.2f.%n", amount);
            } else if (amount < 0) {
                user.withdraw(Math.abs(amount));
                System.out.printf("Withdrew %.2f.%n", Math.abs(amount));
            }
            System.out.printf("Balance updated. New Balance: %.2f%n", user.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Skipping balance update.");
        }
    }

 

private void deleteUser() {
    System.out.print("Enter User ID or Username to DELETE: ");
    String identifier = scanner.nextLine();
    
    
    Optional<User> userOpt = userManager.findUserById(identifier)
        .or(() -> userManager.findUserByUsername(identifier));

    if (userOpt.isEmpty()) {
        System.out.println("User not found with that ID or Username.");
        return;
    }

    User user = userOpt.get();
    System.out.printf("CONFIRM DELETION of user %s (ID: %s)? This cannot be undone. (yes/no): ", 
                      user.getUsername(), user.getUserId());
    String confirmation = scanner.nextLine().toLowerCase();

    if (confirmation.equals("yes")) {
       
        boolean deleted = userManager.removeUser(user.getUserId());

        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
         
            System.out.println("ERROR: Could not delete user. The user was found, but the repository operation failed.");
        }
    } else {
        System.out.println("Operation cancelled.");
    }
}
}