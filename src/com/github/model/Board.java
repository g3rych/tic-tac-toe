package com.github.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Board {
    public static int ROWS = 3;
    public static int COLS = 3;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Cell cells[][] = new Cell[ROWS][COLS];
    private Field currentPlayer = Field.CROSS;
    public Board() {
        init();

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void init() {
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                cells[row][col] = new Cell(row, col);
                cells[row][col].setField(Field.EMPTY);
            }
        }
    }

    public void clear() {
        for (int row = 0; row < Board.ROWS; row++)
            for (int col = 0; col < Board.COLS; col++)
                cells[row][col].setField(Field.EMPTY);
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Field getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Field currentPlayer) {
        Field oldPlayer = this.currentPlayer;
        this.currentPlayer = currentPlayer;
        pcs.firePropertyChange("currentPlayer", oldPlayer, currentPlayer);
    }
}
