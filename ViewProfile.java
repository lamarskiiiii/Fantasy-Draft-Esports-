/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.workflow;

/**
 *
 * @author Administrator
 */

//betting profile
//page is used for ineheritance
import com.mycompany.kdafragbet.ui.Page;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.domain.Bet;
import java.util.List;

public class ViewProfile implements Page {

    BettingManager bettingManager;

    public ViewProfile(BettingManager bettingManager) {
        this.bettingManager = bettingManager;
    }

    @Override
    public void display(User user) {
        showBasicInfo(user);
        showDraftedPlayers(user);
        showActiveBets(user);
    }

    // Display username, age, balance
    private void showBasicInfo(User user) {
        System.out.println("\n=== USER PROFILE ===");
        System.out.println("Username: " + user.getUsername());
        System.out.println("UserID: " + user.getUserId());
        System.out.println("Age: " + user.getAge());
        System.out.println("Balance: $" + user.getBalance());
        
    }

    // Display drafted players
    private void showDraftedPlayers(User user) {
        List<FantasyPlayer> drafted = user.getDraftedPlayers();
        System.out.println("\nDrafted Players:");
        if (drafted.isEmpty()) {
            System.out.println(" - None");
        } else {
            drafted.forEach(p -> System.out.println(" - " + p));
        }
    }

    // Display user's active bets
    private void showActiveBets(User user) {
        List<Bet> activeBets = bettingManager.getActiveBets();
        System.out.println("\nActive Bets:");

        boolean hasBets = activeBets.stream()
                .anyMatch(b -> b.getUser().equals(user));

        if (!hasBets) {
            System.out.println(" - None");
        } else {
            activeBets.stream()
                    .filter(b -> b.getUser().equals(user))
                    .forEach(b -> System.out.println(" - " + b));
        }
    }
}
