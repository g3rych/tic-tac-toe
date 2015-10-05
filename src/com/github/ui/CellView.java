package com.github.ui;

import com.github.model.Cell;

import javax.swing.*;
import java.awt.*;

public class CellView extends JButton {
    public static final Dimension cellSize = new Dimension(50, 50);
    private Cell cell;

    public CellView(Cell cell) {
        this.cell = cell;
        setPreferredSize(cellSize);
        setFocusable(false);
    }

    public Cell getCell() {
        return cell;
    }

    public void paint() {
        switch (cell.getField()) {
            case CROSS:
                setText("X");
                break;
            case NOUGHT:
                setText("O");
                break;
            case EMPTY:
                setText(" ");
                break;
        }
    }
}
