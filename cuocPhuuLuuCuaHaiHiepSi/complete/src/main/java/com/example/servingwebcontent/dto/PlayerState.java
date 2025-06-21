package com.example.servingwebcontent.dto;

public class PlayerState {
    private String name;
    private int health;
    private int strength;
    private int x;
    private int y;
    private boolean facingRight;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean jumping;
    private boolean attacking;
    private boolean hurt;
    private int velocityY;
    private String currentAnimation;

    public PlayerState() {}

    public PlayerState(String name, int health, int strength, int x, int y, 
                      boolean facingRight, boolean movingLeft, boolean movingRight,
                      boolean jumping, boolean attacking, boolean hurt, 
                      int velocityY, String currentAnimation) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.facingRight = facingRight;
        this.movingLeft = movingLeft;
        this.movingRight = movingRight;
        this.jumping = jumping;
        this.attacking = attacking;
        this.hurt = hurt;
        this.velocityY = velocityY;
        this.currentAnimation = currentAnimation;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public boolean isFacingRight() { return facingRight; }
    public void setFacingRight(boolean facingRight) { this.facingRight = facingRight; }
    
    public boolean isMovingLeft() { return movingLeft; }
    public void setMovingLeft(boolean movingLeft) { this.movingLeft = movingLeft; }
    
    public boolean isMovingRight() { return movingRight; }
    public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }
    
    public boolean isJumping() { return jumping; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    
    public boolean isAttacking() { return attacking; }
    public void setAttacking(boolean attacking) { this.attacking = attacking; }
    
    public boolean isHurt() { return hurt; }
    public void setHurt(boolean hurt) { this.hurt = hurt; }
    
    public int getVelocityY() { return velocityY; }
    public void setVelocityY(int velocityY) { this.velocityY = velocityY; }
    
    public String getCurrentAnimation() { return currentAnimation; }
    public void setCurrentAnimation(String currentAnimation) { this.currentAnimation = currentAnimation; }
} 