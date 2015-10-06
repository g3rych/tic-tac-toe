package com.github.controller;

import com.github.model.Board;
import com.github.model.Field;

import javax.swing.*;
import java.util.Random;

public class GameMain {
    private Board board;
    private GameState currentState = GameState.PLAYING;
    private Random random = new Random();

    public GameMain(Board board) {
        this.board = board;
    }

    public void playerMove(int row, int col) {
        board.getCell(row, col).setField(board.getCurrentPlayer());
        board.setCurrentPlayer((board.getCurrentPlayer() == Field.CROSS) ? Field.NOUGHT : Field.CROSS);
        if (board.isEnableAI()) randomMove();
    }

    public void checkWinner() {
        Field winner = Field.EMPTY;
        //3 в строку
        for (int row = 0; row < Board.ROWS; row++)
            if (board.getCell(row, 0).getField() == board.getCell(row, 1).getField() &&
                    board.getCell(row, 1).getField() == board.getCell(row, 2).getField() &&
                    board.getCell(row, 1).getField() != Field.EMPTY)
                winner = board.getCell(row, 0).getField();
        // 3 в ряд
        for (int col = 0; col < Board.COLS; col++)
            if (board.getCell(0, col).getField() == board.getCell(1, col).getField() &&
                    board.getCell(1, col).getField() == board.getCell(2, col).getField() &&
                    board.getCell(1, col).getField() != Field.EMPTY)
                winner = board.getCell(0, col).getField();
        //3 по диагонали
        if (board.getCell(0, 0).getField() == board.getCell(1, 1).getField() &&
                board.getCell(1, 1).getField() == board.getCell(2, 2).getField() &&
                board.getCell(0, 0).getField() != Field.EMPTY)
            winner = board.getCell(0, 0).getField();
        //3 в противоположной диагонали
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
                currentState = GameState.PLAYING;
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

    public void endGame(String winner) {
        int userAnswer;
        userAnswer = JOptionPane.showConfirmDialog(null, "Restart Game?", winner, JOptionPane.YES_NO_OPTION);
        if (userAnswer == JOptionPane.YES_OPTION) {
            board.clear();
            board.setCurrentPlayer(Field.CROSS);
        } else
            System.exit(0);
    }

    public boolean isValidInput(int row, int col) {
        return board.getCell(row, col).getField() == Field.EMPTY;
    }

    public void randomMove() {
        boolean validInput = false;
        do {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (isValidInput(row, col)) {
                board.getCell(row, col).setField(board.getCurrentPlayer());
                board.setCurrentPlayer((board.getCurrentPlayer() == Field.CROSS) ? Field.NOUGHT : Field.CROSS);
                validInput = true;
            }
        } while (!validInput);
    }
}

