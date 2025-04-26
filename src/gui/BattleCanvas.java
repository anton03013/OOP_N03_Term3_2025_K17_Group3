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

    private AnimationHandler p1Animation;

    public BattleCanvas() {
        setFocusable(true);
        addKeyListener(this);
        p1 = new Player("Anthoni", 100, 15);
        p2 = new Warrior("Grom", 100, 20);
        p1Y = groundY;
    
        // Инициализация анимации для Player 1
        p1Animation = new AnimationHandler("d:/Visual/RPG/src/texture/char_blue.png", 55, 52, 1, 6, 5);
    
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
            p1Animation.update(); // Обновление анимации
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
        p1Animation.draw(g, p1X, p1Y - 52); // Отображение анимации Player 1
        g.drawString(p1.getName() + " HP: " + p1.getHealth(), p1X, p1Y - 60);

        // Player 2
        g.setColor(Color.RED);
        g.fillRect(p2X, groundY - 60, 60, 60);
        g.drawString(p2.getName() + " HP: " + p2.getHealth(), p2X, groundY - 70);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int step = 10;

        if (!p1.isAlive() || !p2.isAlive()) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> p1X = Math.max(0, p1X - step);
            case KeyEvent.VK_D -> p1X = Math.min(getWidth() - 40, p1X + step);
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

    private void showWinner(Player winner) {
        JOptionPane.showMessageDialog(this, "🏆 Winner: " + winner.getName());
        System.exit(0); // Завершение игры
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
