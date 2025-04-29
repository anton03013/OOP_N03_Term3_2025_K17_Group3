public class MainCharacter {
    private int x, y;
    private int velocityX, velocityY;
    private int health;

    private boolean isJumping;
    private boolean isAlive;
    private String currentAnimation; // Thêm trạng thái animation

    public MainCharacter(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.health = 100;
        this.isAlive = true;
        this.isJumping = false;
        this.velocityX = 0;
        this.velocityY = 0;
        this.currentAnimation = "Idle"; // animation mặc định
    }
    public int getHealth() {
        return health;
    }

    public void moveLeft() {
        velocityX = -5;
        x += velocityX;
        currentAnimation = "Running Left";
    }

    public void moveRight() {
        velocityX = 5;
        x += velocityX;
        currentAnimation = "Running Right";
    }

    public void jump() {
        if (!isJumping) {
            velocityY = -15;
            y += velocityY;
            isJumping = true;
            currentAnimation = "Jumping";
        }
    }

    public void fall() {
        velocityY += 1;
        y += velocityY;

        if (y >= 0) {
            y = 0;
            isJumping = false;
            velocityY = 0;
            currentAnimation = "Idle"; // trở về Idle khi rơi xong
        }
    }

    public void attack() {
        System.out.println("Tấn công!");
        currentAnimation = "Attacking";
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Bị trúng đòn! Máu còn: " + health);
        currentAnimation = "Taking Damage";

        if (health <= 0) {
            die();
        }
    }

    private void die() {
        isAlive = false;
        currentAnimation = "Dead";
        System.out.println("Đã chết!");
    }

    public void update() {
        if (isJumping) {
            fall();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }
}
