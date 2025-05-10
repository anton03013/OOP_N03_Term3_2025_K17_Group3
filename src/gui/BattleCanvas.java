package gui;

import model.*;
import texture.AnimationHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleCanvas extends JPanel implements KeyListener {
    private Player p1;
    private Warrior p2;
    private boolean facingRight = true;
    private int p1X = 50;
    private int p2X = 300;
    private int p1Y;
    private final int groundY = 200;
    private boolean jumping = false;
    private int velocityY = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean attacking = false;
    private boolean hurt = false;

    private AnimationHandler currentAnimation;
    private AnimationHandler idleAnimation;
    private AnimationHandler runAnimation;
    private AnimationHandler attackAnimation;
    private AnimationHandler hurtAnimation;

    public BattleCanvas() {
        setFocusable(true);
        addKeyListener(this);
        p1 = new Player("Knight", 100, 15);
        p2 = new Warrior("Grom", 100, 20);
        p1Y = groundY;

        // Animations
        idleAnimation = new AnimationHandler("src/texture/IDLE.png", 96, 96, 1, 10, 7);
        runAnimation = new AnimationHandler("src/texture/RUN.png", 96, 96, 1, 16, 6);
        attackAnimation = new AnimationHandler("src/texture/ATTACK 1.png", 96, 96, 1, 7, 3);
        hurtAnimation = new AnimationHandler("src/texture/HURT.png", 96, 96, 1, 4, 5); // HURT animation
        currentAnimation = idleAnimation; // Default animation

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

            // Update animation based on player state
            if (hurt) {
                currentAnimation = hurtAnimation;
            } else if (movingLeft || movingRight) {
                currentAnimation = runAnimation;
            } else if (attacking) {
                currentAnimation = attackAnimation;
            } else {
                currentAnimation = idleAnimation;
            }

            if (movingLeft) {
                p1X = Math.max(0, p1X - 5); // Speed of left movement
            }
            if (movingRight) {
                p1X = Math.min(getWidth() - 40, p1X + 5); // Speed of right movement
            }

            currentAnimation.update();
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
        currentAnimation.draw(g, p1X, p1Y - 80, facingRight);

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
            case KeyEvent.VK_A -> {
                movingLeft = true;
                facingRight = false;
            }
            case KeyEvent.VK_D -> {
                movingRight = true;
                facingRight = true;
            }
            case KeyEvent.VK_W -> {
                if (!jumping) {
                    jumping = true;
                    velocityY = -15;
                }
            }
            case KeyEvent.VK_SPACE -> {
                attacking = true;
                if (Math.abs(p1X - p2X) < 100) { // Distance check for attack
                    p1.attack(p2);
                    if (!p2.isAlive()) {
                        showWinner(p1);
                        return;
                    }
                    hurt = true; // Trigger hurt animation
                    new Timer(500, evt -> { // 500ms delay for response attack
                        hurt = false; // End hurt animation
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
            case KeyEvent.VK_A -> movingLeft = false;
            case KeyEvent.VK_D -> movingRight = false;
            case KeyEvent.VK_SPACE -> {
                new Timer(300,
                        evt -> { // 300ms delay for ending attack animation
                            attacking = false;
                            currentAnimation = idleAnimation;
                            ((Timer) evt.getSource()).stop();
                        }).start();
            }
        }
    }

    private void showWinner(Player winner) {
        JOptionPane.showMessageDialog(this, "🏆 Winner: " + winner.getName());
        System.exit(0);
    }

    @Override public void keyTyped(KeyEvent e) {}
}