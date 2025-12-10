/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.workflow;

/**
 *
 * @author Administrator
 */



import com.mycompany.kdafragbet.AppContext;
import com.mycompany.kdafragbet.ui.Page;
import com.mycompany.kdafragbet.service.draft.CSGODraft;
import com.mycompany.kdafragbet.service.draft.ValorantDraft;
import com.mycompany.kdafragbet.service.draft.DraftEngine;
import com.mycompany.kdafragbet.service.draft.Draft;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.Server;
import java.util.Optional;
import java.util.Scanner;


public class JoinLeague implements Page {

    private final AppContext context;

    public JoinLeague(Scanner sc,AppContext context) {
        this.context = context;
    }

    @Override
    public void display(User user) {
        System.out.println("\n=== JOIN LEAGUE ===");
        System.out.println("Choose Server Type:");
        System.out.println("1. Public Server");
        System.out.println("2. Join Private Server");
        System.out.println("3. Create Private Server");
        System.out.print("Choice: ");

        int serverChoice = getIntInput();
        Server server;

        switch (serverChoice) {
            case 1 -> { 
                server = context.serverManager.getPublicServer();
                if (server == null) {
                    server = context.serverManager.createPublicServer(chooseGame());
                }
                System.out.println("Joined public server: " + server.getName());
            }
            case 2 -> { 
                System.out.print("Enter private server code: ");
                String code = context.sc.nextLine().trim();
                Optional<Server> optional = context.serverManager.getPrivateServer(code);
                if (optional.isEmpty()) {
                    System.out.println("No private server found with that code.");
                    return;
                }
                server = optional.get();
            }
            case 3 -> { 
                server = context.serverManager.createPrivateServer(chooseGame());
                System.out.println("Private server created! Code: " + server.getPrivateCode());
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }


server.addMember(user);

String gameName = server.getGameChoice() == 1 ? "Valorant" : "CS:GO";
user.setLeague(gameName); 
System.out.println(user.getUsername() + " joined " + server.getName() + (server.isPrivate() ? " | Code: " + server.getPrivateCode() : ""));
System.out.println("Total members: " + server.getMemberCount());
Draft draft = (server.getGameChoice() == 1) ? new ValorantDraft(context.sc) : new CSGODraft(context.sc);

    }
    
    
    private int chooseGame() {
        System.out.println("Choose Game League:");
        System.out.println("1. Valorant");
        System.out.println("2. CS:GO");
        System.out.print("Choice: ");
        return getIntInput();
    }

    private int getIntInput() {
        while (true) {
            try {
               
                return Integer.parseInt(context.sc.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}