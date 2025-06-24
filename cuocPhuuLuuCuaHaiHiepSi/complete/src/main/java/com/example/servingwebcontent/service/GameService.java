package com.example.servingwebcontent.service;

import com.example.servingwebcontent.Classes.*;
import com.example.servingwebcontent.dto.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private Player p1;
    private Enemies e1;
    private boolean facingRight = true;
    private int p1X = 50, p2X = 300, p1Y, groundY = 500;
    private boolean jumping = false, movingLeft = false, movingRight = false;
    private boolean p1Hurt = false, e1Hurt = false, attacking = false;
    private int velocityY = 0, p1Width = 205, p2Width = 160;
    private List<Platforms> platforms = new ArrayList<>();
    private boolean enemyMovingRight = true, enemyMovingLeft = false, enemyFacingRight = true;
    private int enemySpeed = 4, enemyPauseTimer = 0;
    private final int enemyPauseDuration = 160;
    private boolean enemyAttacking = false;
    private boolean gameOver = false;
    private String winner = null;
    private boolean onPlatform = false;

    // Enemy fields
    private int enemyY = groundY + 100;
    private int enemyVelocityY = 0;
    private boolean enemyJumping = false;
    private boolean enemyOnPlatform = false;
    private int enemyHitboxOffsetX = -300;
    private int enemyHitboxOffsetY = -300;
    private int enemyHitboxWidth = p2Width;
    private int enemyHitboxHeight = 160;
    private boolean enemyFrozen = false;

    public GameService() {
        initializeGame();
    }

    private void initializeGame() {
        p1 = new Player("John", 100, 15);
        e1 = new Enemies("Grom", 100, 20);
        p1Y = groundY;
        platforms.add(new Platforms(200, 200, 150, 10));
        platforms.add(new Platforms(500, 350, 150, 10));
    }

    public GameState getGameState() {
        List<PlatformState> platformStates = new ArrayList<>();
        for (Platforms platform : platforms) {
            platformStates.add(new PlatformState(
                platform.getX(), platform.getY(), 
                platform.getWidth(), platform.getHeight()
            ));
        }
        PlayerState player1State = new PlayerState(
            p1.getName(), p1.getHealth(), p1.getStrength(),
            p1X, p1Y, facingRight, movingLeft, movingRight,
            jumping, attacking, p1Hurt, velocityY, getCurrentAnimation()
        );
        PlayerState enemyState = new PlayerState(
            e1.getName(), e1.getHealth(), e1.getStrength(),
            p2X, enemyY, enemyFacingRight, enemyMovingLeft, enemyMovingRight,
            enemyJumping, enemyAttacking, e1Hurt, enemyVelocityY, getEnemyCurrentAnimation()
        );
        return new GameState(player1State, enemyState, platformStates, gameOver, winner, groundY);
    }

    public void processInput(GameInput input) {
        if (gameOver) return;
        movingLeft = input.isKeyA();
        movingRight = input.isKeyD();
        if (movingLeft && !movingRight) {
            facingRight = false;
        } else if (movingRight && !movingLeft) {
            facingRight = true;
        }
        if (input.isKeyW() && !jumping && (p1Y >= groundY || onPlatform)) {
            jumping = true;
            velocityY = -20;
        }
        if (input.isKeySpace() && !attacking) {
            performAttack();
        }
    }

    public void update() {
        if (gameOver) return;
        updatePlayerPhysics();
        updateEnemy();
        checkCollisions();
        checkGameOver();
    }

    private void updatePlayerPhysics() {
        if (jumping) {
            p1Y += velocityY;
            velocityY += 1;
            if (p1Y >= groundY) {
                p1Y = groundY;
                jumping = false;
                velocityY = 0;
            }
        }
        if (movingLeft) {
            p1X = Math.max(0-100, p1X - 10);
        }
        if (movingRight) {
            p1X = Math.min(1280 - 100, p1X + 10);
        }
        onPlatform = false;
        int playerHeight = 0; // Set real player height if needed
        for (Platforms platform : platforms) {
            if (p1X + p1Width > platform.getX() && p1X < platform.getX() + platform.getWidth()) {
                int playerFeet = p1Y + playerHeight;
                if (velocityY >= 0 && playerFeet <= platform.getY() &&
                    playerFeet + velocityY >= platform.getY()) {
                    p1Y = platform.getY() - playerHeight;
                    if (jumping) {
                        jumping = false;
                        velocityY = 0;
                    }
                    onPlatform = true;
                    break;
                }
            }
        }
        if (!onPlatform && p1Y < groundY) {
            if (!jumping) {
                jumping = true;
            }
        } else if (!onPlatform && p1Y >= groundY) {
            p1Y = groundY;
            if (jumping) {
                jumping = false;
                velocityY = 0;
            }
        }
    }

    private void updateEnemy() {
        if (enemyFrozen) return;
        int leftBound = 350;
        int rightBound = 650;
        if (enemyPauseTimer > 0) {
            enemyPauseTimer--;
            enemyMovingLeft = false;
            enemyMovingRight = false;
            return;
        }
        if (enemyMovingRight) {
            p2X += enemySpeed;
            if (p2X >= rightBound) {
                enemyMovingRight = false;
                enemyPauseTimer = enemyPauseDuration;
            }
        } else if (enemyMovingLeft) {
            p2X -= enemySpeed;
            if (p2X <= leftBound) {
                enemyMovingLeft = false;
                enemyPauseTimer = enemyPauseDuration;
            }
        } else {
            if (p2X <= leftBound) {
                enemyMovingRight = true;
            } else if (p2X >= rightBound) {
                enemyMovingLeft = true;
            } else {
                enemyMovingRight = true;
            }
        }
        if (enemyMovingRight) {
            enemyFacingRight = true;
        } else if (enemyMovingLeft) {
            enemyFacingRight = false;
        }
        if (enemyJumping) {
            enemyY += enemyVelocityY;
            enemyVelocityY += 1;
            if (enemyY >= groundY - 100) {
                enemyY = groundY - 100;
                enemyJumping = false;
                enemyVelocityY = 0;
            }
        }
        enemyOnPlatform = false;
        int enemyHeight = 100; // Set real enemy height if needed
        for (Platforms platform : platforms) {
            if (p2X + p2Width > platform.getX() && p2X < platform.getX() + platform.getWidth()) {
                int enemyFeet = enemyY + enemyHeight;
                if (enemyVelocityY >= 0 && enemyFeet <= platform.getY() &&
                    enemyFeet + enemyVelocityY >= platform.getY()) {
                    enemyY = platform.getY() - enemyHeight;
                    if (enemyJumping) {
                        enemyJumping = false;
                        enemyVelocityY = 0;
                    }
                    enemyOnPlatform = true;
                    break;
                }
            }
        }
        if (!enemyOnPlatform && enemyY < groundY - 100) {
            if (!enemyJumping) {
                enemyJumping = true;
            }
        } else if (!enemyOnPlatform && enemyY >= groundY - 100) {
            enemyY = groundY - 100;
            if (enemyJumping) {
                enemyJumping = false;
                enemyVelocityY = 0;
            }
        }
    }

    private void performAttack() {
        if (attacking) return;
        attacking = true;
        int enemyHitboxX = p2X + enemyHitboxOffsetX;
        int enemyHitboxY = enemyY + enemyHitboxOffsetY;
        boolean xOverlap = (p1X + p1Width > enemyHitboxX) && (p1X < enemyHitboxX + enemyHitboxWidth);
        boolean yOverlap = (p1Y < enemyHitboxY + enemyHitboxHeight) && (p1Y + 114 > enemyHitboxY); // 114 = player height
        if (xOverlap && yOverlap) {
            p1.attack(e1);
            if (!e1.isAlive()) {
                gameOver = true;
                winner = p1.getName();
                return;
            }
            e1Hurt = true;
            scheduleEnemyAttack();
        }
        scheduleAttackReset();
    }

    private void scheduleEnemyAttack() {
        new Thread(() -> {
            try {
                enemyFrozen = true;
                enemyMovingLeft = false;
                enemyMovingRight = false;
                enemyAttacking = true;
                e1Hurt = true;

                Thread.sleep(800);

                int enemyHitboxX = p2X + enemyHitboxOffsetX;
                int enemyHitboxY = enemyY + enemyHitboxOffsetY;
                boolean xOverlap = (p1X + p1Width > enemyHitboxX) && (p1X < enemyHitboxX + enemyHitboxWidth);
                boolean yOverlap = (p1Y < enemyHitboxY + enemyHitboxHeight) && (p1Y + 114 > enemyHitboxY);
                if (!gameOver && xOverlap && yOverlap) {
                    e1.attack(p1);
                    if (!p1.isAlive()) {
                        gameOver = true;
                        winner = e1.getName();
                        return;
                    }
                    p1Hurt = true;
                    Thread.sleep(700); // Short player hurt animation
                    p1Hurt = false;
                }

                enemyAttacking = false;
                e1Hurt = false;
                enemyFrozen = false;
                enemyMovingRight = true;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void scheduleAttackReset() {
        new Thread(() -> {
            try {
                Thread.sleep(550);
                attacking = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void checkCollisions() {
        // Implement collision logic if needed
    }

    private void checkGameOver() {
        if (!p1.isAlive() || !e1.isAlive()) {
            gameOver = true;
            winner = p1.isAlive() ? p1.getName() : e1.getName();
        }
    }

    private String getCurrentAnimation() {
        if (p1Hurt) return "hurt";
        if (attacking) return "attack";
        if (jumping) return "jump";
        if (movingLeft || movingRight) return "run";
        return "idle";
    }

    private String getEnemyCurrentAnimation() {
        if (enemyAttacking) return "attack";
        if (e1Hurt) return "hurt";
        if (enemyMovingLeft || enemyMovingRight) return "run";
        return "idle";
    }

    public void resetGame() {
        initializeGame();
        gameOver = false;
        winner = null;
        facingRight = true;
        p1X = 50;
        p2X = 300;
        p1Y = groundY;
        jumping = false;
        movingLeft = false;
        movingRight = false;
        p1Hurt = false;
        e1Hurt = false;
        attacking = false;
        velocityY = 0;
        enemyMovingRight = true;
        enemyMovingLeft = false;
        enemyFacingRight = true;
        enemyPauseTimer = 0;
        enemyAttacking = false;
    }
}