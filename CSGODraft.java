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
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class CSGODraft extends Draft {
    Scanner sc;

  
    private static final Map<String, Integer> POSITION_LIMITS = Map.of(
        "Entry", 2,
        "Support", 1,
        "AWPer", 1,
        "Lurker", 1,
        "Rifler", 1,
        "IGL", 1
    );

    public CSGODraft(Scanner sc) {
        super(new ArrayList<>());
        this.sc = sc;
        // Populate available players with average stats (content kept the same)
       availablePlayers.add(new FantasyPlayer("donk", "Entry", 1.36, 146, 12.9, 8.8, 4.0));
       availablePlayers.add(new FantasyPlayer("shiro", "AWPer", 1.20, 155, 10.1, 7.9, 8.0));
       availablePlayers.add(new FantasyPlayer("tN1R", "Rifler", 1.60, 144, 8.2, 11.5, 10.5));
       availablePlayers.add(new FantasyPlayer("zweih", "Rifler", 1.22, 130, 7.5, 6.1, 2.5));
       availablePlayers.add(new FantasyPlayer("chopper", "IGL", 1.52, 144, 10.6, 11.3, 14.2));
       availablePlayers.add(new FantasyPlayer("apEX", "IGL", 1.60, 155, 14.0, 15.5, 8.0));
       availablePlayers.add(new FantasyPlayer("ZywOo", "AWPer", 1.28, 156, 13.7, 6.0, 10.5));
       availablePlayers.add(new FantasyPlayer("ropz", "Lurker", 1.22, 142, 14.1, 3.5, 6.0));
       availablePlayers.add(new FantasyPlayer("flameZ", "Entry", 1.25, 165, 13.1, 7.0, 5.0));
       availablePlayers.add(new FantasyPlayer("mezii", "Rifler", 1.67, 158, 8.6, 2.0, 9.0));
       availablePlayers.add(new FantasyPlayer("bLitz", "IGL", 1.63, 177, 14.5, 8.5, 4.7));
       availablePlayers.add(new FantasyPlayer("Techno", "Rifler", 1.57, 158, 9.5, 10.5, 7.9));
       availablePlayers.add(new FantasyPlayer("mzinho", "Rifler", 1.43, 149, 13.1, 14.5, 8.0));
       availablePlayers.add(new FantasyPlayer("910", "AWPer", 1.49, 145, 7.0, 7.5, 10.5));
       availablePlayers.add(new FantasyPlayer("Senzu", "Support", 1.32, 188, 11.3, 8.0, 9.0));
       availablePlayers.add(new FantasyPlayer("yuurih", "Support", 1.78, 188, 16.7, 7.5, 6.7));
       availablePlayers.add(new FantasyPlayer("KSCERATO", "Lurker", 1.65, 177, 11.5, 9.0, 5.5));
       availablePlayers.add(new FantasyPlayer("FalleN", "IGL", 1.50, 165, 8.6, 9.0, 9.6));
       availablePlayers.add(new FantasyPlayer("Molodoy", "AWPer", 1.43, 146, 7.8, 8.6, 6.2));
       availablePlayers.add(new FantasyPlayer("YEKINDAR", "Entry", 1.22, 171, 13.0, 15.1, 4.4));
       availablePlayers.add(new FantasyPlayer("Frozen", "Rifler", 1.40, 180, 13.1, 10.0, 8.0));
       availablePlayers.add(new FantasyPlayer("xertioN", "Entry", 1.36, 166, 14.0, 15.1, 10.0));
       availablePlayers.add(new FantasyPlayer("siuhy", "IGL", 1.56, 188, 10.5, 11.0, 6.0));
       availablePlayers.add(new FantasyPlayer("torzsi", "AWPer", 1.22, 167, 7.0, 12.3, 9.2));
       availablePlayers.add(new FantasyPlayer("Jimpphat", "IGL", 1.58, 187, 14.1, 16.6, 7.1));
       availablePlayers.add(new FantasyPlayer("NiKo", "Entry", 1.87, 123, 13.1, 15.1, 8.8));
       availablePlayers.add(new FantasyPlayer("kyxsan", "IGL", 1.88, 166, 17.4, 12.0, 6.7));
       availablePlayers.add(new FantasyPlayer("TeSeS", "Entry", 1.52, 144, 10.8, 11.6, 7.5));
       availablePlayers.add(new FantasyPlayer("mONESY", "AWPer", 1.22, 171, 14.8, 15.4, 3.8));
       availablePlayers.add(new FantasyPlayer("Magisk", "Rifler", 1.34, 158, 14.2, 14.4, 9.0));
       availablePlayers.add(new FantasyPlayer("huNter", "IGL", 1.33, 177, 14.2, 12.5, 2.5));
       availablePlayers.add(new FantasyPlayer("malbsMd", "Entry", 1.45, 156, 18.0, 14.0, 6.0));
       availablePlayers.add(new FantasyPlayer("SunPayus", "AWPer", 1.01, 148, 10.5, 12.0, 5.5));
       availablePlayers.add(new FantasyPlayer("HeavyGod", "Support", 1.51, 158, 10.0, 7.8, 8.0));
       availablePlayers.add(new FantasyPlayer("MATYS", "Rifler", 1.65, 173, 12.2, 7.6, 4.5));
       availablePlayers.add(new FantasyPlayer("b1t", "IGL", 1.11, 192, 15.4, 17.5, 5.4));
       availablePlayers.add(new FantasyPlayer("iM", "Entry", 1.35, 177, 8.6, 13.4, 3.2));
       availablePlayers.add(new FantasyPlayer("jL", "Lurker", 1.34, 173, 13.8, 14.3, 8.6));
       availablePlayers.add(new FantasyPlayer("WOnderful", "AWPer", 0.98, 144, 9.8, 16.0, 2.3));
       availablePlayers.add(new FantasyPlayer("MATYS_NaVi", "Rifler", 1.37, 198, 12.6, 8.4, 4.5));
       availablePlayers.add(new FantasyPlayer("MAJ3R", "IGL", 1.36, 173, 13.4, 16.1, 8.0));
       availablePlayers.add(new FantasyPlayer("XANTARES", "Entry", 1.24, 192, 6.3, 9.0, 7.8));
       availablePlayers.add(new FantasyPlayer("w0xic", "AWPer", 1.50, 223, 17.5, 12.0, 2.3));
       availablePlayers.add(new FantasyPlayer("Wicadia", "Rifler", 1.61, 198, 12.0, 11.4, 4.6));
       availablePlayers.add(new FantasyPlayer("jottAAA", "Rifler", 1.15, 158, 12.2, 14.6, 6.7));
       availablePlayers.add(new FantasyPlayer("bodyy", "Rifler", 1.89, 188, 16.5, 12.3, 5.5));
       availablePlayers.add(new FantasyPlayer("Maka", "AWPer", 1.75, 166, 14.5, 8.0, 9.5));
       availablePlayers.add(new FantasyPlayer("Lucky", "Rifler", 1.32, 165, 7.5, 14.6, 4.5));
       availablePlayers.add(new FantasyPlayer("Ex3rcice", "Rifler", 1.45, 145, 9.5, 12.5, 1.2));
       availablePlayers.add(new FantasyPlayer("Graviti", "IGL", 1.45, 222, 13.4, 19.5, 1.1));
       availablePlayers.add(new FantasyPlayer("karrigan", "IGL", 1.65, 212, 13.4, 5.6, 9.5));
       availablePlayers.add(new FantasyPlayer("broky", "AWPer", 2.00, 200, 16.5, 8.5, 8.0));
       availablePlayers.add(new FantasyPlayer("frozen_faze", "Support", 1.54, 166, 8.5, 8.8, 2.2));
       availablePlayers.add(new FantasyPlayer("Twistzz", "Support", 1.77, 189, 13.4, 17.0, 4.0));
       availablePlayers.add(new FantasyPlayer("jcobbb", "Rifler", 1.75, 177, 10.5, 4.5, 13.0));
       availablePlayers.add(new FantasyPlayer("NAF", "Rifler", 1.64, 188, 13.5, 7.4, 3.4));
       availablePlayers.add(new FantasyPlayer("EliGE", "Entry", 1.55, 145, 14.0, 13.5, 3.9));
       availablePlayers.add(new FantasyPlayer("Ultimate", "AWPer", 1.33, 222, 6.5, 8.5, 17.0));
       availablePlayers.add(new FantasyPlayer("NertZ", "Rifler", 1.75, 198, 9.5, 14.5, 8.9));
       availablePlayers.add(new FantasyPlayer("siuhy_liquid", "Support", 1.34, 175, 10.5, 13.0, 7.5));
       availablePlayers.add(new FantasyPlayer("device", "AWPer", 1.85, 188, 15.5, 8.5, 7.5));
       availablePlayers.add(new FantasyPlayer("Magisk_Astralis", "Rifler", 1.45, 178, 12.5, 10.5, 8.6));
       availablePlayers.add(new FantasyPlayer("Staehr", "Rifler", 1.67, 134, 13.5, 15.0, 12.5));
       availablePlayers.add(new FantasyPlayer("Jabbi", "Rifler", 1.45, 167, 6.5, 12.4, 12.5));
       availablePlayers.add(new FantasyPlayer("HooXi", "IGL/Entry", 1.55, 145, 9.5, 10.0, 14.5));
       availablePlayers.add(new FantasyPlayer("dgt", "Lurker", 1.89, 145, 14.5, 8.5, 3.4));
       availablePlayers.add(new FantasyPlayer("nqz", "AWPer", 1.90, 199, 12.4, 8.5, 6.7));
       availablePlayers.add(new FantasyPlayer("biguzera", "IGL/Rifler", 1.65, 177, 8.5, 12.5, 9.8));
       availablePlayers.add(new FantasyPlayer("dav1deus", "Rifler", 1.86, 188, 13.0, 7.7, 6.9));
       availablePlayers.add(new FantasyPlayer("Snow", "IGL", 1.89, 184, 9.5, 10.5, 17.5));
       availablePlayers.add(new FantasyPlayer("dumau", "Rifler/AWPer", 1.89, 201, 16.4, 12.5, 10.5));
       availablePlayers.add(new FantasyPlayer("saadzin", "AWPer", 1.86, 166, 12.5, 9.6, 2.4));
       availablePlayers.add(new FantasyPlayer("latto", "Rifler/AWPer", 1.55, 177, 9.5, 12.5, 16.5));
       availablePlayers.add(new FantasyPlayer("Iux", "IGL/Rifler", 1.33, 140, 12.4, 16.5, 9.9));
       availablePlayers.add(new FantasyPlayer("n1ssim", "Rifler", 1.88, 144, 13.5, 8.6, 5.6));
       
    }

    @Override
    protected Map<String, Integer> getPositionLimits() {
        return POSITION_LIMITS;
    }

    @Override
    public void startDraft(User user) {
        System.out.println("\n=== CS:GO Draft ===");
        while (user.getDraftedPlayers().size() < 7 && !availablePlayers.isEmpty()) {
            showAvailablePlayers();
            System.out.println("Drafted " + user.getDraftedPlayers().size() + "/7 players.");
            System.out.print("Choose a player (0 to cancel): ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }
            if (choice == 0) break;

          
            if (pickPlayer(user, choice)) { 
              
            } else {
            
            }
        }
    }

    @Override
    public void startDraft(List<User> users) {
        for (User u : users) startDraft(u);
    }
}