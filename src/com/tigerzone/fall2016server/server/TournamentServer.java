package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.TournamentPlayer;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lenovo on 11/19/2016.
 */
public class TournamentServer {

    private static HashMap<TournamentPlayer, AuthenticationThread> playerThreads = new LinkedHashMap<TournamentPlayer, AuthenticationThread>();
    private static List<TournamentPlayer> tournamentPlayers = new ArrayList<TournamentPlayer>();

    private static int PORT = 4444;

    public TournamentServer() { }

    public void authenticate() {

        long startTime = System.currentTimeMillis();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Connection connection;
            boolean running = ((System.currentTimeMillis()-startTime)<20000);
            while (!tournamentReady()){
                connection = new Connection(serverSocket);
                connection.accept();
                connection.setupIO();
                new AuthenticationThread(connection).start();
                System.out.println("Created a connection with " + connection.getClientSocket());
                System.out.println("This is the number of tournament players: " + tournamentPlayers.size());
            }
            TextFilePort textFilePort = new TextFilePort();
            Challenge challenge = new Challenge(this, textFilePort.createStringTiles(),123456789,tournamentPlayers);
            challenge.startRound();
            for (TournamentPlayer tp: tournamentPlayers) {
                System.out.println("These are the players " + tp.getUsername());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    public boolean tournamentReady() {
        boolean ready = false;
        if (tournamentPlayers.size()>=2) {
            ready = true;
        }
        return ready;
    }

    public void notifyChallengeComplete(){
        //TODO: end of tournament shut down
    }

    public static HashMap<TournamentPlayer, AuthenticationThread> getPlayerThreads() {
        return playerThreads;
    }

    public static List<TournamentPlayer> getTournamentPlayers() {
        return tournamentPlayers;
    }
}


