/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.service;

import com.mycompany.kdafragbet.domain.User;

/**
 *
 * @author Administrator
 */

public class Transaction {

    // Deposit money into user's account
    public boolean deposit(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Deposit must be positive.");
            return false;
        }
        user.deposit(amount);
        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, user.getBalance());
        return true;
    }

    // Withdraw money from user's account
    public boolean withdraw(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal must be positive.");
            return false;
        }

        if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
            return false;
        }

        user.withdraw(amount);
        System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, user.getBalance());
        return true;
    }
}
