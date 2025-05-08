package texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AnimationHandler {
    private BufferedImage spriteSheet;
    private int frameWidth, frameHeight;
    private int rows, cols;
    private int currentFrame = 0;
    private int frameDelay, frameCounter = 0;

    public AnimationHandler(String filePath, int frameWidth, int frameHeight, int rows, int cols, int frameDelay) {
        try {
            spriteSheet = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.rows = rows;
        this.cols = cols;
        this.frameDelay = frameDelay;
    }

    public void update() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame = (currentFrame + 1) % (rows * cols); // Переключение между всеми кадрами
        }
    }

    public void draw(Graphics g, int x, int y) {
        int frameX = (currentFrame % cols) * frameWidth; // Координата X текущего кадра
        int frameY = (currentFrame / cols) * frameHeight; // Координата Y текущего кадра
        g.drawImage(spriteSheet, x, y, x + frameWidth, y + frameHeight,
                    frameX, frameY, frameX + frameWidth, frameY + frameHeight, null);
    }
}