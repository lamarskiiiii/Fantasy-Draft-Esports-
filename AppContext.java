/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet;

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
import com.mycompany.kdafragbet.repository.ServerManager;
import com.mycompany.kdafragbet.repository.BettingManager;
import com.mycompany.kdafragbet.repository.UserManager;
import com.mycompany.kdafragbet.domain.FantasyPlayer;
import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.menu.AuthSystemImpl;
import java.util.Scanner;
import java.util.List;


public class AppContext {
    public final UserManager userManager;
    public final ServerManager serverManager;
    public final BettingManager bettingManager;
    public final List<FantasyPlayer> availablePlayers;
    public final Scanner sc;
    public User currentUser;

    public AppContext(UserManager userManager, ServerManager serverManager,
                      BettingManager bettingManager, List<FantasyPlayer> availablePlayers, Scanner sc) {
        this.userManager = userManager;
        this.serverManager = serverManager;
        this.bettingManager = bettingManager;
        this.availablePlayers = availablePlayers;
        this.sc = sc;
    }
}