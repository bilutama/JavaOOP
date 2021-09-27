package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Cell;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;
import ru.academits.biluta.minesweeper.gui.View;

public class Controller {
    private Minesweeper minesweeper;
    private View view;

    public Controller(Minesweeper minesweeper, View view, Level level) {
        this.minesweeper = minesweeper;
        this.view = view;
    }

    private void initialize(Level level, Cell firstCell) {
        view = new View(level);
        minesweeper = new Minesweeper(level, firstCell);
    }

    private void resumeOnClick() {

    }
}