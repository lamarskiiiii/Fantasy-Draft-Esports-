/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service;

/**
 *
 * @author Administrator
 */
import java.util.Collections;
import java.util.List;

public class DraftOrderManager {

    private List<String> users;      
    private int currentIndex = 0;    

    public DraftOrderManager(List<String> users) {
        this.users = users;
        shuffleOrder();
    }

    private void shuffleOrder() {
        Collections.shuffle(users);
        System.out.println("\n=== DRAFT ORDER GENERATED ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
        System.out.println("===============================");
    }

    public String getNextUser() {
        String user = users.get(currentIndex);
        currentIndex++;


        if (currentIndex >= users.size()) {
            currentIndex = 0;
        }

        return user;
    }


    public void displayOrder() {
        System.out.println("\n=== CURRENT DRAFT ORDER ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }
}
