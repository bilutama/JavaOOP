package ru.academits.biluta.minesweeper.main;

import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.gui.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Minesweeper minesweeper = new Minesweeper(Level.EASY);

            View view = new View(Level.EASY);
        });
    }
}
