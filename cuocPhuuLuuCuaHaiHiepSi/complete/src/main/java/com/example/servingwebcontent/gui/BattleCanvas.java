package com.example.servingwebcontent.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleCanvas extends JPanel implements KeyListener {
    private final BattleModel model;
    private final BattleController controller;
    private Image background1;
    private Image background2;
    private Image background3;
    private Image background4;

    public BattleCanvas() {
        setFocusable(true);
        model = new BattleModel();
        controller = new BattleController(model, this);
        addKeyListener(this);
        
        background1 = new ImageIcon("src/texture/Background/background1.png").getImage();
        background2 = new ImageIcon("src/texture/Background/background2.png").getImage();
        background3 = new ImageIcon("src/texture/Background/background3.png").getImage();
        background4 = new ImageIcon("src/texture/Background/background4a.png").getImage();

        Timer gameLoop = new Timer(16, e -> {
            controller.update();
            repaint();
        });
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background1, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background2, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background3, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background4, 0, 0, getWidth(), getHeight(), this);

        controller.render(g, getWidth(), getHeight());
    }

    @Override public void keyPressed(KeyEvent e) { controller.keyPressed(e); }
    @Override public void keyReleased(KeyEvent e) { controller.keyReleased(e); }
    @Override public void keyTyped(KeyEvent e) {}
}