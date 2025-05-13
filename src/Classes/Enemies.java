package Classes;

import java.util.Random;

public class Enemies extends Player {
    private int strength;

    public Enemies(String name, int health, int strength) {
        super(name, health, strength); 
        this.strength = strength;
    }

    @Override
    public void attack(Player target) {
        int damage = strength;
        if (new Random().nextInt(100) < 10) {
            damage *= 2;
            System.out.println(getName() + " lands a CRITICAL HIT!");
        }
        System.out.println(getName() + " attacks " + target.getName() + " for " + damage);
        target.takeDamage(damage);
    }

    @Override
    public void printStatus() {
        System.out.println("Warrior: " + getName() + ", Health: " + getHealth() + ", Strength: " + strength);
    }
}
