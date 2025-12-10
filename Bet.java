/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.domain;

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

public class Bet {
    
    // Using static to generate unique IDs
    private static int counter = 1; 
    
    // Core Bet Properties
    private final String betId;            // Changed to String for consistency (if needed later)
    private final User user;
    private final double amount;
    
    // Two types of prediction, used depending on the betting context:
    private final FantasyPlayer predictedPlayer; // Used for simple player bets (your original logic)
    private final User predictedWinner;         // Used for match/team bets (required by BetManagement UI)
    
    // Status flags
    private boolean approved = false;       // Changed default to FALSE for admin approval workflow
    private boolean resolved = false;
    private boolean won = false;
    
    // --- CONSTRUCTORS ---

    /**
     * Constructor for a simple player-score bet (your original design).
     */
    public Bet(User user, FantasyPlayer predictedPlayer, double amount) {
        this.betId = "B" + counter++;
        this.user = user;
        this.predictedPlayer = predictedPlayer;
        this.predictedWinner = null; // Not predicting a user winner
        this.amount = amount;
        // NOTE: Default approved is FALSE, forcing admin review.
    }

    /**
     * Constructor for a match/team winner bet (required by BetManagement UI).
     */
    public Bet(User user, User predictedWinner, double amount) {
        this.betId = "B" + counter++;
        this.user = user;
        this.predictedPlayer = null; // Not predicting a player
        this.predictedWinner = predictedWinner;
        this.amount = amount;
        // NOTE: Default approved is FALSE, forcing admin review.
    }

    // --- GETTERS ---

    public String getBetId() { return betId; } // ✅ REQUIRED by BetManagement
    public int getId() { return Integer.parseInt(betId.substring(1)); } // Legacy getter using integer part
    public User getUser() { return user; } // ✅ REQUIRED by BetManagement
    public double getAmount() { return amount; } 
    public FantasyPlayer getPredictedPlayer() { return predictedPlayer; }
    
    // ✅ REQUIRED by BetManagement UI: Allows printing the predicted winner's name
    public User getPredictedWinner() { 
    return predictedWinner != null ? predictedWinner : (predictedPlayer != null ? predictedPlayer.getOwner() : null); 
}
    
    public boolean isApproved() { return approved; }
    public boolean isResolved() { return resolved; }
    public boolean isWon() { return won; }

    // --- SETTERS / STATUS CHANGERS ---

    public void setApproved(boolean approved) { this.approved = approved; } // ✅ REQUIRED by BetManagement
    public void setResolved(boolean resolved) { this.resolved = resolved; } 

    // --- RESOLUTION LOGIC ---
    
    /**
     * Resolves the bet based on a specific match winner.
     * Used by GameSimulation or BetManagement for match bets.
     * @param matchWinner The User who won the simulated match.
     * @param totalPool The total pool amount available for payout.
     */
    public void resolveMatchBet(User matchWinner, double totalPool) {
        if (!approved || resolved) {
            System.out.println("❌ Bet not approved or already resolved: " + betId);
            return;
        }

        this.won = this.predictedWinner != null && this.predictedWinner.equals(matchWinner);
        this.resolved = true;
        
        if (won) {
            // NOTE: The GameSimulation class handles the actual deposit of the payout.
            // This flag is mainly for record-keeping.
            System.out.printf("Bet %s | User %s WON 🏆%n", betId, user.getUsername());
        } else {
            // Loss simply means no payout for this bet
            System.out.printf("Bet %s | User %s LOST ❌%n", betId, user.getUsername());
        }
    }


    /**
     * Resolves the bet based on the player's score threshold (your original logic).
     * Used for individual player performance bets.
     */
    public void resolve(double threshold) {
        if (!approved || resolved || predictedPlayer == null) {
            System.out.println("❌ Cannot resolve player bet (status or type mismatch): " + betId);
            return;
        }
        
        // This logic implies a simple 1:1 payout/loss, ignoring pool complexity.
        double score = predictedPlayer.calculateScore();
        won = score >= threshold;
        resolved = true;
        
        if (won) user.deposit(amount);  // reward equals amount (simple)
        else user.withdraw(amount);     // lose amount (simple)

        System.out.printf("%s | Bet %s | Player Score: %.2f | New Balance: $%.2f%n",
                 user.getUsername(), won ? "WON 🏆" : "LOST ❌", score, user.getBalance());
    }

    public boolean isSettled() { return resolved; }

    @Override
    public String toString() {
        String winnerName = predictedWinner != null 
                            ? predictedWinner.getUsername() 
                            : (predictedPlayer != null ? predictedPlayer.getName() : "N/A");
                            
        return String.format("Bet #%s | User: %s | Prediction: %s | $%.2f | Approved: %s | Resolved: %s | Won: %s",
                betId, user.getUsername(), winnerName, amount,
                approved ? "Yes" : "No",
                resolved ? "Yes" : "No",
                won ? "Yes" : "No");
    }
}