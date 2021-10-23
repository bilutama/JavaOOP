package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Game;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.MinesweeperGame;
import ru.academits.biluta.minesweeper.gui.View;

import javax.swing.*;
import java.awt.event.*;

public class Controller extends MouseAdapter {
    private Game minesweeper;
    private final View view;

    public Controller(Game minesweeper, View view) {
        this.minesweeper = minesweeper;
        this.view = view;
        this.view.setResetGameButton(this, new PopupMenuHandler());
        new GameTimeListener();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            minesweeper = new MinesweeperGame(minesweeper.getLevel());
            view.initializeGui(minesweeper);
            view.setGameTime(0L);
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
            view.setGameTime(0L);
        }
    }

    class GameTimeListener implements ActionListener {
        Timer timer;

        public GameTimeListener() {
            timer = new Timer(1000, this);
            timer.setInitialDelay(0);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setGameTime(minesweeper.getGameTime());
        }
    }
}