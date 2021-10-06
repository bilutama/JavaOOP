package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Cell;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;
import ru.academits.biluta.minesweeper.gui.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends MouseAdapter {
    private Minesweeper minesweeper;
    private View view;

    public Controller(Minesweeper minesweeper, View view, Level level) {
        this.minesweeper = minesweeper;
        this.view = view;
    }

    private void initialize(Level level, Cell firstCell) {
        minesweeper = new Minesweeper(level);
        view = new View(level, minesweeper);
    }
}
