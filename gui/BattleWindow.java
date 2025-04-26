package gui;

import javax.swing.*;

public class BattleWindow extends JFrame {
    public BattleWindow() {
        setTitle("🎮 WASD Battle Arena");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new BattleCanvas());
        setVisible(true);
    }
}
