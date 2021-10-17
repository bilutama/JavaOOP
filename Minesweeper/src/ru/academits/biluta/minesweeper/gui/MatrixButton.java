package ru.academits.biluta.minesweeper.gui;

import javax.swing.*;

public class MatrixButton extends JButton {
    private final int row;
    private final int column;
    private boolean isFlagged;

    public MatrixButton(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setFlagged() {
        isFlagged = true;
    }

    public void setUnflagged() {
        isFlagged = false;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
}