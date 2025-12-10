/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.ui.workflow;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.service.draft.CSGODraft;
import com.mycompany.kdafragbet.service.draft.ValorantDraft;
import com.mycompany.kdafragbet.service.draft.DraftEngine;
import com.mycompany.kdafragbet.service.draft.Draft;
import com.mycompany.kdafragbet.repository.ServerManager;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.domain.Server;
import com.mycompany.kdafragbet.AppContext;
import java.util.List;
import java.util.Scanner;

public class AdminSimulation {
    private final AppContext context;

    public AdminSimulation(AppContext context) {
        this.context = context;
    }

    public void run() {
        System.out.println("\n=== ADMIN SIMULATION START ===");
        
        Scanner sc = context.sc;
        UserManager userManager = context.userManager;
        ServerManager serverManager = context.serverManager;

     
        int gameChoice = chooseGame();
        Draft draft = (gameChoice == 1) ? new ValorantDraft(sc) : new CSGODraft(sc);

        // Use all users in system
        List<User> allUsers = userManager.getAllUsers();
        if (allUsers.isEmpty()) {
            System.out.println("No users to simulate.");
            return;
        }

        
        Server server = serverManager.createPrivateServer(gameChoice);
        allUsers.forEach(server::addMember);
        System.out.println("Simulation server created with " + allUsers.size() + " users.");

    
        DraftEngine draftEngine = new DraftEngine(context);
        draftEngine.startDraft(server, draft, userManager); 

        System.out.println("\n✅ Admin Simulation Completed!");
    }

    private int chooseGame() {
        System.out.println("Choose Game:");
        System.out.println("1. Valorant");
        System.out.println("2. CS:GO");
        System.out.print("Choice: ");
        while (true) {
            try {
             return Integer.parseInt(context.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}