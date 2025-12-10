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
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.workflow.JoinLeague;
import com.mycompany.kdafragbet.workflow.ViewProfile;
import com.mycompany.kdafragbet.ui.workflow.ViewPlayer;
import java.util.Scanner;

public class UserMenu {

    private final AppContext context;
    private final UserTransactionHelper transactionHelper; 

    public UserMenu(AppContext context) {
        this.context = context;
        
        this.transactionHelper = new UserTransactionHelper(context); 
    }

    public void display(User user) {
        Scanner sc = context.sc;

        while (true) {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1. Join League");
            System.out.println("2. View Profile");
            System.out.println("3. View Players");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> new JoinLeague(context.sc, context).display(user); 
                case 2 -> new ViewProfile(context.bettingManager).display(user); 
                case 3 -> new ViewPlayer(context).display(user);
                case 4 -> depositMoney(user);
                case 5 -> withdrawMoney(user);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void depositMoney(User user) {
       
        String transactionMethod = transactionHelper.chooseDepositMethod();
        if (transactionMethod == null) return;
        
       
        double amount = transactionHelper.chooseAmount(); 
        if (amount <= 0) return; 
        
        String transactionType = null;
        
        
        if (transactionMethod.equals("E-Wallet")) {
            
            transactionType = transactionHelper.processEWalletDeposit(user, amount);
        } else if (transactionMethod.equals("Bank")) {
       
            transactionType = transactionHelper.processBankDeposit(user, amount);
        }
       
        if (transactionType != null) {
            user.deposit(amount);
            System.out.printf("Successfully deposited $%.2f via %s. New balance: $%.2f%n", amount, transactionType, user.getBalance());
        }
    }

    private void withdrawMoney(User user) {
        
        String transactionType = transactionHelper.chooseWithdrawalMethod();
        
        if (transactionType == null) return;

        double amount = transactionHelper.chooseAmount(); 
        
        if (amount > 0) {
            if (amount <= user.getBalance()) {
                user.withdraw(amount);
                System.out.printf("Successfully withdrew $%.2f via %s. New balance: $%.2f%n", amount, transactionType, user.getBalance());
            } else {
                System.out.println("Insufficient balance. Transaction cancelled.");
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