package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleCanvas extends JPanel implements KeyListener {
    private final BattleModel model;
    private final BattleController controller;

    public BattleCanvas() {
        setFocusable(true);
        model = new BattleModel();
        controller = new BattleController(model, this);
        addKeyListener(this);

        Timer gameLoop = new Timer(16, e -> {
            controller.update();
            repaint();
        });
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.render(g, getWidth(), getHeight());
    }

    @Override public void keyPressed(KeyEvent e) { controller.keyPressed(e); }
    @Override public void keyReleased(KeyEvent e) { controller.keyReleased(e); }
    @Override public void keyTyped(KeyEvent e) {}
}