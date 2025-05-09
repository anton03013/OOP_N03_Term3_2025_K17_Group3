package test;

import model.Warrior;
import model.Player;

public class Test {
    public static void main(String[] args) {
        Player player1 = new Player("Zeros", 250, 30);
        Warrior player2 = new Warrior("Sen", 50, 50);
    
        player1.printStatus();
        player2.printStatus();
    }
}
