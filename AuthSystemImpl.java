/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.menu;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.menu.AdminMenu;
import com.mycompany.kdafragbet.menu.UserMenu;
import java.util.Scanner;


public class AuthSystemImpl {

   
    private final AppContext context;

    public AuthSystemImpl(AppContext context) {
        this.context = context;
    }

    public void start() {
        Scanner sc = context.sc;

        while (true) {
            System.out.println("\n=== KDA FRAG BET ===");

            
            User.Role role = chooseRole(sc);
            if (role == null) continue; 
            
          
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> handleLogin(role);
                case "2" -> handleSignUp(role);
                case "3" -> {
                    System.out.println("Exiting application...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

  
    private User.Role chooseRole(Scanner sc) {
        System.out.println("Choose Role:");
        System.out.println("1. ADMIN");
        System.out.println("2. USER");
        System.out.print("Choice: ");

        String roleChoice = sc.nextLine().trim();
        return switch (roleChoice) {
            case "1" -> User.Role.ADMIN;
            case "2" -> User.Role.USER;
            default -> {
                System.out.println("Invalid role choice.");
                yield null;
            }
        };
    }

    
    private void handleLogin(User.Role role) {
        System.out.print("Enter username: ");
        
        String username = context.sc.nextLine().trim();

      
        User user = context.userManager.getUser(username);
        if (user == null || !user.getRole().equals(role)) { 
            System.out.println("Login failed: User not found or incorrect role.");
            return;
        }

        System.out.print("Enter password: ");
      
        String password = context.sc.nextLine().trim();

        if (user.getPassword().equals(password)){
        user.setOnline(true);
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            context.currentUser = user;
            redirectUser(user);
        } else {
            System.out.println("Login failed: Incorrect password.");
        }
    }

    private void handleSignUp(User.Role role) {
        
        Scanner sc = context.sc;

        System.out.print("Enter age: ");
        int age;
        try {
            age = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age.");
            return;
        }

        if (age <= 20) {
            System.out.println("You must be over 21 to sign up.");
            return;
        }

        System.out.print("Choose username: ");
        String username = sc.nextLine().trim();
        
       
        if (!isValidUsername(username)) {
            System.out.println("Invalid username. Must be at least 7 characters and contain a maximum of 2 numbers (0-9).");
            return;
        }
        
      
        if (context.userManager.userExists(username)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        User newUser = new User(username, age, 0.0, password, role);
       
        newUser.setOnline(true);
        context.userManager.addUser(newUser);
        context.currentUser = newUser;
        System.out.println("Account created successfully as " + role + "! Logged in as " + username + ".");

        redirectUser(newUser);
    }
    

    private boolean isValidUsername(String username) {
        if (username.length() < 7) {
            return false;
        }
        // Count digits by replacing all non-digits with an empty string
        String digitsOnly = username.replaceAll("[^0-9]", "");
        
        if (digitsOnly.length() > 2) {
            return false;
        }
        return true;
    }


    // ======================
    // REDIRECT BASED ON ROLE
    // ======================
    private void redirectUser(User user) {
        context.currentUser = user; // Ensure the context user is set here as well
        if (user.getRole().equals(User.Role.ADMIN)) { // Use .equals for robustness
            // AdminMenu call is correct and passes the 4 required dependencies and the User
            new AdminMenu(context).display(user);
        } else {
            // ✅ FIX: Assuming UserMenu's constructor and display method follow the same pattern
            // as AdminMenu for consistency and to resolve potential missing dependencies or user context.
            new UserMenu(context).display(user);
        }
    }
}