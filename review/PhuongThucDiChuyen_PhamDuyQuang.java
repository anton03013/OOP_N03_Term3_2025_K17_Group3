//package gui;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//
//public class BattleController {
//    private final BattleModel model;
//    private final BattleCanvas view;
//
//    public BattleController(BattleModel model, BattleCanvas view) {
//        this.model = model;
//        this.view = view;
//    }
//
//    public void update() {
//        if (model.movingLeft) {
//            model.p1X = Math.max(0, model.p1X - 10);
//        }
//        if (model.movingRight) {
//            model.p1X = Math.min(view.getWidth() - 40, model.p1X + 10);
//        }
//    }
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode(); #nghe bàn phím
//        if (key == KeyEvent.VK_A) {
//            model.movingLeft = true;
//            if (!model.movingRight) model.facingRight = false;
//        } else if (key == KeyEvent.VK_D) {
//            model.movingRight = true;
//            if (!model.movingLeft) model.facingRight = true;
