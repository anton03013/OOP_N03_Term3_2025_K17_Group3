package Character;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainCharacter hero = new MainCharacter(0, 0);

        System.out.println("Chào mừng đến game 2D!");
        System.out.println("Các lệnh: a (trái), d (phải), w (nhảy), k (tấn công), h (bị đánh 20 máu), q (thoát)");

        while (hero.isAlive()) {
            System.out.print("\nNhập lệnh: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    hero.moveLeft();
                    break;
                case "d":
                    hero.moveRight();
                    break;
                case "w":
                    hero.jump();
                    break;
                case "k":
                    hero.attack();
                    break;
                case "h":
                    hero.takeDamage(20);
                    break;
                case "q":
                    System.out.println("Thoát game.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lệnh không hợp lệ!");
            }

            hero.update();

            System.out.println("Vị trí: (" + hero.getX() + ", " + hero.getY() + ")");
            System.out.println("Animation: " + hero.getCurrentAnimation());
            System.out.println("Máu: " + hero.getHealth());
        }

        System.out.println("\nGame Over!");
    }
}
