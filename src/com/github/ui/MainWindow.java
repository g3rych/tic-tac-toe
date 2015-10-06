package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class MainWindow extends JFrame {
    JMenuBar menuBar = new JMenuBar();
    JMenu menuGame = new JMenu("Game");
    JCheckBoxMenuItem enableAICheck = new JCheckBoxMenuItem("Enable AI");
    private Board board;
    private JLabel statusBar = new JLabel("Current Player X ");
    PropertyChangeListener listener = evt -> {
        statusBar.setText("Current player " + board.getCurrentPlayer().toString());
    };


    public MainWindow() {
        board = new Board();
        board.addPropertyChangeListener(listener);
        GameMain gameMain = new GameMain(board);
        BoardView view = new BoardView(board, gameMain);


        setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        setJMenuBar(menuBar);
        menuBar.add(menuGame);
        menuGame.add(enableAICheck);
        enableAICheck.setState(board.isEnableAI());

        enableAICheck.addActionListener(e -> {
            board.setEnableAI(true);
        });


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

