/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.ui.Page;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.domain.Bet;
import java.util.List;
import java.util.Scanner;

public class PlayerBettingPage implements Page {
    Scanner sc;
    BettingManager bettingManager;

    public PlayerBettingPage(Scanner sc, BettingManager bettingManager) {
        this.sc = sc;
        this.bettingManager = bettingManager;
    }

    @Override
    public void display(User user) {
        List<FantasyPlayer> drafted = user.getDraftedPlayers();
        if (drafted.isEmpty()) {
            System.out.println("You have no drafted players. You must draft a team before placing a team bet.");
            return;
        }

        System.out.println("\n=== BET ON YOUR ENTIRE DRAFTED TEAM ===");
        System.out.println("Your team consists of " + drafted.size() + " players:");
        
        for (FantasyPlayer player : drafted) {
            System.out.println("- " + player.getName()); 
        }
        
        double amount = enterBetAmount(user);
        if (amount <= 0) return; 
        FantasyPlayer teamPlaceholder = drafted.get(0); 


        user.withdraw(amount);
        
        Bet bet = new Bet(user, teamPlaceholder, amount); 
        
        
        bettingManager.placeTeamBet(bet); 
        
        System.out.println("\n--- Bet Placed ---");
        System.out.printf("Team Bet placed (pending approval) for $%.2f.%n", amount);
        System.out.printf("New Balance: $%.2f%n", user.getBalance());
    }

    
    private double enterBetAmount(User user) {
        while (true) {
            System.out.print("Enter team bet amount (Balance: $" + String.format("%.2f", user.getBalance()) + "): $");
            String input = sc.nextLine();
            double amt;

            try {
                amt = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Enter a number.");
                continue;
            }

            if (amt <= 0) {
                System.out.println("Amount must be greater than 0.");
                continue;
            }

            if (amt > user.getBalance()) {
                System.out.println("Not enough balance.");
                continue;
            }

            return amt;
        }
    }
}