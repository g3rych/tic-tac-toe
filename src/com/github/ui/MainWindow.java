package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class MainWindow extends JFrame {
    private Board model = new Board();
    private JLabel statusBar = new JLabel("Current Player X ");
    PropertyChangeListener listener = evt -> {
        statusBar.setText("Current player " + model.getCurrentPlayer().toString());
    };


    public MainWindow() {
        model = new Board();
        model.addPropertyChangeListener(listener);
        GameMain gameMain = new GameMain(model);
        BoardView view = new BoardView(model, gameMain);

        setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

