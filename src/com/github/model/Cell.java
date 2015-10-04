package com.github.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Cell {
    private int row;
    private int col;
    private Field field;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeSupport(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        field = Field.EMPTY;
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
        this.field = field;
        pcs.firePropertyChange("Field",field,field);
        //System.out.println(row+" "+col);
    }
    public String toString() {
        return "row "+"col "+field+" ";
    }
}
