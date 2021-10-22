package minesweeper_v0.gui;

import javax.swing.*;

public class MatrixButton extends JButton {
    private final int buttonY;
    private final int buttonX;
    private boolean isFlagged;

    public MatrixButton(int buttonX, int buttonY) {
        super();
        this.buttonY = buttonY;
        this.buttonX = buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public int getButtonX() {
        return buttonX;
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