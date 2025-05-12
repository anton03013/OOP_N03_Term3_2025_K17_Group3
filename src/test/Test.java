package test;

import model.Warrior;
import model.Player;

public class Test {
    public static void main(String[] args) {
        Player Zeros = new Player("Zeros", 250, 30);
        Warrior Sen = new Warrior("Sen", 50, 50);
    
        Player[] players = {Zeros, Sen};
        for (Player player : players) {
            player.printStatus();
        }
    }
}
