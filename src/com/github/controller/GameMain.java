package com.github.controller;

import com.github.model.Board;
import com.github.model.Field;

import javax.swing.*;

public class GameMain {
    private Board board;
    private Field currentPlayer = Field.CROSS;
    private GameState currentState = GameState.PLAYING;

    public GameMain(Board board) {
        this.board = board;
    }

    public void playerMove(int row, int col) {
        board.getCell(row, col).setField(currentPlayer);
        currentPlayer = (currentPlayer == Field.CROSS) ? Field.NOUGHT : Field.CROSS;
    }

    public void checkWinner() {
        Field winner = Field.EMPTY;
        //3 in row
        for (int row = 0; row < Board.ROWS; row++)
            if (board.getCell(row, 0).getField() == board.getCell(row, 1).getField() &&
                    board.getCell(row, 1).getField() == board.getCell(row, 2).getField() &&
                    board.getCell(row, 1).getField() != Field.EMPTY)
                winner = board.getCell(row, 0).getField();
        // 3 in col
        for (int col = 0; col < Board.COLS; col++)
            if (board.getCell(0, col).getField() == board.getCell(1, col).getField() &&
                    board.getCell(1, col).getField() == board.getCell(2, col).getField() &&
                    board.getCell(1, col).getField() != Field.EMPTY)
                winner = board.getCell(0, col).getField();
        //3 in diagonal
        if (board.getCell(0, 0).getField() == board.getCell(1, 1).getField() &&
                board.getCell(1, 1).getField() == board.getCell(2, 2).getField() &&
                board.getCell(0, 0).getField() != Field.EMPTY)
            winner = board.getCell(0, 0).getField();
        //3 in opposite diagonal
        if (board.getCell(0, 2).getField() == board.getCell(1, 1).getField() &&
                board.getCell(1, 1).getField() == board.getCell(2, 0).getField() &&
                board.getCell(0, 2).getField() != Field.EMPTY)
            winner = board.getCell(0, 2).getField();

        switch (winner) {
            case CROSS:
                currentState = GameState.CROSSWIN;
                break;
            case NOUGHT:
                currentState = GameState.NOUGHTWIN;
                break;
            default:
                break;
        }
        if (currentState == GameState.CROSSWIN)
            endGame("CROSS WIN");
        else if (currentState == GameState.NOUGHTWIN)
            endGame("NOUGHT WIN");
        else if (isTie()) {
            currentState = GameState.TIE;
            endGame("It's TIE");
        }
    }

    public boolean isTie() {
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                if (board.getCell(row, col).getField() == Field.EMPTY) return false;
            }
        }
        return true;
    }

    public void endGame(String s) {
        int userAnswer;
        userAnswer = JOptionPane.showConfirmDialog(null, s, "Game Over", JOptionPane.YES_NO_OPTION);
        if (userAnswer == JOptionPane.YES_OPTION) {
            board.clear();
        }
        //System.exit(0);
    }

    public boolean isValidInput(int row, int col) {
        return board.getCell(row, col).getField() == Field.EMPTY;
    }

    public String getCurrentPlayer() {
        switch (currentPlayer) {
            case CROSS:
                return "CROSS";
            case NOUGHT:
                return "NOUGHT";
            default:
                return "CROSS";
        }
    }
}

