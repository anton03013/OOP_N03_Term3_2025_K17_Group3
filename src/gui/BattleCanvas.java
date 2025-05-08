package gui;

import model.*;
import texture.AnimationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleCanvas extends JPanel implements KeyListener {
    private Player p1;
    private Warrior p2;

    private int p1X = 50;
    private int p2X = 300;
    private int p1Y;
    private final int groundY = 200;
    private boolean jumping = false;
    private int velocityY = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private AnimationHandler currentAnimation;
    private AnimationHandler idleAnimation;
    private AnimationHandler runAnimation;

    public BattleCanvas() {
        setFocusable(true);
        addKeyListener(this);
        p1 = new Player("Anthoni", 100, 15);
        p2 = new Warrior("Grom", 100, 20);
        p1Y = groundY;

        // Инициализация анимаций
        idleAnimation = new AnimationHandler("d:/Visual/RPG/src/texture/IDLE.png", 96, 96, 1, 10, 5);
        runAnimation = new AnimationHandler("d:/Visual/RPG/src/texture/RUN.png", 96, 96, 1, 16, 5);
        currentAnimation = idleAnimation; // По умолчанию анимация ожидания

        Timer gameLoop = new Timer(16, e -> {
            if (jumping) {
                p1Y += velocityY;
                velocityY += 1; // gravity
                if (p1Y >= groundY) {
                    p1Y = groundY;
                    jumping = false;
                    velocityY = 0;
                }
            }

            // Обновление позиции игрока
            if (movingLeft || movingRight) {
                currentAnimation = runAnimation; // Переключение на анимацию бега
            } else {
                currentAnimation = idleAnimation; // Переключение на анимацию ожидания
            }

            if (movingLeft) {
                p1X = Math.max(0, p1X - 5); // Скорость движения влево
            }
            if (movingRight) {
                p1X = Math.min(getWidth() - 40, p1X + 5); // Скорость движения вправо
            }

            currentAnimation.update(); // Обновление текущей анимации
            repaint();
        });
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Line
        g.setColor(Color.GRAY);
        g.fillRect(0, groundY, getWidth(), 5);

        // Player 1
        currentAnimation.draw(g, p1X, p1Y - 80); // Отображение текущей анимации Player 1
        g.drawString(p1.getName() + " HP: " + p1.getHealth(), p1X, p1Y - 60);

        // Player 2
        g.setColor(Color.RED);
        g.fillRect(p2X, groundY - 60, 60, 60);
        g.drawString(p2.getName() + " HP: " + p2.getHealth(), p2X, groundY - 70);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!p1.isAlive() || !p2.isAlive()) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> movingLeft = true; // Начать движение влево
            case KeyEvent.VK_D -> movingRight = true; // Начать движение вправо
            case KeyEvent.VK_W -> {
                if (!jumping) {
                    jumping = true;
                    velocityY = -15;
                }
            }
            case KeyEvent.VK_SPACE -> {
                if (Math.abs(p1X - p2X) < 60) { // Distance check for attack
                    p1.attack(p2);
                    if (!p2.isAlive()) {
                        showWinner(p1);
                        return;
                    }
                    new Timer(500, evt -> { // 500ms delay for response attack
                        if (Math.abs(p1X - p2X) < 60) {
                            p2.attack(p1);
                            if (!p1.isAlive()) showWinner(p2);
                        }
                        ((Timer) evt.getSource()).stop();
                    }).start();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> movingLeft = false; // Остановить движение влево
            case KeyEvent.VK_D -> movingRight = false; // Остановить движение вправо
        }
    }

    private void showWinner(Player winner) {
        JOptionPane.showMessageDialog(this, "🏆 Winner: " + winner.getName());
        System.exit(0); // Завершение игры
    }

    @Override public void keyTyped(KeyEvent e) {}
}
