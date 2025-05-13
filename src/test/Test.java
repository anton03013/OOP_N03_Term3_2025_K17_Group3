package test;

import Classes.Enemies;
import Classes.Player;

public class Test {
    public static void main(String[] args) {
        Player Zeros = new Player("Zeros", 250, 30);
        Enemies Sen = new Enemies("Sen", 50, 50);
    
        Player[] players = {Zeros, Sen};
        for (Player player : players) {
            player.printStatus();
        }
    }
}
