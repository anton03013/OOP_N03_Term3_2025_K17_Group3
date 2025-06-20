package com.example.servingwebcontent.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.example.servingwebcontent.Classes.Platforms;

public class BattleController {
    private final BattleModel model;
    private final BattleCanvas view;

    public BattleController(BattleModel model, BattleCanvas view) {
        this.model = model;
        this.view = view;
    }

    public void update() {
        if (model.jumping) {
            model.p1Y += model.velocityY;
            model.velocityY += 1;
            if (model.p1Y >= model.groundY) {
                model.p1Y = model.groundY;
                model.jumping = false;
                model.velocityY = 0;
            }
        }

        if (model.p1Hurt) {
            model.p1CurrentAnimation = model.hurtAnimation;
        } else if (model.attacking) {
            model.p1CurrentAnimation = model.attackAnimation;
        } else if (model.jumping) {
            model.p1CurrentAnimation = model.jumpAnimation;
        } else if (model.movingLeft ^ model.movingRight) {
            model.p1CurrentAnimation = model.runAnimation;
        } else {
            model.p1CurrentAnimation = model.idleAnimation;
        }

        model.enemyMovingLeft = false;
        model.enemyMovingRight = false;

        if (model.e1Hurt) {
            model.p2CurrentAnimation = model.e1idle;
        } else if (model.enemyAttacking) {
            model.p2CurrentAnimation = model.e1attack;
        } else if (model.enemyMovingRight) {
            model.enemyFacingRight = true;
            model.p2CurrentAnimation = model.e1run;
        } else if (model.enemyMovingLeft) {
            model.enemyFacingRight = false;
            model.p2CurrentAnimation = model.e1run;
        } else {
            model.p2CurrentAnimation = model.e1idle;
        }
        if (model.movingLeft) {
            model.p1X = Math.max(0, model.p1X - 10);
        }
        if (model.movingRight) {
            model.p1X = Math.min(view.getWidth() - 40, model.p1X + 10);
        }

        boolean onPlatform = false;
        for (var platform : model.platforms) {
            if (model.p1X + model.p1Width > platform.getX() && model.p1X < platform.getX() + platform.getWidth()) {
                int playerFeet = model.p1Y + BattleModel.PLAYER_HEIGHT;
                if (model.velocityY >= 0
                        && playerFeet <= platform.getY()
                        && playerFeet + model.velocityY >= platform.getY()) {
                    model.p1Y = platform.getY() - BattleModel.PLAYER_HEIGHT;
                    if (model.jumping) {
                        model.jumping = false;
                        model.velocityY = 0;
                    }
                    onPlatform = true;
                    break;
                }
            }
        }
        if (!onPlatform && model.p1Y < model.groundY) {
            if (!model.jumping) {
                model.jumping = true;
            }
        } else if (!onPlatform && model.p1Y >= model.groundY) {
            model.p1Y = model.groundY;
            if (model.jumping) {
                model.jumping = false;
                model.velocityY = 0;
            }
        }

        //moveEnemy();

        model.p1CurrentAnimation.update();
        model.p2CurrentAnimation.update();
    }

    private void moveEnemy() {
        int leftBound = 300;
        int rightBound = 700;

        if (model.enemyPauseTimer > 0) {
            model.enemyPauseTimer--;
            model.enemyMovingLeft = false;
            model.enemyMovingRight = false;
            model.p2CurrentAnimation = model.e1idle;
            return;
        }

        if (model.enemyMovingRight) {
            model.p2X += model.enemySpeed;
            if (model.p2X >= rightBound) {
                model.enemyMovingRight = false;
                model.enemyPauseTimer = model.enemyPauseDuration;
            }
        } else if (model.enemyMovingLeft) {
            model.p2X -= model.enemySpeed;
            if (model.p2X <= leftBound) {
                model.enemyMovingLeft = false;
                model.enemyPauseTimer = model.enemyPauseDuration;
            }
        } else {
            if (model.p2X <= leftBound) {
                model.enemyMovingRight = true;
            } else if (model.p2X >= rightBound) {
                model.enemyMovingLeft = true;
            } else {
                model.enemyMovingRight = true;
            }
        }
    }

