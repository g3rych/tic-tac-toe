package com.github.ui;

import com.github.model.Cell;

import javax.swing.*;
import java.awt.*;

public class CellView extends JButton {
    private Cell cell;
    public static final Dimension cellSize = new Dimension(50, 50);

    public CellView(Cell cell) {
        this.cell = cell;
        setPreferredSize(cellSize);
        setFocusable(false);
    }

    public Cell getCell() {
        return cell;
    }
}
