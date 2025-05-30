package gui;

import javax.swing.*;

public class BattleWindow extends JFrame {
    public BattleWindow() {
        setTitle("🎮 Battle Arena");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new BattleCanvas());
        setVisible(true);
    }
    public int getHeight() {
        return getSize().height;
    }
    public int getWidth() {
        return getSize().width;
    }
}
