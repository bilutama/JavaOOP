package ru.academits.biluta.minesweeper.gui;

import javax.swing.*;

public class MatrixButton extends JButton {
    private final int row;
    private final int column;

    public MatrixButton(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }

    public MatrixButton(String buttonLabel, int row, int column) {
        super(buttonLabel);
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}