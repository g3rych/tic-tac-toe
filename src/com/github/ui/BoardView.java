package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;
import com.github.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class BoardView extends JPanel {
    private Board board;
    private GameMain gameMain;
    private CellView[][] cellView = new CellView[Board.ROWS][Board.COLS];
    PropertyChangeListener listener = e -> {
        Cell source = (Cell) e.getSource();
        int row = source.getRow();
        int col = source.getCol();
        updateFields(row, col);
    };
    public BoardView(Board board,GameMain gameMain) {
        this.board = board;
        this.gameMain = gameMain;
        setLayout(new GridLayout(3,3));
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                cellView[row][col] = new CellView(board.getCell(row,col));
                add(cellView[row][col]);
                board.getCell(row, col).addPropertyChangeSupport(listener);
                cellView[row][col].addActionListener(e -> {
                    CellView source = (CellView) e.getSource();
                    int sourceRow = source.getCell().getRow();
                    int sourceCol = source.getCell().getCol();
                    if (gameMain.isValidInput(sourceRow, sourceCol)) {
                        gameMain.playerMove(sourceRow, sourceCol);
                        //statusBar.setText("Current player " + gameMain.getCurrentPlayer());
                        gameMain.checkWinner();
                    }
                });
            }
        }
    }

    public void updateFields(int row, int col) {
        cellView[row][col].paint();
    }

}


