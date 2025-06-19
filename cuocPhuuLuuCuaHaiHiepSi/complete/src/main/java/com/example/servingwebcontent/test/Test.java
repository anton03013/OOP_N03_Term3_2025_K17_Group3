

package com.example.servingwebcontent.test;

import com.example.servingwebcontent.Classes.Player;
import com.example.servingwebcontent.Classes.Enemies;
import com.example.servingwebcontent.Classes.Platforms;

public class Test {
    public static void test(String[] args) {
        // Test Player methods
        Player player = new Player("Hero", 100, 20);
        System.out.println("== Player Status ==");
        player.printStatus();
        player.takeDamage(30);
        System.out.println("After taking 30 damage: Health = " + player.getHealth());
        player.setHealth(200);
        System.out.println("After healing: Health = " + player.getHealth());
        System.out.println("Is Alive? " + player.isAlive());

        // Test Enemies methods
        Enemies enemy = new Enemies("Goblin", 80, 15);
        System.out.println("\n== Enemy Status ==");
        enemy.printStatus();
        enemy.attack(player);
        System.out.println("Player Health after enemy attack: " + player.getHealth());

        // Test Player attack
        player.attack(enemy);
        System.out.println("Enemy Health after player attack: " + enemy.getHealth());

        // Test Platforms
        Platforms platform = new Platforms(100, 200, 150, 10);
        System.out.println("\n== Platform Info ==");
        System.out.println("Platform X: " + platform.getX());
        System.out.println("Platform Y: " + platform.getY());
        System.out.println("Platform Width: " + platform.getWidth());
        System.out.println("Platform Height: " + platform.getHeight());
    }
}