package com.github.ui;

import com.github.controller.GameMain;
import com.github.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private Board board;
    private GameMain gameMain;
    private CellView[][] cellView = new CellView[Board.ROWS][Board.COLS];

    public BoardView(Board board,GameMain gameMain) {
        this.board = board;
        this.gameMain = gameMain;
        setLayout(new GridLayout(3,3));
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                cellView[row][col] = new CellView(board.getCell(row,col));
                add(cellView[row][col]);
                cellView[row][col].addActionListener(e -> {
                    CellView source = (CellView) e.getSource();
                    gameMain.playerMove(source.getCell().getRow(),source.getCell().getCol());
                    source.setText(updateFields(source));
                    gameMain.checkWinner();
                });
            }
        }
    }
    public String updateFields(CellView s) {
        String field = "";
        switch (s.getCell().getField()) {
            case CROSS: field = "X";break;
            case NOUGHT: field = "O"; break;
            default:break;
        }
        return field;
    }
}


