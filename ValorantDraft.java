/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service.draft;

/**
 *
 * @author Administrator
 */


import com.mycompany.kdafragbet.service.draft.Draft;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ValorantDraft extends Draft {

    private Scanner sc;

    // Define position limits
    private static final Map<String, Integer> POSITION_LIMITS = Map.of(
        "Duelist", 2,
        "Initiator", 2,
        "Sentinel", 1,
        "Controller", 2
    );

    public ValorantDraft(Scanner sc) {
        super(new ArrayList<>());
        this.sc = sc;
        populatePlayers();
    }
    
    @Override
    protected Map<String, Integer> getPositionLimits() {
        return POSITION_LIMITS;
    }
    
    private void populatePlayers() {
availablePlayers.add(new FantasyPlayer("aspas", "Duelist", 1.89, 262.0, 21.2, 12.8, 2.9));
availablePlayers.add(new FantasyPlayer("xenom", "Controller", 1.86, 155.0, 0.0, 0.0, 0.0));
availablePlayers.add(new FantasyPlayer("Verno", "Initiator", 1.72, 199.0, 15.8, 12.7, 7.3));
availablePlayers.add(new FantasyPlayer("brawk", "Controller", 1.7, 224.0, 16.1, 13.8, 7.5));
availablePlayers.add(new FantasyPlayer("Smoggy", "Duelist", 1.67, 206.0, 0.0, 0.0, 0.0));
availablePlayers.add(new FantasyPlayer("artzin", "Sentinel", 1.64, 202.0, 14.6, 13.9, 8.2));
availablePlayers.add(new FantasyPlayer("MaKo", "Controller", 1.64, 172.0, 13.2, 12.9, 8.0));
availablePlayers.add(new FantasyPlayer("cortezia", "Sentinel", 1.63, 196.0, 15.8, 12.4, 4.5));
availablePlayers.add(new FantasyPlayer("RieNs", "Duelist", 1.61, 226.0, 16.9, 14.8, 7.0));
availablePlayers.add(new FantasyPlayer("forsakeN", "Duelist", 1.61, 213.0, 16.6, 16.0, 9.3));
availablePlayers.add(new FantasyPlayer("johnqt", "Duelist", 1.58, 187.0, 14.1, 12.5, 5.6));
availablePlayers.add(new FantasyPlayer("skuba", "Sentinel", 1.57, 190.0, 15.6, 13.4, 5.5));
availablePlayers.add(new FantasyPlayer("Ethan", "Initiator", 1.57, 176.0, 13.2, 14.2, 9.2));
availablePlayers.add(new FantasyPlayer("something", "Duelist", 1.56, 220.0, 17.5, 15.0, 5.8));
availablePlayers.add(new FantasyPlayer("Flickless", "Initiator", 1.53, 174.0, 11.2, 13.4, 9.3));
availablePlayers.add(new FantasyPlayer("Boo", "Controller", 1.52, 144.0, 10.6, 11.2, 14.3));
availablePlayers.add(new FantasyPlayer("valyn", "Controller", 1.52, 192.0, 14.4, 14.9, 8.3));
availablePlayers.add(new FantasyPlayer("happywel", "Controller", 1.51, 216.0, 16.8, 15.8, 7.1));
availablePlayers.add(new FantasyPlayer("leaf", "Sentinel", 1.5, 223.0, 17.7, 15.6, 5.7));
availablePlayers.add(new FantasyPlayer("Cloud", "Initiator", 1.5, 202.0, 13.7, 13.5, 6.5));
availablePlayers.add(new FantasyPlayer("s0m", "Controller", 1.49, 197.0, 15.1, 15.6, 8.2));
availablePlayers.add(new FantasyPlayer("kaajak", "Duelist", 1.48, 228.0, 16.9, 14.1, 4.0));
availablePlayers.add(new FantasyPlayer("Chronicle", "Initiator", 1.48, 204.0, 14.8, 13.8, 5.6));
availablePlayers.add(new FantasyPlayer("NoMan", "Duelist", 1.47, 221.0, 17.5, 14.8, 4.2));
availablePlayers.add(new FantasyPlayer("Monyet", "Controller", 1.44, 179.0, 12.8, 13.2, 5.8));
availablePlayers.add(new FantasyPlayer("Jinggg", "Duelist", 1.43, 224.0, 17.3, 16.0, 4.7));
availablePlayers.add(new FantasyPlayer("d4v41", "Sentinel", 1.43, 187.0, 14.8, 14.3, 5.8));
availablePlayers.add(new FantasyPlayer("SpiritZ1", "Duelist", 1.43, 237.0, 20.0, 19.5, 8.0));
availablePlayers.add(new FantasyPlayer("DH", "Controller", 1.43, 186.0, 15.8, 17.6, 9.5));
availablePlayers.add(new FantasyPlayer("Viva", "Initiator", 1.42, 166.0, 13.4, 14.0, 6.5));
availablePlayers.add(new FantasyPlayer("Alfajer", "Sentinel", 1.41, 212.0, 15.7, 13.8, 3.7));
availablePlayers.add(new FantasyPlayer("Crashies", "Initiator", 1.41, 179.0, 12.8, 13.2, 5.8));
availablePlayers.add(new FantasyPlayer("N4RRATE", "Iniatior", 1.4, 190.0, 13.1, 15.1, 8.1));
availablePlayers.add(new FantasyPlayer("BeYN", "Duelist", 1.4, 171.0, 13.0, 13.3, 5.7));
availablePlayers.add(new FantasyPlayer("JonahP", "Sentinel", 1.38, 173.0, 12.9, 15.3, 8.2));
availablePlayers.add(new FantasyPlayer("Flex1n", "Duelist", 1.38, 185.0, 15.2, 18.0, 9.7));
availablePlayers.add(new FantasyPlayer("HYUNMIN", "Duelist", 1.38, 213.0, 16.4, 14.8, 4.1));
availablePlayers.add(new FantasyPlayer("Boaster", "Controller", 1.37, 145.0, 10.0, 12.9, 7.8));
availablePlayers.add(new FantasyPlayer("Knight", "Initiator", 1.37, 197.0, 13.0, 13.4, 5.4));
availablePlayers.add(new FantasyPlayer("jawgemo", "Duelist", 1.36, 230.0, 17.8, 16.3, 4.5));
availablePlayers.add(new FantasyPlayer("nephh", "Initiator", 1.35, 164.0, 10.6, 12.0, 5.6));
availablePlayers.add(new FantasyPlayer("Flashback", "Sentinel", 1.35, 223.0, 17.2, 15.3, 3.5));
availablePlayers.add(new FantasyPlayer("trent", "Initiator", 1.34, 173.0, 13.8, 14.1, 5.2));
availablePlayers.add(new FantasyPlayer("nAts", "Sentinel", 1.34, 194.0, 14.5, 13.7, 3.8));
availablePlayers.add(new FantasyPlayer("zekken", "Duelist", 1.33, 229.0, 15.6, 16.6, 6.5));
availablePlayers.add(new FantasyPlayer("Nicc", "Initiator", 1.33, 173.0, 16.0, 17.0, 6.7));
availablePlayers.add(new FantasyPlayer("mada", "Duelist", 1.32, 214.0, 16.7, 15.7, 4.1));
availablePlayers.add(new FantasyPlayer("ZmjjKK", "Duelist", 1.32, 237.0, 17.6, 16.2, 9.6));
availablePlayers.add(new FantasyPlayer("coconut", "Duelist", 1.32, 150.0, 11.2, 16.0, 9.8));
availablePlayers.add(new FantasyPlayer("grubinho", "Iniator", 1.31, 169.0, 12.0, 15.0, 7.8));
availablePlayers.add(new FantasyPlayer("IZU", "Duelist", 1.29, 212.0, 18.8, 17.3, 3.6));
availablePlayers.add(new FantasyPlayer("CHICHOO", "Sentinel", 1.28, 158.0, 14.0, 15.6, 6.0));
availablePlayers.add(new FantasyPlayer("keiko", "Controller", 1.28, 200.0, 14.4, 15.4, 5.4));
availablePlayers.add(new FantasyPlayer("Woot", "Duelist", 1.27, 210.0, 16.0, 15.2, 3.3));
availablePlayers.add(new FantasyPlayer("westside", "Duelist", 1.27, 187.0, 13.9, 13.9, 3.7));
availablePlayers.add(new FantasyPlayer("Demon1", "Duelist", 1.26, 175.0, 16.0, 15.7, 4.0));
availablePlayers.add(new FantasyPlayer("Rarga", "Duelist", 1.26, 209.0, 16.4, 16.7, 4.7));
availablePlayers.add(new FantasyPlayer("BuZz", "Duelist", 1.25, 190.0, 17.1, 18.6, 6.1));
availablePlayers.add(new FantasyPlayer("Jemkin", "Duelist", 1.25, 215.0, 14.5, 13.7, 2.5));
availablePlayers.add(new FantasyPlayer("paTiTek", "Initiator", 1.24, 157.0, 11.1, 15.1, 7.7));
availablePlayers.add(new FantasyPlayer("Kushy", "Initiator", 1.24, 172.0, 11.8, 13.4, 3.8));
availablePlayers.add(new FantasyPlayer("bang", "Controller", 1.22, 141.0, 11.1, 14.1, 6.1));
availablePlayers.add(new FantasyPlayer("nobody", "Sentinel", 1.22, 171.0, 14.8, 15.2, 3.8));
availablePlayers.add(new FantasyPlayer("Levius", "Sentinel", 1.22, 188.0, 13.4, 13.6, 3.2));
availablePlayers.add(new FantasyPlayer("benjyfishy", "Sentinel", 1.21, 175.0, 12.6, 15.3, 5.9));
availablePlayers.add(new FantasyPlayer("ara", "Duelist", 1.19, 199.0, 14.5, 14.3, 2.6));
availablePlayers.add(new FantasyPlayer("Meteor", "Sentinel", 1.19, 219.0, 18.3, 19.0, 4.3));
availablePlayers.add(new FantasyPlayer("freeing", "Initiator", 1.19, 160.0, 12.2, 14.0, 4.4));
availablePlayers.add(new FantasyPlayer("crazyguy", "Initiator", 1.19, 152.0, 9.5, 13.4, 6.4));
availablePlayers.add(new FantasyPlayer("xffero", "Controller", 1.17, 175.0, 12.0, 13.7, 7.8));
availablePlayers.add(new FantasyPlayer("MiniBoo", "Duelist", 1.16, 204.0, 15.2, 16.0, 3.4));
availablePlayers.add(new FantasyPlayer("Zellsis", "Initiator", 1.15, 152.0, 11.3, 14.6, 5.6));
availablePlayers.add(new FantasyPlayer("Patmen", "Initiator", 1.15, 158.0, 12.2, 14.6, 4.7));
availablePlayers.add(new FantasyPlayer("stax", "Initiator", 1.12, 146.0, 12.1, 17.6, 7.6));
availablePlayers.add(new FantasyPlayer("Jieni7", "Duelist", 1.11, 192.0, 15.4, 18.6, 5.4));
availablePlayers.add(new FantasyPlayer("kamo", "Duelist", 1.11, 190.0, 14.2, 15.1, 2.5));
availablePlayers.add(new FantasyPlayer("trexx", "Duelist", 1.1, 160.0, 10.8, 14.8, 5.5));
availablePlayers.add(new FantasyPlayer("Akeman", "Duelist", 1.09, 197.0, 17.0, 19.2, 4.0));
availablePlayers.add(new FantasyPlayer("rushia", "Controller", 1.01, 148.0, 8.6, 14.6, 6.2));
availablePlayers.add(new FantasyPlayer("whzy", "Duelist", 0.87, 155.0, 9.8, 16.0, 4.2));
    }

    @Override
    public void startDraft(User user) {
        System.out.println("\n=== Valorant Draft ===");

        while (user.getDraftedPlayers().size() < 7 && !availablePlayers.isEmpty()) {
            showAvailablePlayers();
            System.out.println("Drafted " + user.getDraftedPlayers().size() + "/7 players.");
            System.out.print("Choose a player (0 to cancel): ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
                continue;
            }
            if (choice == 0) break;

            
            if (pickPlayer(user, choice)) { 
              
            } else {
               
            }
        }

        System.out.println(user.getUsername() + " has finished drafting their players!");
    }
    
   

    @Override
    public void startDraft(List<User> users) {
        for (User u : users) startDraft(u);
    }
}