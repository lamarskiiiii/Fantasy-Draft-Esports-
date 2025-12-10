/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.repository;

/**
 *
 * @author Administrator
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */

import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.Bet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BettingManager {
 
    List<Bet> bets = new ArrayList<>();

    public void placeBet(Bet bet) {
        bets.add(bet);
        
        System.out.println("Individual bet registered: " + bet.getId());
    }

  
    public void placeTeamBet(Bet bet) {
        bets.add(bet);
        
        System.out.println("Team bet registered: " + bet.getId());
    }

    public List<Bet> getAllBets() {
        return bets;
    }

    public List<Bet> getPendingBets() {
        
        return bets.stream()
                .filter(b -> !b.isApproved() && !b.isResolved())
                .collect(Collectors.toList());
    }

   
    public List<Bet> getActiveBets() {
       
        return bets.stream()
                .filter(Bet::isApproved)
                .filter(b -> !b.isResolved())
                .collect(Collectors.toList());
    }
    
    
    public void removeBet(Bet bet) {
      
        bets.remove(bet);
    }
    
   
    public List<Bet> getActiveBetsForUser(User user) {
        List<Bet> out = new ArrayList<>();
        for (Bet b : bets) if (b.getUser() == user && b.isApproved() && !b.isResolved()) out.add(b);
        return out;
    }

    public void viewAllBets() {
        if (bets.isEmpty()) {
            System.out.println("No bets found.");
            return;
        }
        System.out.println("\n=== ALL BETS ===");
        
        bets.forEach(System.out::println);
    }

    public void viewActiveBets() {
        List<Bet> active = getActiveBets();
        if (active.isEmpty()) {
            System.out.println("No active bets.");
            return;
        }
        System.out.println("\n=== ACTIVE BETS ===");
        active.forEach(System.out::println);
    }

    public boolean approveBet(int id) {
        for (Bet b : bets) {
            if (b.getId() == id) { 
                if (b.isApproved()) {
                    System.out.println("Bet already approved.");
                    return false;
                }
                b.setApproved(true);
                System.out.println("Approved: " + b);
                return true;
            }
        }
        System.out.println("Bet ID not found.");
        return false;
    }

    public boolean cancelBet(int id) {
        for (int i = 0; i < bets.size(); i++) {
            Bet betToCancel = bets.get(i);
            if (betToCancel.getId() == id) {
                
           
                System.out.println("Cancelled: " + bets.remove(i));
                return true;
            }
        }
        System.out.println("Bet ID not found.");
        return false;
    }

    public void resolveAllApproved(double threshold) {
        for (Bet b : bets) {
            if (b.isApproved() && !b.isResolved()) b.resolve(threshold);
        }
        System.out.println("Resolution logic executed for approved bets.");
    }

    public void clearBets() {
        int count = bets.size();
        bets.clear();
        System.out.println("Cleared " + count + " bets.");
    }
}

