package com.example.servingwebcontent.Classes;

import java.awt.Color;
import java.awt.Graphics;

public class Platforms {
    private int x;
    private int y;
    private int width;
    private int height;

    public Platforms(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x - 100, y + 25, width + 190, height);
    }
}
