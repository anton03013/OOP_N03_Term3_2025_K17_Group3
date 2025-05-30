package gui;

import Classes.*;
import texture.AnimationHandler;
import java.util.ArrayList;
import java.util.List;

public class BattleModel {
    public Player p1;
    public Enemies e1;
    public boolean facingRight = true;
    public int p1X = 50, p2X = 300, p1Y, groundY;
    public boolean jumping = false, movingLeft = false, movingRight = false;
    public boolean p1Hurt = false, e1Hurt = false, attacking = false;
    public int velocityY = 0, p1Width, p2Width;
    public AnimationHandler p1CurrentAnimation, p2CurrentAnimation;
    public AnimationHandler idleAnimation, runAnimation, attackAnimation, hurtAnimation, jumpAnimation, e1idle, e1run, e1attack;
    public List<Platforms> platforms = new ArrayList<>();
    public boolean enemyMovingRight = true, enemyMovingLeft = false, enemyFacingRight = true;
    public int enemySpeed = 4, enemyPauseTimer = 0;
    public final int enemyPauseDuration = 160;
    public static final int PLAYER_HEIGHT = 0;
    public boolean enemyAttacking = false;

    public BattleModel() {
        p1 = new Player("John", 100, 15);
        e1 = new Enemies("Grom", 100, 20);
        e1run = new AnimationHandler("src/texture/Nightborne/Run.png", 160, 160, 1, 6, 6);
        e1idle = new AnimationHandler("src/texture/Nightborne/Idle1.png", 160, 160, 1, 9, 6);
        e1attack = new AnimationHandler("src/texture/Nightborne/Attack.png", 160, 160, 1, 12, 4);
        idleAnimation = new AnimationHandler("src/texture/Sprites/Idle1.png", 205, 114, 1, 3, 12);
        runAnimation = new AnimationHandler("src/texture/Sprites/runner.png", 215, 101, 1, 5, 3);
        attackAnimation = new AnimationHandler("src/texture/Sprites/ATKfinal.png", 208, 108, 1, 8, 4);
        hurtAnimation = new AnimationHandler("src/texture/Sprites/gethit.png", 250, 108, 1, 2, 5);
        jumpAnimation = new AnimationHandler("src/texture/Sprites/JUMP1.png", 200, 100, 1, 3, 5);
        p1CurrentAnimation = idleAnimation;
        p2CurrentAnimation = e1idle;
        p1Width = idleAnimation.getWidth();
        p2Width = idleAnimation.getWidth();
        platforms.add(new Platforms(200, 600, 150, 10));
        platforms.add(new Platforms(500, 500, 150, 10));
    }
}
