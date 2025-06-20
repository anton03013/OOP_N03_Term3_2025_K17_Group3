package com.example.servingwebcontent.dto;

import java.util.List;

public class GameState {
    private PlayerState player1;
    private PlayerState enemy;
    private List<PlatformState> platforms;
    private boolean gameOver;
    private String winner;
    private int groundY;

    public GameState() {}

    public GameState(PlayerState player1, PlayerState enemy, List<PlatformState> platforms, 
                    boolean gameOver, String winner, int groundY) {
        this.player1 = player1;
        this.enemy = enemy;
        this.platforms = platforms;
        this.gameOver = gameOver;
        this.winner = winner;
        this.groundY = groundY;
    }

    // Getters and Setters
    public PlayerState getPlayer1() { return player1; }
    public void setPlayer1(PlayerState player1) { this.player1 = player1; }
    
    public PlayerState getEnemy() { return enemy; }
    public void setEnemy(PlayerState enemy) { this.enemy = enemy; }
    
    public List<PlatformState> getPlatforms() { return platforms; }
    public void setPlatforms(List<PlatformState> platforms) { this.platforms = platforms; }
    
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    
    public int getGroundY() { return groundY; }
    public void setGroundY(int groundY) { this.groundY = groundY; }
} 