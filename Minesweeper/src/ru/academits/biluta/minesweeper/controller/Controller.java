package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Cell;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;
import ru.academits.biluta.minesweeper.gui.View;

import java.awt.event.*;

public class Controller implements ActionListener {
    private Minesweeper minesweeper;
    private View view;

    public Controller(Minesweeper minesweeper, View view, Level level) {
        this.minesweeper = minesweeper;
        this.view = view;
        view.addResetGameButtonListener(this);
    }

    private void initialize(Level level) {
        minesweeper = new Minesweeper(level);
        view = new View(minesweeper);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Level level = minesweeper.getLevel();
        minesweeper = new Minesweeper(level);
        view.initializeGame(minesweeper);
    }
}
