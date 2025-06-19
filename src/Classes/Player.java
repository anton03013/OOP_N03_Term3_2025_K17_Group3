package Classes;

import java.util.Random;

public class Player {
    private String name;
    private int health;
    private int strength; 
    private Random critical_rate = new Random();

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

    public void takeDamage(int damage) {
        setHealth(health - damage);
    }

    public void printStatus() {
        try {
            System.out.println("Player: " + name + ", Health: " + health + ", Strength: " + strength);
        } catch (Exception e) {
            System.out.println("Lỗi khi in trạng thái: " + e.getMessage());
        }
    }

    public void attack(Player target) {
        try {
            int damage = strength;
            if (critical_rate.nextInt(100) < 25) {
                damage *= 2;
                System.out.println(name + " lands a CRITICAL HIT!");
            }
            System.out.println(name + " attacks " + target.getName() + " for " + damage);
            target.takeDamage(damage);
        } catch (Exception e) {
            System.out.println("Lỗi khi tấn công: " + e.getMessage());
        }

    }

    public boolean isAlive() {
        return health > 0;
    }
}
