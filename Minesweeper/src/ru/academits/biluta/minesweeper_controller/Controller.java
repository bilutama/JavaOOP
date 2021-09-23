package ru.academits.biluta.minesweeper_controller;

import ru.academits.biluta.minesweeper.Cell;
import ru.academits.biluta.minesweeper.Level;
import ru.academits.biluta.minesweeper.Minesweeper;
import ru.academits.biluta.minesweeper_gui.View;

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
