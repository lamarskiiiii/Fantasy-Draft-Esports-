/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.domain;

/**
 *
 * @author Administrator
 */


import java.util.ArrayList;
import java.util.List;

public class Server {

    private String name;
    private boolean isPrivate;
    private String privateCode;
    private int gameChoice;
    private List<User> members;

    // Constructor
    public Server(String name, boolean isPrivate, int gameChoice) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.gameChoice = gameChoice;
        this.members = new ArrayList<>();

        if (isPrivate) {
            this.privateCode = generatePrivateCode();
        }
    }

    // Private method to generate 4-digit code
    private String generatePrivateCode() {
        return String.valueOf((int) (Math.random() * 9000 + 1000));
    }

    // Getters
    public String getName() { return name; }
    public boolean isPrivate() { return isPrivate; }
    public String getPrivateCode() { return privateCode; }
    public int getGameChoice() { return gameChoice; }
    public List<User> getMembers() { return members; }

    // Add a member to the server
    public void addMember(User user) {
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    // Get number of members
    public int getMemberCount() {
        return members.size();
    }

    // Check if server is ready to start
    public boolean isReadyToStart() {
        return members.size() >= 2; // minimum 2 members to start
    }

    // Optional: check if server is public
    public boolean isPublic() {
        return !isPrivate;
    }
}
