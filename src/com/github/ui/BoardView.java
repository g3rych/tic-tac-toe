package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;
import com.github.model.Cell;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    Board board;
    GameMain gameMain;
    JPanel boardPanel = new JPanel();
    JLabel statusBar = new JLabel(" ", JLabel.CENTER);


    public BoardView() {
        setLayout(new BorderLayout());
        boardPanel.setLayout(new GridLayout(3, 3));
        add(boardPanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

    }

    public BoardView(Board board, GameMain gameMain) {
        this();
        this.board = board;
        this.gameMain = gameMain;
        statusBar.setText("Current player " + gameMain.getCurrentPlayer());
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                board.getCell(row, col).setFocusable(false);
                boardPanel.add(board.getCell(row, col));
                board.getCell(row, col).addActionListener(evt -> {
                    Cell source = (Cell) evt.getSource();
                    if (gameMain.isValidInput(source.getRow(), source.getCol())) {
                        gameMain.playerMove(source.getRow(), source.getCol());
                        statusBar.setText("Current player " + gameMain.getCurrentPlayer());
                    }
                    paint();
                    gameMain.checkWinner();
                });
            }
        }
    }

    public void paint() {
        for (int row = 0; row < Board.ROWS; row++)
            for (int col = 0; col < Board.COLS; col++)
                board.getCell(row, col).paint();
    }
}
