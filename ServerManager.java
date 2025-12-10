/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.repository;

/**
 *
 * @author Administrator
 */

import com.mycompany.kdafragbet.domain.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServerManager {

    private List<Server> publicServers = new ArrayList<>();
    private List<Server> privateServers = new ArrayList<>();

    public Server getPublicServer() {
        if (publicServers.isEmpty()) return null;
        return publicServers.get(0);
    }

    public Server createPublicServer(int gameChoice) {
        Server server = new Server("PublicServer", false, gameChoice);
        publicServers.add(server);
        return server;
    }

    public Server createPrivateServer(int gameChoice) {
        Server server = new Server("PrivateServer", true, gameChoice);
        privateServers.add(server);
        return server;
    }

    public Optional<Server> getPrivateServer(String code) {
        return privateServers.stream()
                .filter(s -> s.getPrivateCode().equals(code))
                .findFirst();
    }
}

