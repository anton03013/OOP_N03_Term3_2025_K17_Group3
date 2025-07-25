
package com.example.servingwebcontent.texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class  AnimationHandler {
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
            currentFrame = (currentFrame + 1) % (rows * cols); // Frames loop
        }
    }

    public void draw(Graphics g, int x, int y, boolean facingRight) {
        int frameX = (currentFrame % cols) * frameWidth;
        int frameY = (currentFrame / cols) * frameHeight;

        Graphics2D g2d = (Graphics2D) g;

        if (facingRight) {
            g2d.drawImage(spriteSheet, x, y, x + frameWidth, y + frameHeight,
                    frameX, frameY, frameX + frameWidth, frameY + frameHeight, null);
        } else {
            // Draw flipped horizontally
            g2d.drawImage(spriteSheet, x + frameWidth, y, x, y + frameHeight,
                    frameX, frameY, frameX + frameWidth, frameY + frameHeight, null);
        }
    }
    public int getWidth() {
        return frameWidth;
    }
    public int getHeight() {
        return frameHeight;
    }

}
