package com.github.controller;

import com.github.model.Board;
import com.github.model.Space;

import javax.swing.*;

public class GameMain {
    private Board board;
    private Space currentPlayer = Space.CROSS;
    private GameState currentState = GameState.PLAYING;

    public GameMain(Board board) {
        this.board = board;
    }

    public void playerMove(int row, int col) {
        board.getCell(row, col).setSpace(currentPlayer);
        currentPlayer = (currentPlayer == Space.CROSS) ? Space.NOUGHT : Space.CROSS;
    }

    public void checkWinner() {
        Space winner = Space.EMPTY;
        //3 in row
        for (int row = 0; row < Board.ROWS; row++)
            if (board.getCell(row, 0).getSpace() == board.getCell(row, 1).getSpace() &&
                    board.getCell(row, 1).getSpace() == board.getCell(row, 2).getSpace() &&
                    board.getCell(row, 1).getSpace() != Space.EMPTY)
                winner = board.getCell(row, 0).getSpace();
        // 3 in col
        for (int col = 0; col < Board.COLS; col++)
            if (board.getCell(0, col).getSpace() == board.getCell(1, col).getSpace() &&
                    board.getCell(1, col).getSpace() == board.getCell(2, col).getSpace() &&
                    board.getCell(1, col).getSpace() != Space.EMPTY)
                winner = board.getCell(0, col).getSpace();
        //3 in diagonal
        if (board.getCell(0, 0).getSpace() == board.getCell(1, 1).getSpace() &&
                board.getCell(1, 1).getSpace() == board.getCell(2, 2).getSpace() &&
                board.getCell(0, 0).getSpace() != Space.EMPTY)
            winner = board.getCell(0, 0).getSpace();
        //3 in opposite diagonal
        if (board.getCell(0, 2).getSpace() == board.getCell(1, 1).getSpace() &&
                board.getCell(1, 1).getSpace() == board.getCell(2, 0).getSpace() &&
                board.getCell(0, 2).getSpace() != Space.EMPTY)
            winner = board.getCell(0, 2).getSpace();

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
                if (board.getCell(row, col).getSpace() == Space.EMPTY) return false;
            }
        }
        return true;
    }

    public void endGame(String s) {
        JOptionPane.showMessageDialog(null, s, "Game Over", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    public boolean isValidInput(int row, int col) {
        return board.getCell(row, col).getSpace() == Space.EMPTY;
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

