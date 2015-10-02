package com.github.model;

public class Board {
    public static int ROWS = 3;
    public static int COLS = 3;
    Cell cells[][] = new Cell[ROWS][COLS];

    public Board() {
        clear();

    }

    public void clear() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell(row, col);
                cells[row][col].paint();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
