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
import com.mycompany.kdafragbet.domain.Bet;
import java.util.List;

public class AllBetsPage {
    BettingManager bettingManager;

    public AllBetsPage(BettingManager bettingManager) { this.bettingManager = bettingManager; }

    public void display() {
        List<Bet> bets = bettingManager.getAllBets();
        System.out.println("\n=== ALL BETS ===");
        if (bets.isEmpty()) System.out.println("No bets found.");
        else bets.forEach(System.out::println);
    }

    public void displayActiveBets() {
        List<Bet> bets = bettingManager.getActiveBets();
        System.out.println("\n=== ACTIVE BETS ===");
        if (bets.isEmpty()) System.out.println("No active bets found.");
        else bets.forEach(System.out::println);
    }
}
