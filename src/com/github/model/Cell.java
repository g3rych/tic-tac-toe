package com.github.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Cell {
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int row;
    private int col;
    private Field field;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        field = Field.EMPTY;
    }

    public void addPropertyChangeSupport(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        Field oldField = this.field;
        this.field = field;
        pcs.firePropertyChange("Field", oldField, field);
    }
    public String toString() {
        return "row "+"col "+field+" ";
    }
}
