package model;

import java.util.Random;

public class Player {
    private String name;
    private int health;
    private int strength; 
    private Random random = new Random();

    public Player(String name, int health, int strength) {
        this.name = name;
        this.health = health;
        this.strength = strength;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getStrength() { return strength; } 

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public void setStrength(int strength) { 
        this.strength = Math.max(0, strength);
    }

    public void takeDamage(int damage) {
        setHealth(health - damage);
    }

    public void printStatus() {
        System.out.println("Player: " + name + ", Health: " + health + ", Strength: " + strength);
    }

    public void attack(Player target) {
        int damage = strength; 
        if (random.nextInt(100) < 25) {
            damage *= 2;
            System.out.println(name + " lands a CRITICAL HIT!");
        }
        System.out.println(name + " attacks " + target.getName() + " for " + damage);
        target.takeDamage(damage);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
