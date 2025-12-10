/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.domain;

/**
 *
 * @author Administrator
 */
public enum Role {
    ADMIN("Administrator"),
    USER("User");

    private final String displayName;
    
    Role(String displayName){this.displayName = displayName;}
    
  
    public String getDisplayName(){return displayName;}
    public boolean isAdmin() {return this == ADMIN;}
    public boolean isUser() {return this == USER;}

    public static Role fromString(String value) {
        if (value == null) return USER;
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return USER; // default fallback
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}
