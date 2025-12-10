/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service.draft;

/**
 *
 * @author Administrator
 */

import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class Draft {

    protected List<FantasyPlayer> availablePlayers;

    public Draft(List<FantasyPlayer> players) {
        this.availablePlayers = players;
    }
    protected abstract Map<String, Integer> getPositionLimits();
    
    private Map<String, Integer> getUserPositionCount(User user) {
        Map<String, Integer> countMap = new HashMap<>();
        for (FantasyPlayer p : user.getDraftedPlayers()) {
            countMap.put(p.getPosition(), countMap.getOrDefault(p.getPosition(), 0) + 1);
        }
        return countMap;
    }
    public abstract void startDraft(User user);
    public abstract void startDraft(List<User> users);
    public void showAvailablePlayers() {
        System.out.println("\n=== AVAILABLE PLAYERS ===");
        for (int i = 0; i < availablePlayers.size(); i++) {
            FantasyPlayer p = availablePlayers.get(i);
            System.out.printf("%d. %s | Position: %s | KDA: %.2f | ACS/MAP: %.1f | K/MAP: %.1f | A/MAP: %.1f | D/MAP: %.1f%n",
                    i + 1, p.getName(), p.getPosition(), p.getKda(), p.getAcsPerMap(), p.getKillsPerMap(),
                    p.getAssistsPerMap(), p.getDeathsPerMap());
        }
    }

    public boolean pickPlayer(User user, int pickId) {
        if (pickId < 1 || pickId > availablePlayers.size()) {
            System.out.println("❌ Invalid player selection ID.");
            return false;
        }

        FantasyPlayer picked = availablePlayers.get(pickId - 1);
        Map<String, Integer> limits = getPositionLimits();
        Map<String, Integer> userPositionCount = getUserPositionCount(user);
        
        String position = picked.getPosition();
        int currentCount = userPositionCount.getOrDefault(position, 0);
        
       
        int maxAllowed = limits.getOrDefault(position, 7); 

        if (currentCount >= maxAllowed) {
            System.out.println("❌ Position limit exceeded! You cannot draft more " + position + "s (Limit: " + maxAllowed + ").");
            System.out.println("Please pick another player.");
            return false;
        }


        availablePlayers.remove(picked);
        user.draftPlayer(picked);
        System.out.println("✅ " + user.getUsername() + " drafted: " + picked.getName());
        return true;
    }
        public List<FantasyPlayer> getAvailablePlayers() {
        return availablePlayers;
    }
   
    public boolean isDraftComplete() {
        return availablePlayers.isEmpty();
    }
}