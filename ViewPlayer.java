/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow;


import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.service.draft.ValorantDraft;
import com.mycompany.kdafragbet.service.draft.CSGODraft; 
import com.mycompany.kdafragbet.ui.Page;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import java.util.List;
import java.util.Scanner;

public class ViewPlayer implements Page {

    
    private final AppContext context; 
    
    
    public ViewPlayer(AppContext context) {
        this.context = context;
    }

    @Override
    public void display(User currentUser) {
        Scanner sc = context.sc;
        
        while (true) {
            System.out.println("\n=== VIEW PLAYERS ===");
            System.out.println("Which game's players would you like to view?");
            System.out.println("1. Valorant Players");
            System.out.println("2. CS:GO Players");
            System.out.println("3. Back to User Menu");
            System.out.print("Choice: ");

            String input = sc.nextLine().trim();

            switch (input) {
              
                case "1" -> showPlayerList(new ValorantDraft(sc).getAvailablePlayers(), "Valorant");
                case "2" -> showPlayerList(new CSGODraft(sc).getAvailablePlayers(), "CS:GO");
                case "3" -> {
                    return; 
                }
                default -> System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }
    

    private void showPlayerList(List<FantasyPlayer> availablePlayers, String gameName) {
        Scanner sc = context.sc;
        System.out.println("\n=== AVAILABLE " + gameName.toUpperCase() + " PLAYERS ===");

        if (availablePlayers == null || availablePlayers.isEmpty()) {
            System.out.println("No players available for " + gameName + " at the moment.");
            System.out.println("Press Enter to go back.");
            sc.nextLine(); 
            return;
        }

        // Display formatted header
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-20s | %-10s | %-5s | %-8s | %-5s | %-5s | %-5s |%n",
                "No", "Player Name", "Role", "KDA", "ACS/MAP", "K/MAP", "A/MAP", "D/MAP");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (int i = 0; i < availablePlayers.size(); i++) {
            FantasyPlayer p = availablePlayers.get(i);
            // Use your original printf format
            System.out.printf("| %-2d | %-20s | %-10s | %-5.2f | %-8.1f | %-5.1f | %-5.1f | %-5.1f |%n",
                    i + 1,
                    p.getName(),
                    p.getPosition(),
                    p.getKda(),
                    p.getAcsPerMap(),
                    p.getKillsPerMap(),
                    p.getAssistsPerMap(),
                    p.getDeathsPerMap());
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

        System.out.println("\nPress Enter to go back.");
        sc.nextLine();
    }
}