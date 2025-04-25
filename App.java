public class App {
    public static void main(String[] args) throws Exception {
  
        Player [] players = {
            new Player("Anthoni", 100),
            new Warrior("Grom", 100, 50)
        };
        for (Player p: players){
            p.printStatus();
        }
        BattleArena arena = new BattleArena();
        arena.fight(players[0], players[1]);
    }
}

class Player {
    private String name;
    private int health;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public void takeDamage(int damage) {
        setHealth(health - damage);
    }

    public void printStatus() {
        System.out.println("Player: " + name + ", Health: " + health);
    }

    public void attack(Player target){
        System.out.println(getName() + " attacks " + target.getName());
        target.takeDamage(10); // Fixed damage for simplicity
    }

    public boolean isAlive() {
        if (getHealth() > 0) {
            return true;
        } else {
            System.out.println(getName() + " is dead.");
            return false;
        }
    }
}

class Warrior extends Player {
    private int strength;

    public Warrior(String name, int health, int strength) {
        super(name, health);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public void printStatus() {
        System.out.println("Warrior: " + getName() + ", Health: " + getHealth() + ", Strength: " + strength);
    }

    public void powerAttack(){
        System.out.println("Warrior " + getName() + " наносит мощную атаку с силой " + strength);
    }
}

class BattleArena {
    public void fight(Player p1, Player p2) {
        System.out.println(p1.getName() + " vs " + p2.getName());
        while (p1.isAlive() && p2.isAlive()) {
            p1.attack(p2);
            System.out.println(p1.getName() + " наносит 10 урона " + p2.getName());
            if (!p2.isAlive()) break;
            try {
                Thread.sleep(1000); // Задержка 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            p2.attack(p1);
            System.out.println(p2.getName() + " наносит 10 урона " + p1.getName());
            try {
                Thread.sleep(1000); // Задержка 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("🏆 Победитель: " + (p1.isAlive() ? p1.getName() : p2.getName()));
    }
}