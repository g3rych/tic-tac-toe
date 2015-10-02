package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;

import javax.swing.*;

public class TTT {
    public static void main(String[] args) {
        Board board = new Board();
        GameMain gameMain = new GameMain(board);
        BoardView view = new BoardView(board, gameMain);
        new JFrame() {
            {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {

                }
                setLocationRelativeTo(null);
                setVisible(true);
                add(view);
                pack();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };
    }
}
