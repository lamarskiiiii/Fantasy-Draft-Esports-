/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow;

/**
 *
 * @author Administrator
 */


import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import java.util.Scanner;

public class PostDraftBetting {
    Scanner sc;
    BettingManager bettingManager;

    public PostDraftBetting(Scanner sc, BettingManager bettingManager) {
        this.sc = sc;
        this.bettingManager = bettingManager;
    }

    public void start(User user) {
        System.out.println("\n=== POST-DRAFT BETTING ===");

        if (user.getBalance() <= 0) {
            deposit(user);
        }

        PlayerBettingPage page = new PlayerBettingPage(sc, bettingManager);
        page.display(user);
    }

   
    private void deposit(User user) {
        while (true) {
            System.out.print("Deposit amount: $");
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

            user.deposit(amt);
            System.out.println("Deposit successful. New balance: $" + user.getBalance());
            break;
        }
    }
}