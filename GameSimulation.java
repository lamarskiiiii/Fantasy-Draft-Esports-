/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service;

/**
 *
 * @author Administrator
 */
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.domain.Bet;
import java.util.*;

public class GameSimulation {

    private final BettingManager bettingManager;
    private final User admin;
    private final double housePct;
    private final Random rnd = new Random();

    public GameSimulation(BettingManager bettingManager, User admin, double housePct) {
        this.bettingManager = bettingManager;
        this.admin = admin;
        this.housePct = housePct;
    }

    public GameSimulation(BettingManager bettingManager, User admin) {
        this(bettingManager, admin, 0.10); 
    }
    
    
  
   public boolean simulateDraft(List<User> players, List<FantasyPlayer> availableFantasyPlayers, int rosterSize) {
    if (players == null || players.size() != 2) {
        System.out.println("Draft Engine Error: Need exactly 2 users for the draft.");
        return false;
    }
    if (availableFantasyPlayers == null || availableFantasyPlayers.size() < (rosterSize * 2)) {
        System.out.println("Draft Engine Error: Not enough available players for the draft.");
        return false;
    }

    User playerA = players.get(0);
    User playerB = players.get(1);

   
    Collections.shuffle(availableFantasyPlayers, rnd);

    
    playerA.getDraftedPlayers().clear();
    playerB.getDraftedPlayers().clear();

    System.out.println("\n--- DRAFT ENGINE STARTED ---");

   
   
    int totalDrafts = rosterSize * 2;
    for (int i = 0; i < totalDrafts; i++) {
        
       
        if (i >= availableFantasyPlayers.size()) {
            break; 
        }
        
        FantasyPlayer draftedPlayer = availableFantasyPlayers.get(i);
        int roundNumber = (i / 2) + 1;

       
        if (i % 2 == 0) { 
            if (playerA.getDraftedPlayers().size() < rosterSize) {
                playerA.draftPlayer(draftedPlayer); 
                System.out.printf("Round %d: %s drafts %s (%s)%n", roundNumber, playerA.getUsername(), draftedPlayer.getName(), draftedPlayer.getPosition());
            }
        } else {
            if (playerB.getDraftedPlayers().size() < rosterSize) {
                playerB.draftPlayer(draftedPlayer); 
                System.out.printf("Round %d: %s drafts %s (%s)%n", roundNumber, playerB.getUsername(), draftedPlayer.getName(), draftedPlayer.getPosition());
            }
        }
    }
    
  
    System.out.printf("\n%s's Final Roster Size: %d%n", playerA.getUsername(), playerA.getDraftedPlayers().size());
    System.out.printf("%s's Final Roster Size: %d%n", playerB.getUsername(), playerB.getDraftedPlayers().size());
    System.out.println("--- DRAFT ENGINE COMPLETE ---");
    
    return true;
}
    
    public void runSimulation(List<User> players, int gameChoice) {
        if (players == null || players.size() < 2) {
            System.out.println("Not enough players to simulate a match (need at least 2).");
            return;
        }
        
        System.out.println("\n=== GAME SIMULATION STARTED ===");

      
        Map<String, Integer> positionLimit = new HashMap<>();
        if (gameChoice == 1) { // Valorant
            positionLimit.put("Duelist", 2);
            positionLimit.put("Sentinel", 1);
            positionLimit.put("Initiator", 2);
            positionLimit.put("Controller", 2);
            
        } else if (gameChoice == 2) { // CS:GO
            positionLimit.put("Entry", 2);
            positionLimit.put("Support", 1);
            positionLimit.put("AWPer", 1);
            positionLimit.put("Lurker", 1);
            positionLimit.put("IGL", 1);
        }
        
        double[] teamScores = new double[players.size()];
        for (int i = 0; i < players.size(); i++) {
            User u = players.get(i);
            System.out.println("\nTeam for " + u.getUsername() + ":");

            if (u.getDraftedPlayers().isEmpty()) {
                System.out.println("  (no drafted players)");
                teamScores[i] = 0;
                continue;
            }

            Map<String, Integer> positionCount = new HashMap<>();
            double totalScore = 0;

            for (FantasyPlayer p : u.getDraftedPlayers()) {
                String pos = p.getPosition();
                int limit = positionLimit.getOrDefault(pos, Integer.MAX_VALUE);
                int count = positionCount.getOrDefault(pos, 0);

                if (count < limit) {
                    double score = p.calculateScore();
                    totalScore += score;
                    positionCount.put(pos, count + 1);
                    System.out.printf("  - %s | %s | score: %.2f%n", p.getName(), pos, score);
                } else {
                    System.out.println("  - Skipping " + p.getName() + " (" + pos + ") due to position limit (max " + limit + ").");
                }
            }

            double variance = 1.0 + (rnd.nextDouble() * 0.3 - 0.15); 
            double teamScore = totalScore * variance;
            System.out.printf("Total Team Score for %s: %.2f (base %.2f, var %.2f)%n",
                    u.getUsername(), teamScore, totalScore, variance);
            teamScores[i] = teamScore;
        }

        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - i - 1; j++) {
                if (teamScores[j] < teamScores[j + 1]) {
                  
                    double tmpScore = teamScores[j];
                    teamScores[j] = teamScores[j + 1];
                    teamScores[j + 1] = tmpScore;

      
                    User tmpUser = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, tmpUser);
                }
            }
        }

       
        User winner = players.get(0);
        System.out.println("\n🏆 MATCH WINNER: " + winner.getUsername() + " (score: " + String.format("%.2f", teamScores[0]) + ")");

        
        List<Bet> activeBets = bettingManager.getActiveBets();
        if (!activeBets.isEmpty()) {
            double totalPool = activeBets.stream().mapToDouble(Bet::getAmount).sum();
            double houseCut = totalPool * housePct;
            double payout = totalPool - houseCut;

            if (admin != null) {
                admin.deposit(houseCut);
                System.out.printf("House (admin %s) receives: %.2f%n", admin.getUsername(), houseCut);
            }

            winner.deposit(payout);
            System.out.printf("%s receives payout: %.2f (total pool %.2f minus house %.2f)%n",
                    winner.getUsername(), payout, totalPool, houseCut);


            for (Bet b : activeBets) {
                b.setResolved(true);
            }
        } else {
            System.out.println("No active bets were placed. No payouts required.");
        }

        System.out.println("\n=== GAME SIMULATION COMPLETE ===\n");
    }
}