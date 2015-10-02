package com.github.model;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;

public class Cell extends JButton {
    public static final Dimension cellSize = new Dimension(50, 50);
    private int row;
    private int col;
    private Space space;
    private SwingPropertyChangeSupport pcs = new SwingPropertyChangeSupport(this);

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        space = Space.EMPTY;
        setPreferredSize(cellSize);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void paint() {
        switch (space) {
            case CROSS:
                setText("X");
                break;
            case NOUGHT:
                setText("O");
                break;
            default:
                setText(" ");
                break;
        }
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        Space oldSpace = this.space;
        this.space = space;
        pcs.firePropertyChange("cell", oldSpace, space);
    }
}
