/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service.draft;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.ui.workflow.PostDraftBetting;
import com.mycompany.kdafragbet.service.draft.Draft;
import com.mycompany.kdafragbet.service.GameSimulation;
import com.mycompany.kdafragbet.service.DraftOrderManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.domain.Server;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DraftEngine {

    private final AppContext context;

    public DraftEngine(AppContext context) {
        this.context = context;
    }

   
    public void startDraft(Server server, Draft draft, UserManager userManager) {
        System.out.println("\n=== SERVER DRAFT STARTED ===");

        List<User> members = server.getMembers();
        if (members.isEmpty()) {
            System.out.println("❌ No members in server. Cannot start draft.");
            return;
        }

       
        List<String> usernames = members.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        DraftOrderManager orderManager = new DraftOrderManager(usernames);

               String nextName = orderManager.getNextUser(); 


        while (!draft.isDraftComplete() &&
                members.stream().anyMatch(u -> u.getDraftedPlayers().size() < 7)) {

            User currentUser = null;
            
           
            if (nextName != null) {
                for (User u : members) {
                    if (nextName.equals(u.getUsername())) {
                        currentUser = u;
                        break;
                    }
                }
            }

            
            if (currentUser == null) {
                System.out.println("⚠ User '" + nextName + "' not found or name is null. Advancing turn.");
                nextName = orderManager.getNextUser(); 
                continue; 
            }
            
         
            if (currentUser.getDraftedPlayers().size() >= 7) {
                System.out.println("✅ " + currentUser.getUsername() + " has a full team. Advancing turn.");
                nextName = orderManager.getNextUser(); 
                continue;
            }

            
            System.out.println("\n➡ " + currentUser.getUsername() + " is drafting!");
            draft.showAvailablePlayers();

            System.out.print("Pick player ID (0 to skip): ");
            int pickId;
            try {
                pickId = Integer.parseInt(context.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue; // Stays on current user
            }

            if (pickId == 0) {
                System.out.println("Skipping pick...");
                nextName = orderManager.getNextUser(); // Advance turn
                continue;
            }

           
            boolean pickSuccessful = draft.pickPlayer(currentUser, pickId);
            
            if (pickSuccessful) {
                nextName = orderManager.getNextUser(); // Advance turn only on success
            }
           
        }

        System.out.println("\n🎉 ALL PICKS COMPLETED! 🎉");

        // POST-DRAFT BETTING
        for (User u : members) {
            new PostDraftBetting(context.sc, context.bettingManager).start(u);
        }

        // GAME SIMULATION
        User admin = userManager.getUser("admin");
        if (admin == null) {
            System.out.println("⚠ No admin found! Using temporary admin for simulation.");
        }

        GameSimulation sim = new GameSimulation(context.bettingManager, admin);
        sim.runSimulation(members, server.getGameChoice());

        System.out.println("\n🏁 GAME SIMULATION COMPLETED!");
    }
}