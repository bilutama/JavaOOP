package ru.academits.biluta.minesweeper.gui;

import javax.swing.*;

public class MatrixButton extends JButton {
    private final int x;
    private final int y;

    public MatrixButton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MatrixButton(String t, int x, int y) {
        super(t);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
