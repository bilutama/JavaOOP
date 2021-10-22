package minesweeper_v0.controller;

import minesweeper_v0.gui.View;
import minesweeper_v0.logic.Game;
import minesweeper_v0.logic.Level;
import minesweeper_v0.logic.MinesweeperGame;

import java.awt.event.*;

public class Controller extends MouseAdapter {
    private Game minesweeper;
    private final View view;

    public Controller(Game minesweeper, View view) {
        this.minesweeper = minesweeper;
        this.view = view;
        this.view.setResetGameButton(this, new PopupMenuHandler());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            minesweeper = new MinesweeperGame(minesweeper.getLevel());
            view.initializeGui(minesweeper);
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            view.showPopupMenu(e);
        }
    }

    class PopupMenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minesweeper = new MinesweeperGame(Level.valueOf(e.getActionCommand()));
            view.initializeGui(minesweeper);
        }
    }
}