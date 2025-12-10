/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.domain;

/**
 *
 * @author Administrator
 */
public class FantasyPlayer {
    private String name;
    private String position;
    private double kda;
    private double acsPerMap;
    private double killsPerMap;
    private double assistsPerMap;
    private double deathsPerMap;
    private User owner;

    public FantasyPlayer(String name, String position, double kda, double acsPerMap, double killsPerMap,
                         double assistsPerMap, double deathsPerMap) {
        this.name = name;
        this.position = position;
        this.kda = kda;
        this.acsPerMap = acsPerMap;
        this.killsPerMap = killsPerMap;
        this.assistsPerMap = assistsPerMap;
        this.deathsPerMap = deathsPerMap;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getKda() { return kda; }
    public double getAcsPerMap() { return acsPerMap; }
    public double getKillsPerMap() { return killsPerMap; }
    public double getAssistsPerMap() { return assistsPerMap; }
    public double getDeathsPerMap() { return deathsPerMap; }
    public User getOwner()  {return owner;}
    public void setOwner(User owner) {this.owner = owner;}


    // Calculate score based on player stats
    public double calculateScore() {
        double score = 0;
        score += killsPerMap * 3;             // 3 points per kill
        score += assistsPerMap * 1.5;         // 1.5 points per assist
        score -= deathsPerMap * 2;            // -2 points per death
        score += acsPerMap / 50;              // 1 point for every 50 ACS
        score += kda;                          // add KDA as minor factor
        return score;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | KDA: %.2f | ACS/MAP: %.1f | K/MAP: %.1f | A/MAP: %.1f | D/MAP: %.1f",
                name, position, kda, acsPerMap, killsPerMap, assistsPerMap, deathsPerMap);
    }
}
