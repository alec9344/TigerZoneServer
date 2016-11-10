package com.tigerzone.fall2016.gamesystem;

import java.util.List;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Player {
    private int playerId;
    private int goodSupply; // Tigers
    private int badSupply;  // Crocodiles

    public Player(int playerId) {
        this.playerId = playerId;
        this.goodSupply = 7;
        this.badSupply = 2;
    }

    /**
     * Increase supply by 1.
     * PRECONDITION: Supply <= 7
     */
    public void incrementSupply() {

    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementSupply() {

    }

    /**
     * Accessor for playerId (unique identifier for player)
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Accessor for supply (number of followers)
     * @return int
     */
    public int getSupply() {
        return goodSupply;
    }
}