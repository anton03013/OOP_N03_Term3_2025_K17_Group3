package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.*;

public class BattleWindow extends JFrame {
    private JLabel statusP1, statusP2;
    private JButton fightButton;

    private Player p1;
    private Warrior p2;

    public BattleWindow() {
        p1 = new Player("Anthoni", 100);
        p2 = new Warrior("Grom", 100, 20);

        setTitle("⚔️ Battle Arena");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusP1 = new JLabel();
        statusP2 = new JLabel();
        updateStatus();

        fightButton = new JButton("Attack!");
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.isAlive() && p2.isAlive()) {
                    p1.attack(p2);
                    if (p2.isAlive()) p2.attack(p1);
                    updateStatus();
                    if (!p1.isAlive() || !p2.isAlive()) {
                        fightButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null,
                            "🏆 Победитель: " + (p1.isAlive() ? p1.getName() : p2.getName()));
                    }
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(statusP1);
        panel.add(statusP2);
        panel.add(fightButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void updateStatus() {
        statusP1.setText(p1.getName() + " HP: " + p1.getHealth());
        statusP2.setText(p2.getName() + " HP: " + p2.getHealth());
    }
}
