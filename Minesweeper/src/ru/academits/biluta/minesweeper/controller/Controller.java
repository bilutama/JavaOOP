package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;
import ru.academits.biluta.minesweeper.gui.View;

import java.awt.event.*;

public class Controller extends MouseAdapter {
    private Minesweeper minesweeper;
    private final View view;
    private Level newGameLevel;

    public Controller(Minesweeper minesweeper, View view) {
        this.minesweeper = minesweeper;
        this.view = view;
        view.setResetGameButton(this, new PopupMenuHandler());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            newGameLevel = minesweeper.getLevel();
            minesweeper = new Minesweeper(newGameLevel);
            view.initializeGame(minesweeper);
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            view.showPopupMenu(e);
        }
    }

    class PopupMenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newGameLevel = Level.valueOf(e.getActionCommand());
            minesweeper = new Minesweeper(newGameLevel);
            view.initializeGame(minesweeper);
        }
    }
}
