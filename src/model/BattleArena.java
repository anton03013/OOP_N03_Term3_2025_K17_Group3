package model;

public class BattleArena {
    public void fight(Player p1, Player p2) {
        System.out.println(p1.getName() + " vs " + p2.getName());
        while (p1.isAlive() && p2.isAlive()) {
            p1.attack(p2);
            if (!p2.isAlive()) break;
            p2.attack(p1);
        }
        System.out.println("🏆 Победитель: " + (p1.isAlive() ? p1.getName() : p2.getName()));
    }
}
