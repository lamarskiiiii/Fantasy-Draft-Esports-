/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow; // Assuming this package based on your imports

import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.domain.Bet;
import java.util.List;
import java.util.Scanner;

public class BetManagement {
    
    private final BettingManager bettingManager;
    private final Scanner scanner;

    public BetManagement(AppContext context) {
        this.bettingManager = context.bettingManager;
        this.scanner = context.sc;
    }

    public void display() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MANAGE BETS ===");
            System.out.println("1. Review Pending Bets (Approve/Reject)");
            System.out.println("2. View Active Bets");
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
                case 1 -> reviewPendingBets();
                case 2 -> viewActiveBets();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void reviewPendingBets() {
        List<Bet> pendingBets = bettingManager.getPendingBets();
        if (pendingBets.isEmpty()) {
            System.out.println("No pending bets to review.");
            return;
        }

        System.out.println("\n--- Pending Bets ---");
        
        for (int i = 0; i < pendingBets.size(); i++) {
            Bet b = pendingBets.get(i);

            System.out.printf("%d. BetID: %s | User: %s | Amount: %.2f | Predicted Winner: %s%n",
                i + 1, b.getBetId(), b.getUser().getUsername(), b.getAmount(), b.getPredictedWinner().getUsername());
        }

        System.out.print("Enter number to manage (or 0 to cancel): ");
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Operation cancelled.");
            return;
        }

        if (index >= 0 && index < pendingBets.size()) {
            Bet bet = pendingBets.get(index);
            System.out.printf("Manage Bet %s. (A)pprove, (R)eject, or (C)ancel? ", bet.getBetId());
            String action = scanner.nextLine().toUpperCase();

            if (action.equals("A")) {
                bet.setApproved(true);
                System.out.println("Bet approved and is now active.");
            } else if (action.equals("R")) {
             
                bet.getUser().deposit(bet.getAmount()); 
                bettingManager.removeBet(bet); 
                System.out.println("Bet rejected and amount returned to user. Bet removed from list.");
            } else {
                System.out.println("Operation cancelled.");
            }
        } else if (index != -1) {
            System.out.println("Invalid bet number.");
        }
    }
    
    private void viewActiveBets() {
         List<Bet> activeBets = bettingManager.getActiveBets();
         if (activeBets.isEmpty()) {
             System.out.println("No active (approved and unresolved) bets.");
             return;
         }
         System.out.println("\n--- Active Bets ---");
         activeBets.forEach(b -> 
             System.out.printf("BetID: %s | User: %s | Amount: %.2f | Winner: %s%n",
                 b.getBetId(), b.getUser().getUsername(), b.getAmount(), b.getPredictedWinner().getUsername()));
    }
}