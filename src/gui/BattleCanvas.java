package gui;

import texture.AnimationHandler;

import javax.swing.*;

import Classes.*;

import java.awt.*;
import java.awt.event.*;

public class BattleCanvas extends JPanel implements KeyListener {
    private Player p1;
    private Enemies e1;
    private boolean facingRight = true;
    private int p1X = 50;
    private int p2X = 300;
    private int p1Y;
    private final int groundY = 200;
    private boolean jumping = false;
    private int velocityY = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean p1Hurt = false;
    private boolean e1Hurt = false;
    private boolean attacking = false;
    private int p1Width;
    private int p2Width;

    private AnimationHandler p1CurrentAnimation;
    private AnimationHandler p2CurrentAnimation;
    private AnimationHandler idleAnimation;
    private AnimationHandler runAnimation;
    private AnimationHandler attackAnimation;
    private AnimationHandler hurtAnimation;
    private AnimationHandler jumpAnimation;

    public BattleCanvas() {
        setFocusable(true);
        addKeyListener(this);
        p1 = new Player("John", 100, 15);
        e1 = new Enemies("Grom", 100, 20);
        p1Y = groundY;
        

        // Animations
        idleAnimation = new AnimationHandler("src/texture/Sprites/Idle.png", 180, 180, 1, 11, 10);
        runAnimation = new AnimationHandler("src/texture/Sprites/Run.png", 180, 180, 1, 8, 6);
        attackAnimation = new AnimationHandler("src/texture/Sprites/Attack1.png", 180, 180, 1, 7, 5);
        hurtAnimation = new AnimationHandler("src/texture/Sprites/Take Hit.png", 180, 180, 1, 4, 5);
        jumpAnimation = new AnimationHandler("src/texture/Sprites/Jump.png", 180, 180, 1, 3, 5);
        p1CurrentAnimation = idleAnimation; // Default animation for Player 1
        p2CurrentAnimation = idleAnimation; // Default animation for Player 2
        p1Width = idleAnimation.getWidth();
        p2Width = idleAnimation.getWidth();

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

            // Update animations based on player states
            if (p1Hurt) {
                p1CurrentAnimation = hurtAnimation;
            } else if (attacking) {
                p1CurrentAnimation = attackAnimation;
            } else if (jumping) {
                p1CurrentAnimation = jumpAnimation;
            } else if (movingLeft || movingRight) {
                p1CurrentAnimation = runAnimation;
            } else {
                p1CurrentAnimation = idleAnimation;
            }

            if (e1Hurt) {
                p2CurrentAnimation = hurtAnimation;
            } else {
                p2CurrentAnimation = idleAnimation;
            }

            if (movingLeft) {
                p1X = Math.max(0, p1X - 5); // Speed of left movement
            }
            if (movingRight) {
                p1X = Math.min(getWidth() - 40, p1X + 5); // Speed of right movement
            }

            p1CurrentAnimation.update();
            p2CurrentAnimation.update();
            repaint();
        });
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Line
        g.setColor(Color.GRAY);
        g.fillRect(0, groundY + 300, getWidth(), 5);

        // Player 1
        p1CurrentAnimation.draw(g, p1X, p1Y - 60, facingRight);
        g.drawString(p1.getName() + " HP: " + p1.getHealth(), p1X, p1Y - 60);

        // Player 2
        p2CurrentAnimation.draw(g, p2X, groundY - 80, false);
        g.drawString(e1.getName() + " HP: " + e1.getHealth(), p2X, groundY - 70);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!p1.isAlive() || !e1.isAlive()) return;

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
                    velocityY = -10;
                }
            }
            case KeyEvent.VK_SPACE -> {
                if (attacking) return; 
                attacking = true;
                p1CurrentAnimation = attackAnimation;

                if (Math.abs(p1X - p2X) < (p1Width + p2Width) / 4) { // Distance check for attack
                    p1.attack(e1);
                    if (!e1.isAlive()) {
                        showWinner(p1);
                        return;
                    }
                    e1Hurt = true; // Trigger hurt animation for Player 2
                    new Timer(500, evt -> { 
                        e1Hurt = false; 
                        if (Math.abs(p1X - p2X) < (p1Width + p2Width) / 4) {
                            e1.attack(p1);
                            if (!p1.isAlive()) {
                                showWinner(e1);
                                return;
                            }
                            p1Hurt = true; // Trigger hurt animation for Player 1
                            new Timer(500, evt2 -> {
                                p1Hurt = false; 
                                ((Timer) evt2.getSource()).stop();
                            }).start();
                        }
                        new Timer(550, evt3 -> { // Ensure Player 2 attack animation plays fully
                            p2CurrentAnimation = idleAnimation;
                            ((Timer) evt3.getSource()).stop();
                        }).start();
                        ((Timer) evt.getSource()).stop();
                    }).start();
                }

            new Timer(550, evt -> { // Ensure attack animation plays fully
                attacking = false;
                p1CurrentAnimation = idleAnimation;
                ((Timer) evt.getSource()).stop();
            }).start();
        }
    }
}

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> movingLeft = false;
            case KeyEvent.VK_D -> movingRight = false;
        }
    }

    private void showWinner(Player winner) {
        JOptionPane.showMessageDialog(this, "🏆 Winner: " + winner.getName());
        System.exit(0);
    }

    @Override public void keyTyped(KeyEvent e) {}
}