    public void render(Graphics g, int width, int height) {
        model.groundY = height - 100;
        g.setColor(Color.GREEN);
        g.fillRect(0, model.groundY + 30, width, 5);

        for (var platform : model.platforms) {
            platform.draw(g);
        }

        if (model.p1Y == 0 || model.p1Y > model.groundY) {
            model.p1Y = model.groundY;
        }
        model.p1CurrentAnimation.draw(g, model.p1X, model.p1Y - 75, model.facingRight);
        g.drawString(model.p1.getName() + " HP: " + model.p1.getHealth(), 70, view.getHeight() - 800);

        model.p2CurrentAnimation.draw(g, model.p2X, model.groundY - 100, model.enemyFacingRight);
        g.drawString(model.e1.getName() + " HP: " + model.e1.getHealth(), model.p2X, model.groundY - 70);
    }

    public void keyPressed(KeyEvent e) {
        if (!model.p1.isAlive() || !model.e1.isAlive()) return;

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            model.movingLeft = true;
            if (!model.movingRight) model.facingRight = false;
        } else if (key == KeyEvent.VK_D) {
            model.movingRight = true;
            if (!model.movingLeft)
                model.facingRight = true;
        } else if (key == KeyEvent.VK_SPACE) {
            try {
                if (model.attacking) return;

                model.attacking = true;
                model.p1CurrentAnimation = model.attackAnimation;

                if (Math.abs(model.p1X - model.p2X) < (model.p1Width + model.p2Width) / 4) {
                    model.p1.attack(model.e1);
                    if (!model.e1.isAlive()) {
                        showWinner(model.p1.getName());
                        return;
                    }

                    model.e1Hurt = true;
                    new Timer(500, evt -> {
                        model.e1Hurt = false;
                        model.enemyAttacking = true;
                        if (Math.abs(model.p1X - model.p2X) < (model.p1Width + model.p2Width) / 4) {
                            model.e1.attack(model.p1);
                            if (!model.p1.isAlive()) {
                                showWinner(model.e1.getName());
                                return;
                            }
                            model.p1Hurt = true;
                            new Timer(900, evt2 -> {
                                model.enemyAttacking = false;
                                model.p1Hurt = false;
                                ((Timer) evt2.getSource()).stop();
                            }).start();
                        }
                        ((Timer) evt.getSource()).stop();
                    }).start();
                }

                new Timer(550, evt -> {
                    model.attacking = false;
                    model.p1CurrentAnimation = model.idleAnimation;
                    ((Timer) evt.getSource()).stop();
                }).start();
            } catch (Exception ex) {
                System.out.println("Đã xảy ra lỗi khi xử lý phím Space: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                System.out.println("Kết thúc xử lý phím Space.");
            }
        } else if (key == KeyEvent.VK_W) {
                try {
                    if (!model.jumping) {
                        model.jumping = true;
                        model.velocityY = -20;
                    }
                } catch (Exception ex) {
                    System.out.println("Lỗi khi nhấn W để nhảy: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    System.out.println("Kết thúc xử lý phím W.");
                }

        } else if (key == KeyEvent.VK_E) {
            model.enemyAttacking = true;
            new Timer(500, evt -> {
                model.enemyAttacking = false;
                ((Timer) evt.getSource()).stop();
            }).start();
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> model.movingLeft = false;
            case KeyEvent.VK_D -> model.movingRight = false;
        }
    }
    private void showWinner(String winnerName) {
        JOptionPane.showMessageDialog(view, "Người chiến thắng là: " + winnerName, "Chiến thắng", JOptionPane.INFORMATION_MESSAGE);
    }
}
